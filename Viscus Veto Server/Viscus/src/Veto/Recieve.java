package Veto;

import javax.swing.JOptionPane;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

import States.IntroState;

public class Recieve {

	public static boolean Started = false;
	
	public static void Server() {
		
		System.out.println("Server Running");
		
		
		String IP = JOptionPane.showInputDialog("Enter Your IP");
		
		final String host = IP;
		final int port = 43594;
		final GNetServer netServer = new GNetServer(host, port);
		
		netServer.addEventListener(new ServerEventListener() {

			@Override
			protected void clientConnected(ClientModel arg0) {
				// TODO Auto-generated method stub
				System.out.println("Client Connected");
				Started = true;
			}

			@Override
			protected void clientDisconnected(ClientModel arg0) {
				// TODO Auto-generated method stub
				System.out.println("Client Disconnected");
				Started = false;
			}

			@Override
			protected void debugMessage(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void errorMessage(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("Fatal Error");
			}

			@Override
			protected void packetReceived(ClientModel client, Packet packet) {
				// TODO Auto-generated method stub
 				if(packet.getPacketName().equals("Location")) {
 					IntroState.P1 = (Integer) packet.getEntry("x");
 					IntroState.P1Dir = (Integer) packet.getEntry("Dir");
 					return;
 				}
				
			}});
		
		System.out.println("Server Started At " + IP);
		netServer.bind();
		netServer.start();
		
	}	
}
