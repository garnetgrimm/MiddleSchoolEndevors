package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

public class init {
	
public static String saveLoc = "";	
	
@SuppressWarnings("serial")


public static JButton File_Search = null;
public static JButton File_Drop = null;
public static JButton Save = null;
public static JButton Done = null;
public static JTextField Depth = null;

public static void main(String[] args) {
	
	/*
	try {
	    Image img = ImageIO.read(new FileInputStream("src/images/Convert.png"));
	    convert.setIcon(new ImageIcon(img));
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	 */ 
	
	File_Search = new JButton("Open PNG");
	File_Search.setVerticalTextPosition(AbstractButton.CENTER);
	File_Search.setHorizontalTextPosition(AbstractButton.LEADING);
	File_Search.setBounds(826 - 95, 35, 95, 25);
	File_Search.setToolTipText("Open a sprite or drag one in!"); // Adding Tool
	File_Search.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  { 
			  init_helper.openJChooser();
		  }
	});
	
    Done = new JButton();
    Done.setText("Done");
    Done.setEnabled(false);
    Done.setBounds(826 - 64, 65, 64, 25);
	Done.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  { 
			  init_helper.setDepths(); 
			  init_helper.calcHighest();
		  }
	});
    
    Depth = new JTextField();
    Depth.setText("0");
    Depth.setBounds(826 - 64 - 25 - 5, 65, 25, 25);
	
	File_Drop = new JButton();
	File_Drop.setEnabled(false);
	File_Drop.setText("Drop Here");		
	File_Drop.setDropTarget(new DropTarget() {
		private static final long serialVersionUID = 1L;
		public synchronized void drop(DropTargetDropEvent evt) {
			try {
	        	evt.acceptDrop(DnDConstants.ACTION_COPY);
	            @SuppressWarnings("unchecked")
				List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    File_Drop.setText(file.getAbsolutePath());
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
			
				Done.setEnabled(true);
	        }
	    });
    
	File_Drop.setBounds(0, 0, 725, 630);
    
    Save = new JButton();
    Save.setText("Save");
    Save.setEnabled(false);
    Save.setBounds(826 - 64, 5, 64, 25);
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	// Create and set up the frame.
	JFrame frame = new JFrame("ExcelBlender");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	// Create and set up the content pane.
	frame.add(File_Search);
	frame.add(Done);
	frame.add(Save);
	frame.add(Depth);
	frame.add(File_Drop);
	// Display the window.		
	frame.setResizable(false);
	frame.pack();
	frame.setSize(840, 630);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
	frame.setVisible(true);

}

}