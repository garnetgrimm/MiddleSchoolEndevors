package Main;

import java.io.IOException;
import java.net.*;

public class RawServer {
	
	public static void main(String[] args) throws SocketException {
		
		System.out.println("Starting Server");
		
		while(true) {
		
		try {
			DatagramSocket socket = new DatagramSocket(7500);
			boolean serverActive = true;
			byte[] buffer = new byte[256];
			DatagramPacket incomePacket = new DatagramPacket(buffer, buffer.length);
			socket.receive(incomePacket);
			InetAddress client_address = incomePacket.getAddress();
			int client_port = incomePacket.getPort();
			String message = new String(buffer, 0, incomePacket.getLength());
			System.out.println(message);
			String reply = message + "Sent";
			byte[] replyBytes = reply.getBytes();
			DatagramPacket replyPacket = new DatagramPacket(replyBytes, replyBytes.length, client_address, client_port);
			socket.send(replyPacket);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
