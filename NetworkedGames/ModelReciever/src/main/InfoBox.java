package main;

import javax.swing.JOptionPane;

public class InfoBox extends Thread {
	
	public JOptionPane jp = null;
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		int result = jp.showOptionDialog(
				null,
	            "Connecting... " , "Connecting to network",
	            JOptionPane.CANCEL_OPTION,
	            JOptionPane.NO_OPTION,
	            null,
	            null,
	            null
	    );
		
		if(result == 2) {
			System.exit(0);
		}
	}
}
