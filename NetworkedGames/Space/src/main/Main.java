package main;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import States.*;

public class Main extends StateBasedGame {

	public static final int Width = 1366;
	public static final int Height = 768;
	
	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SlickException, IOException {
		AppGameContainer app = new AppGameContainer(new Main("Mehhhh"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		//app.setIcon("Resources/Logo.png");
		app.setDisplayMode(Width, Height, false);
		app.setFullscreen(true);
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
		this.addState(new MenuState());
		this.addState(new GameState());
		this.enterState(0);
		
	}

}