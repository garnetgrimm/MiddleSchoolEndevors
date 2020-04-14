package main;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameState.GameState;
import Modding.ModReader;
import Msc.ConfReader;
import PasswordState.PasswordState;
import StartupState.StartupState;

public class Main extends StateBasedGame {

	public static boolean sendRequest = false;
	public static int requestTime = 0;
	
	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void update() {
		if(sendRequest) requestTime++;
		if(requestTime > 30) { sendRequest = false; requestTime = 0; } 
		ModReader.updateMods();
	}
	
	public static void main(String[] args) throws SlickException, IOException {
		ConfReader.init();
		ModReader.init();
		AppGameContainer app = new AppGameContainer(new Main("B@CK$1@$H"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.setIcon("res/icon.png");
		app.setDisplayMode(840, 630, false);
		app.start();
	}
	
    @Override
    public boolean closeRequested()
    {
    	System.exit(0); // Use this if you want to quit the app.
    	return false;
    }

	@Override
	public void initStatesList(GameContainer arg1) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new GameState());
		this.addState(new PasswordState());
		this.addState(new StartupState());

		this.enterState(0);
		
	}
}