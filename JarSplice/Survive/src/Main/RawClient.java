package Main;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class RawClient {

	private static int x = 0;
	
	public static void main(String[] args) {
		System.out.println("Starting Client");
		
		while(true) {
			x++;
		try {
			DatagramSocket socket = new DatagramSocket();
			
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			
			String requestMessage = Integer.toString(x);
			
			byte[] requestBytes = requestMessage.getBytes();
			DatagramPacket requestPacket = new DatagramPacket(requestBytes, requestBytes.length, serverAddress, 7500);
			socket.send(requestPacket);
			byte[] responseBuffer = new byte[256];
			DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
			socket.receive(responsePacket);
			System.out.println("Sent " + x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
