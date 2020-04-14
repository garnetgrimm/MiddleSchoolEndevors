package Networking;

import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

import Msc.ConfReader;
import PasswordState.CheckReadied;
import PasswordState.PasswordBox;
import PasswordState.PasswordState;

import main.Main;

public class Client {

	public static int SendMax = 100000;
	public static String host;
	public static String IP;
	
	public static void Start() {
		
		boolean foundName = false;
		String rawIp = PasswordBox.CipField.getText();
		for(int i = 0; i < ConfReader.lines; i++) {
			if(rawIp.equals(ConfReader.names[i])) { IP = ConfReader.ips[i]; foundName = true; }
		}
		if(!foundName) { IP = rawIp; }
		
		host = IP;
		final int port = 43594;
		final GNetClient netClient = new GNetClient(host, port);
	
		netClient.addEventListener(new ClientEventListener() {

			@Override
			protected void clientConnected(ServerModel server) {
				int SendTime = 0;
				while(true) {
					while(Main.sendRequest) {
						SendTime += 1;
						if(SendTime > SendMax) SendTime = 0;
						if(SendTime == SendMax) {
							//THIS PACKET IS ONLY FOR STARTUP
							//MAKE A NEW PACKET FOR GAMEPLAY
							Packet Loc = new Packet("Startup", 5);
							Loc.addEntry("ServerReady", CheckReadied.ServerReady);
							Loc.addEntry("ClientReady", CheckReadied.ClientReady);
							Loc.addEntry("PassReady", CheckReadied.PassReady);
							Loc.addEntry("PASS", CheckReadied.password);
							Loc.addEntry("BowClicked", PasswordState.bowTieClicked);
							server.sendPacket(Loc);							
						}
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
