package startupComponents;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import Main.Button;

public class Header {
	
	public static int xOffset = 150;
	
	public static TextField UserName = null;
	public static TextField Host = null;
	static Button StartServer = new Button("Start", 420 + xOffset, 5);
	
	public static void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		UserName = new TextField(arg0, arg0.getDefaultFont(), 300 + xOffset, 15, 100, 20);
		
		Host = new TextField(arg0, arg0.getDefaultFont(), 80 + xOffset, 15, 100, 20);
	}
	
	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		boolean Click = arg0.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
		if(StartServer.isMouseOver(arg0) && Click) Main.init.startServer();
	}

	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, Main.init.Width, 50);
		g.setColor(Color.black);
		Host.render(arg0, g);
		StartServer.draw(arg0, g);
		g.scale(0.5f, 0.5f);
		g.setColor(Color.black);
		g.drawString("Host IP:", 5 + (xOffset * 2), 30);
		g.drawString("Username:", 405 + (xOffset * 2), 30);
		g.resetTransform();
		UserName.render(arg0, g);
	}
}
