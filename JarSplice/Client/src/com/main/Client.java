package com.main;

import javax.swing.JOptionPane;

import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

public class Client {

	public static void main(String[] args) {
		
		String IP = JOptionPane.showInputDialog("Enter Target IP");
				
		final String host = IP;
		final int port = 43594;
		final GNetClient netClient = new GNetClient(host, port);
	
		netClient.addEventListener(new ClientEventListener() {

			@Override
			protected void clientConnected(ServerModel server) {
				//String name = JOptionPane.showInputDialog("Enter Name");
				//Packet LOGIN = new Packet("namePacket", 1);
				//LOGIN.addEntry("username", name);
				//server.sendPacket(LOGIN);
				int x = 0;
				int y = 0;
				
				while(true) {
				x++;
				y++;	
				Packet Loc = new Packet("Location", 1);
				Loc.addEntry("x", x);
				Loc.addEntry("x", y);
				server.sendPacket(Loc);
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
