import java.io.IOException;
import java.net.ServerSocket;

public class InServer implements Runnable {
	
	public static int clientNumber = 0;
	
	public void run() {
		try {
			System.out.println("The capitalization server is running.");
	        clientNumber = 0;
	        ServerSocket listener = new ServerSocket(9898);
	        try {
	            while (true) {
	                new Capitalizer(listener.accept(), clientNumber++).start();
	                //update user list to all clients
	            }
	        } finally {
	            listener.close();
	        }
		} catch(IOException e) {}
	}
}
