package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class init {
	
public static String saveLoc = "";	
	
@SuppressWarnings("serial")
public static void main(String[] args) {

	JButton convert = new JButton();
	convert.setBounds(95,5,25,25); 
	convert.setEnabled(false);
	
	try {
	    Image img = ImageIO.read(new FileInputStream("src/images/Convert.png"));
	    convert.setIcon(new ImageIcon(img));
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	
	JButton Demo = new JButton("RRA Demographic");
	Demo.setVerticalTextPosition(AbstractButton.CENTER);
	Demo.setHorizontalTextPosition(AbstractButton.LEADING);
	Demo.setBounds(125, 35, 140, 25);
	Demo.setToolTipText("Save to RRA Demographic Format"); // Adding Tool
	Demo.setEnabled(false);
	Demo.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  { 
			  try { XlsWriter.writeRRADemo(saveLoc + "RRA demographic.xls"); } catch (IOException e1) { e1.printStackTrace(); }
		  }
	});
	
	JButton Enroll = new JButton("RRA Enrollment");
	Enroll.setVerticalTextPosition(AbstractButton.CENTER);
	Enroll.setHorizontalTextPosition(AbstractButton.LEADING);
	Enroll.setBounds(125, 65, 140, 25);
	Enroll.setToolTipText("Save to RRA Enrollment Format"); // Adding Tool
	Enroll.setEnabled(false);
	Enroll.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  { 
			  try { XlsWriter.writeRRAEnroll(saveLoc + "RRA Enrollment.xls"); } catch (IOException e1) { e1.printStackTrace(); }
		  }
	});
	
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
	                    /*
	                     * NOTE:
	                     *  When I change this to a println,
	                     *  it prints the correct path
	                     */
	                	//System.out.println(currNum + " " + file.getAbsolutePath());
	                    myPanel.setText(file.getAbsolutePath());
	                    convert.setEnabled(true);
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
    
	convert.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
			convert.setEnabled(false);  
			  
			File f;
			f = new File(myPanel.getText());
			
			String[] folders = f.toString().split("\\\\");
			saveLoc = "";
			for(int i = 0; i < folders.length - 1; i++) {
				saveLoc += folders[i] + "\\";
			}
			
			Demo.setEnabled(true);
			Enroll.setEnabled(true);
			
			//readfile throws error because mmddyyyy is not a int
		    XlsHandler.readFile(f);
		  }
	});
    
	myPanel.setBounds(25, 35, 95, 55);
	
    JButton xTool = new JButton();
    xTool.setText("xTool");
    xTool.setEnabled(false);
    xTool.setBounds(25, 5, 69, 25);
    
    JButton RRA = new JButton();
    RRA.setText("Save as");
    RRA.setEnabled(false);
    RRA.setBounds(125, 5, 140, 25);
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	// Create and set up the frame.
	JFrame frame = new JFrame("ExcelBlender");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	// Create and set up the content pane.
	frame.add(myPanel);
	frame.add(Demo);
	frame.add(Enroll);
	frame.add(xTool);
	frame.add(RRA);
	frame.add(convert);
	// Display the window.		
	frame.setResizable(false);
	frame.pack();
	frame.setSize(300, 130);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
	frame.setVisible(true);

}

}