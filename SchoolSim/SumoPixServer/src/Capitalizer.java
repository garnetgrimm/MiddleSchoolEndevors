import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Capitalizer extends Thread {
	        private Socket socket;
	        private int clientNumber;
	        
	    	private String ShipPixels = "";
	    	
	        public Capitalizer(Socket socket, int clientNumber) {
	            this.socket = socket;
	            this.clientNumber = clientNumber;
	            System.out.println("New connection with client# " + clientNumber + " at " + socket);
	        }

	        /**
	         * Services this thread's client by first sending the
	         * client a welcome message then repeatedly reading strings
	         * and sending back the capitalized version of the string.
	         */
	        public void run() {
	            try {

	                // Decorate the streams so we can send characters
	                // and not just bytes.  Ensure output is flushed
	                // after every newline.
	                BufferedReader in = new BufferedReader(
	                        new InputStreamReader(socket.getInputStream()));
	                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	                // Send a welcome message to the client.
	                out.println(clientNumber);

	                // Get messages from the client, line by line; return them
	                // capitalized
	                while (true) {
	                    String input = in.readLine();
	                    if (input == null || input.equals(".")) {
	                        break;
	                    }
	                    
	                    String[] command = input.split(":");
	                    if(command[0].equals("setUsername")) {
	                    	String name = "";
	                    	for(int i = 1; i < command.length; i++) {
	                    		name += command[i];
	                    	}
		                    ClientList.UserList.add(name);
		                    ClientList.ReadyList.add(false);
		                    ClientList.ShipList.add(null);
		                    ClientList.xLocList.add(0);
		                    ClientList.yLocList.add(0);
		                    out.println(ClientList.UserList);
	                    } else if(command[0].equals("getUsers")) {
	                    	out.println(ClientList.UserList);
	                    } else if(command[0].equals("ready")) {
	                    	ClientList.ReadyList.set(clientNumber, true);
	                    	out.println("ready set!");
	                    } else if(command[0].equals("unready")) {
	                    	ClientList.ReadyList.set(clientNumber, false);
	                    	out.println("ready unset!");
	                    } else if(command[0].equals("getReady")) {
	                    	out.println(ClientList.ReadyList);
	                    } else if(command[0].equals("defineShip")) {
	                    	ShipPixels += (command[1] + ":" + command[2] + ":" + command[3] + ",");
	                    } else if(command[0].equals("getShip")) {
	                    	String[] s = ClientList.ShipList.get(Integer.parseInt(command[1]));
	                    	for(int i = 0; i < s.length; i++) {
	                    		out.println(s[i]);
	                    	}
	                    	out.println("!");
	                    } else if(command[0].equals("addShip")) {
	                    	String[] sp = ShipPixels.split(",");
	                    	ClientList.ShipList.set(clientNumber, sp);
	                    } else if(command[0].equals("setXLoc")) {
	                    	ClientList.xLocList.set(clientNumber, Integer.parseInt(command[1]));
	                    } else if(command[0].equals("setYLoc")) {
	                    	ClientList.yLocList.set(clientNumber, Integer.parseInt(command[1]));
	                    } else if(command[0].equals("getXLoc")) {
	                    	out.println(ClientList.xLocList.get(Integer.parseInt(command[1])));
	                    } else if(command[0].equals("getYLoc")) {
	                    	out.println(ClientList.yLocList.get(Integer.parseInt(command[1])));
	                    }
	                    
	                }
	            } catch (IOException e) {
	                System.out.println("Error handling client# " + clientNumber + ": " + e);
	            } finally {
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    System.out.println("Couldn't close a socket, what's going on?");
	                }
	                System.out.println("Connection with client# " + clientNumber + " closed");
	            }
	            ClientList.UserList.remove(clientNumber);
	            ClientList.ReadyList.remove(clientNumber);
                InServer.clientNumber--;
	        }
}
