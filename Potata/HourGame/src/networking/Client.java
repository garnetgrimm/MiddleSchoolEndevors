package networking;

import javax.swing.JOptionPane;

import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

import main.Armor;
import main.GameState;
import main.Health;

public class Client {

	public static int SendMax = 100000;
	public static String host;
	public static String IP;
	
	public static void Start() {
		
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
				Packet Loc = new Packet("Location", 6);
				Loc.addEntry("x", GameState.P1X);
				Loc.addEntry("Dir", GameState.P1Dir);
				Loc.addEntry("Health", Health.P1Health);
				Loc.addEntry("SwordDown", Armor.P1SwordDown);
				Loc.addEntry("Rot", Armor.P1RotInt);
				Loc.addEntry("SwordChange", Armor.P1SwordChange);
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
