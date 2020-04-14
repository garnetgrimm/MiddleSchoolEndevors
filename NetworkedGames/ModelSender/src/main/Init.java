package main;

import javax.swing.*;
//import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Init {
	
public static JTextField first_name = new JTextField();
public static JTextField last_name = new JTextField();
public static JTextField email = new JTextField();
public static JTextField phone_num = new JTextField();

public static List<Attached_File> Attached_Files = new ArrayList<Attached_File>();

public static boolean connected = false;

@SuppressWarnings("serial")
public static void main(String[] args) {

	JFrame frame = new JFrame("3DMail");
	
	JButton upload = new JButton();
	upload.setEnabled(false);
	upload.setText("Upload");
	upload.setBounds(25, 65, 95, 25);
	upload.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
			 new Thread(new Network()).start();
		  }
	});
	
	email.setBounds(125, 35, 140, 24);
	phone_num.setBounds(125, 65, 140, 25);
	
	JButton file_drop = new JButton();
	file_drop.setEnabled(false);
	file_drop.setText("Drop Here");	
	file_drop.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                @SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    /*
	                     * NOTE:
	                     *  When I change this to a println,
	                     *  it prints the correct path
	                     */
	                	//System.out.println(currNum + " " + file.getAbsolutePath());
	                    Attached_Files.add(new Attached_File(file.getName(), File_Handler.file_to_string(file)));
	                    if(Attached_Files.size() <= 1) file_drop.setText(Attached_Files.size() + " file.");
	                    else  file_drop.setText(Attached_Files.size() + " files.");
	                    upload.setEnabled(true);
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
    
	file_drop.setBounds(25, 5, 95, 54);
    
    //RRA.setPlaceHolder("johndoe@example.com");
    first_name.setBounds(125, 5, 70, 25);
    last_name.setBounds(196, 5, 70, 25);
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	// Create and set up the content pane.
	frame.add(file_drop);
	frame.add(email);
	frame.add(phone_num);
	frame.add(first_name);
	frame.add(last_name);
	frame.add(upload);
	// Display the window.		
	frame.setResizable(false);
	frame.pack();
	frame.setSize(300, 130);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
	frame.setVisible(true);

}

}