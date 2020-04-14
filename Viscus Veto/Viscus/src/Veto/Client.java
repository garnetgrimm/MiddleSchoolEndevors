package Veto;

import javax.swing.JOptionPane;

import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

import States.IntroState;

public class Client {

	public static int SendMax = 100000;
	public static String host;
	public static String IP;
	
	public static void Send() {
		
		IP = JOptionPane.showInputDialog("Enter Hosts IP");
		
		//System.out.println(IP);
		
		host = IP;
		final int port = 43594;
		final GNetClient netClient = new GNetClient(host, port);
	
		netClient.addEventListener(new ClientEventListener() {

			@Override
			protected void clientConnected(ServerModel server) {
				int SendTime = 0;
				while(true) {	
				SendTime += 1;
				if(SendTime > SendMax) SendTime = 0;
				if(SendTime == SendMax) {
				Packet Loc = new Packet("Location", 2);
				Loc.addEntry("x", IntroState.x);
				Loc.addEntry("Dir", IntroState.Dir);
				server.sendPacket(Loc);
				}
				}				
				}
			
			@Override
			protected void clientDisconnected(ServerModel server) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void debugMessage(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void errorMessage(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void packetReceived(ServerModel server, Packet arg1) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		netClient.bind();
		netClient.start();
	}
	
}
