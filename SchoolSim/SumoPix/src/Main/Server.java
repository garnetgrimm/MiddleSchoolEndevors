package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import startupComponents.Header;
import startupComponents.ShipCreator;
import startupComponents.UsersList;

public class Server implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
	public static String HOST_IP;
	public static String USERNAME;
	public static boolean started = false;
	public static boolean first = true;

	public List<String> getShip(int ShipNum) {
		
		List<String> Ship = new ArrayList<String>();
		
		try {
		out.println("getShip:" + ShipNum); 
		boolean stillrec = true;
		while(stillrec) {
			String response = in.readLine();
			if(response.equals("!")) stillrec = false;
			else {
				Ship.add(response);
			}
		}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return Ship;
	}
	
	public List<String> getListVals(String s) {
		List<String> returnVal = new ArrayList<String>();
		char[] c = s.toCharArray();
		boolean foundComma = false;
		for(int i = 0; i < c.length; i++) {
			if(c[i] == ',') foundComma = true;
		}
		
		if(foundComma) {
		String[] split = s.substring(1, s.length() - 1).split(", ");
		
		for(int i = 0; i < split.length; i++) {
			returnVal.add(split[i]);
		}
		
		} else {
			returnVal.add(s.substring(1, s.length() - 1));
		}
		
		return returnVal;
	}
	
	public String sendMsg(String data) {
		out.println(data);
        String response;
        try {
			response = in.readLine();
			if (response == null || response.equals("")) {
				System.exit(0);
			}
		} catch (IOException ex) {
			response = "Error: " + ex;
		}
        
        return response;
	}
	
	   public void run() {
		   try {
	        // Get the server address from a dialog box.
			HOST_IP = Header.Host.getText();
			USERNAME = Header.UserName.getText();
			
	        // Make connection and initialize streams
	        @SuppressWarnings("resource")
			Socket socket = new Socket(HOST_IP, 9898);
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream(), true);
			
	            init.clientNum = Integer.parseInt(in.readLine());
	            
	        	UsersList.ClientList = getListVals(sendMsg("setUsername:" + USERNAME));
	        	started = true;
	        	
	        while(true) {
				UsersList.ClientList = getListVals(sendMsg("getUsers"));
				UsersList.ReadyList = getListVals(sendMsg("getReady"));
				if(ShipCreator.readied && first) { 
					for(int x = 0 ; x < ShipCreator.maxPixels; x++) {
						for(int y = 0 ; y < ShipCreator.maxPixels; y++) {
							if(!ShipCreator.pixels[x][y].equals("0xGGGGGG")) out.println("defineShip:" + x + ":" + y + ":" + ShipCreator.pixels[x][y]); 
						}
					}
					
					out.println("addShip");
					sendMsg("ready"); 
					first = false;
				}
				
				//need unready message
				if(UsersList.entered) { 
					ShipCreator.readied = false; 
					gameState.Player1 = getShip(gameState.client1); 
					gameState.Player2 = getShip(gameState.client2); 
					out.println("setXLoc:" + gameState.myX);
					out.println("setYLoc:" + gameState.myY);
					if(gameState.playerNum == 1) gameState.p2x = Integer.parseInt(sendMsg("getXLoc:"+gameState.client2));
					if(gameState.playerNum == 2) gameState.p1x = Integer.parseInt(sendMsg("getXLoc:"+gameState.client1));
				}
	        }
	        
		   } catch(IOException e) {
			   
		   }
	    }
}
