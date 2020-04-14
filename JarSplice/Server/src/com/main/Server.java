package com.main;

import javax.swing.JOptionPane;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

public class Server {

	public static void main(String[] args) {
		
		System.out.println("Server Running");
		
		
		String IP = JOptionPane.showInputDialog("Enter Target IP");
		
		final String host = IP;
		final int port = 43594;
		final GNetServer netServer = new GNetServer(host, port);
		
		netServer.addEventListener(new ServerEventListener() {

			@Override
			protected void clientConnected(ClientModel arg0) {
				// TODO Auto-generated method stub
				System.out.println("Client Connected");
			}

			@Override
			protected void clientDisconnected(ClientModel arg0) {
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
			protected void packetReceived(ClientModel client, Packet packet) {
				// TODO Auto-generated method stub
 				if(packet.getPacketName().equals("Location")) {
 					int name = (Integer) packet.getEntry("x");
 					System.out.println(name);
 					return;
 				}
				
			}});
		
		netServer.bind();
		netServer.start();
		
	}	
}
