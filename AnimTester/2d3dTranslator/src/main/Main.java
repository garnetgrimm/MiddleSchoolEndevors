package main;

import java.awt.Font;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import States.StartUpState;

public class Main extends StateBasedGame {
	
	public static final int WIDTH = 840;
	public static final int HEIGHT = 630;
	//defined in startupstate
	public static Font font;
	public static TrueTypeFont ttf;
	
	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SlickException, IOException {
		AppGameContainer app = new AppGameContainer(new Main("PixelTo3D Converter"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.setIcon("Resources/icon.png");
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.start();
	}
	
    @Override
    public boolean closeRequested()
    {
    	System.exit(0); // Use this if you want to quit the app.
    	return false;
    }

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new StartUpState());
		this.enterState(0);
		
	}
}