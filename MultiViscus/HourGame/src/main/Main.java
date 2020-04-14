package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Viscus Veto"));
		//app.setShowFPS(false);
		app.setTargetFrameRate(60);
		//app.setIcon("res/Icon.png");
		app.setDisplayMode(800, 600, false);
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
		
		this.enterState(0);
		
	}
}