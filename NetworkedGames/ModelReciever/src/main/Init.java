package main;

import javax.swing.*;
//import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Init {
	
public static boolean connected = false;	

public static DefaultListModel<String> newModel = new DefaultListModel<>();
public static DefaultListModel<String> readModel = new DefaultListModel<>();

public static void main(String[] args) {

	//start server, if no handshake, create popup and close
	InfoBox IF = new InfoBox();
	IF.start();
	
	Network n = new Network();
	n.send_request("update", new String[] {"first_run=true"});
	
	IF.interrupt();
	
	//IF.jp.setVisible(false);
	//new Thread(n).start();
	
	if(!connected) {
		JOptionPane.showOptionDialog(
				null,
                "Connection failed, check internet connection. " , "Failure",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );
	  System.exit(0);
	} else {
		n.start();
	}
	
	JTextField searchBar = new JTextField();
	searchBar.setBounds(105, 5, 170, 20);
	
	JButton search = new JButton();
	search.setBounds(20, 5, 80, 20);
	search.setText("search");
	
	JButton setRead = new JButton();
	setRead.setText("read");
	setRead.setBounds(195, 95, 80, 20);
	JButton setUnread = new JButton();
	setUnread.setText("unread");
	setUnread.setBounds(20, 95, 80, 20);	
	
	JList<String> newList = null;
	JList<String> readList = null;
	
	JButton pull = new JButton();
	pull.setText("generate");
	pull.setBounds(105, 95, 85, 20);
	pull.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
			 String file_name = "sym_test.html";
			 String encoding = n.send_request("generate", new String[] { "file_name=" + file_name });
			 System.out.println(file_name);
			 try {
				FileWriter fw = new FileWriter(file_name);
				fw.write(File_Handler.url_decode(encoding));
				fw.close();
			 } catch (IOException e1) {
				e1.printStackTrace();
			}
		  }
	});
    
    //create the list
    newList = new JList<>(newModel);
	newList.setBounds(45,30,200,60);
    
	JFrame frame = new JFrame("3DMail");
	JFrame.setDefaultLookAndFeelDecorated(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	// Create and set up the content pane.
	frame.add(newList);
	frame.add(pull);
	//frame.add(setRead);
	//frame.add(setUnread);
	frame.add(searchBar);
	frame.add(search);
	// Display the window.		
	frame.setResizable(false);
	frame.pack();
	frame.setSize(300, 160);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
	frame.setVisible(true);

}

}