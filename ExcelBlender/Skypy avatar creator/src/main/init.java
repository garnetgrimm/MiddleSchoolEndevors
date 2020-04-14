package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class init {
	
public static String saveLoc = "";	
public static List<File> Files = new ArrayList<File>();  
public static JList<Object> list = new JList<Object>(Files.toArray());
public static JFrame frame = new JFrame("ExcelBlender");
public static JButton Save = new JButton();

@SuppressWarnings("serial")
public static void main(String[] args) {
	
		JButton myPanel = new JButton();
    	
	    myPanel.setEnabled(false);
	    
	    myPanel.setText("Drop Here");	
	    	
	    myPanel.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                @SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                	boolean found = false;
	                	for(int i = 0; i < Files.size(); i++) {
	                		if(Files.get(i).equals(file)) found = true;
	                	}
	                	if(!found)Files.add(file);
	                }
	                
	                frame.remove(list);
	                list = new JList<Object>(Files.toArray());
	                list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	                list.setLayoutOrientation(JList.VERTICAL);
	                list.setVisibleRowCount(-1);
	                list.setBounds(125, 5, 135, 85);
	                frame.add(list);
	                frame.repaint();
	                Save.setEnabled(true);
	                
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
    
	myPanel.setBounds(25, 35, 95, 55);

    Save.setText("Save");
    Save.setEnabled(false);
    Save.setBounds(25, 5, 95, 25);
    
	Save.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
			  for(int f = 0; f < Files.size(); f++) {
				  BufferedImage b = ImageHandler.FileToBufferedImage(Files.get(f));
				  String[][] imageArray = ImageHandler.BufferedImageToArray(b);
				  String saveLoc = Files.get(f).toString();
				  DrawImage.draw(b, imageArray, saveLoc, 0);
				  DrawImage.draw(b, imageArray, saveLoc, 1);
				  DrawImage.draw(b, imageArray, saveLoc, 2);
				  DrawImage.draw(b, imageArray, saveLoc, 3);
				  DrawImage.draw(b, imageArray, saveLoc, 4);
				  DrawImage.draw(b, imageArray, saveLoc, 5);
				  DrawImage.draw(b, imageArray, saveLoc, 6);
			  }
		  }
	});
     
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list.setLayoutOrientation(JList.VERTICAL);
    list.setVisibleRowCount(-1);
    list.setBounds(125, 5, 135, 85);
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	// Create and set up the frame.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	// Create and set up the content pane.
	frame.add(myPanel);
	frame.add(Save);
	frame.add(list);
	// Display the window.		
	frame.setResizable(false);
	frame.pack();
	frame.setSize(300, 130);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
	frame.setVisible(true);

}

}