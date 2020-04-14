package Main;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class init extends StateBasedGame {

	public static int clientNum = 0;
	
	public static final int Width = 840;
	public static final int Height = 630;
	
	public static int EllieX = 35;
	public static int EllieY = 14;
	
	public init(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void startServer() {
		(new Thread(new Server())).start();
	}
	
	public static void main(String[] args) throws SlickException, IOException {
		AppGameContainer app = new AppGameContainer(new init("Mehhhh"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		//app.setIcon("Resources/Logo.png");
		app.setDisplayMode(Width, Height, false);
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
		this.addState(new main());
		this.addState(new gameState());
		this.enterState(0);
		
	}

}