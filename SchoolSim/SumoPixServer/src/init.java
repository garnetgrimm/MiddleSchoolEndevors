import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class init {
	
	public static void main(String[] args) {
	    
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the frame.
		JFrame frame = new JFrame("SumoPix Server GUI");
		
		JButton ConfirmPixels = new JButton();
	    ConfirmPixels.setText("Confirm");
	    ConfirmPixels.setEnabled(true);
	    ConfirmPixels.setBounds(135, 5, 100, 25);
	    ConfirmPixels.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println();
				
			}
		});
		
		JTextField PixelNum = new JTextField(1);
		PixelNum.setBounds(110, 5, 20, 25);
		
	    JButton Pixels = new JButton();
	    Pixels.setText("Max Pixels");
	    Pixels.setToolTipText("How many pixels each player is allowed to use to build their vessle with.");
	    Pixels.setEnabled(false);
	    Pixels.setBounds(5, 5, 100, 25);
	    
	    JButton Confirm = new JButton();
	    Confirm.setText("Start");
	    //StartHost.setToolTipText("Start Server Host, Only do this if you entered you correct IP");
	    Confirm.setEnabled(true);
	    Confirm.setBounds(95, 45, 100, 25);
	    Confirm.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				(new Thread(new InServer())).start();
			}
	    	
		});
	   
		// TODO Auto-generated method stub
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// Create and set up the content pane.
		// Display the window.		
		frame.setResizable(false);
		frame.pack();
		frame.setSize(300, 130);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		frame.add(Confirm);
		frame.add(ConfirmPixels);
		frame.add(PixelNum);
		frame.add(Pixels);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
