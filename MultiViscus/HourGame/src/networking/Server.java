package networking;

import javax.swing.JOptionPane;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

import main.Armor;
import main.GameState;
import main.Health;

public class Server {

	public static boolean Started = false;
	
	public static void Start() {
		
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
				
			}

			@Override
			protected void errorMessage(String arg0) {
				System.out.println("Fatal Error");
			}

			@Override
			protected void packetReceived(ClientModel client, Packet packet) {
 				if(packet.getPacketName().equals("Location")) {
 					GameState.P2X = (Integer) packet.getEntry("x");
 					GameState.P2Dir = (Integer) packet.getEntry("Dir");
 					Health.P2Health = (Float) packet.getEntry("Health");
 					Armor.P2SwordDown = (Boolean) packet.getEntry("SwordDown");
 					Armor.P2RotInt = (Float) packet.getEntry("Rot");
 					Armor.P2SwordChange = (Boolean) packet.getEntry("SwordChange");
 					return;
 				}
				
			}});
		
		System.out.println("Server Started At " + IP);
		netServer.bind();
		netServer.start();
		
	}	
}
