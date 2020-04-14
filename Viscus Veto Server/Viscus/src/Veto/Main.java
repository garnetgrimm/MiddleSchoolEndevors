package Veto;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import States.IntroState;
import States.MenuState;

public class Main extends StateBasedGame {

	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Viscus Veto"));
		//app.setShowFPS(false);
		app.setTargetFrameRate(240);
		app.setIcon("res/Icon.png");
		app.setDisplayMode(800, 600, false);
		app.start();
	}
	
    @Override
    public boolean closeRequested()
    {
    	try {
			Progress.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.exit(0); // Use this if you want to quit the app.
    	return false;
    }

	@Override
	public void initStatesList(GameContainer arg1) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new IntroState());
		this.addState(new MenuState());
		
		this.enterState(0);
		
	}
}