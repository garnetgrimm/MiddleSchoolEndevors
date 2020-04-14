package Networking;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

import Msc.ConfReader;
import Msc.EnemyStats;
import PasswordState.CheckReadied;
import PasswordState.PasswordBox;
import PasswordState.PasswordState;

public class Server {

	public static boolean Started = false;
	public static String IP;
	
	public static void Start() {
		
		System.out.println("Server Starting");
		
		boolean foundName = false;
		String rawIp = PasswordBox.SipField.getText();
		for(int i = 0; i < ConfReader.lines; i++) {
			if(rawIp.equals(ConfReader.names[i])) { IP = ConfReader.ips[i]; foundName = true; }
		}
		if(!foundName) { IP = rawIp; }
		
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
 				if(packet.getPacketName().equals("Startup")) {
 					CheckReadied.EClientReady = (Boolean) packet.getEntry("ClientReady");
 					CheckReadied.EServerReady = (Boolean) packet.getEntry("ServerReady");
 					CheckReadied.EPassReady = (Boolean) packet.getEntry("PassReady");
 					EnemyStats.enemyPassword = (String) packet.getEntry("PASS");
 					PasswordState.EBowTieClicked = (boolean) packet.getEntry("BowClicked");
 					return;
 				}
 				if(packet.getPacketName().equals("Gameplay")) {
 					
 				}
				
			}});
		
		System.out.println("Server Started At " + IP);
		netServer.bind();
		netServer.start();
		
	}	
}
