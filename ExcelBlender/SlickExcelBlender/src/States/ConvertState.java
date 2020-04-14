package States;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.*;

import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Button;
import main.Date;
import main.Main;
import main.XlsHandler;

public class ConvertState extends BasicGameState {
	
	Button button1 = null;
	Button button2 = null;
    JTextArea myPanel = new JTextArea();
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//XlsHandler.readFile(new File("src/Resources/RRA demographic.xls"));
		//XlsHandler.readFile(new File("src/Resources/xTool.xls"));
		
		button1 = new Button("Save It!", 150, 350);
		button2 = new Button("Save It!", Main.Width - button1.width - 150, 350);
		
		System.out.println(Date.switchLayout("01012015"));
		//System.out.println(Date.switchLayout("20160420"));
		
	    myPanel.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    /*
	                     * NOTE:
	                     *  When I change this to a println,
	                     *  it prints the correct path
	                     */
	                    myPanel.setText(file.getAbsolutePath());
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
	    
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		boolean Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		if(button1.isMouseOver(arg0) && Click) { } 
		if(button2.isMouseOver(arg0) && Click) { } 
		
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		button1.draw(arg0, g);
		button2.draw(arg0, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
