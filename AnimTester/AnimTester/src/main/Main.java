package main;

import java.awt.Font;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import States.AnimationEditor;
import States.PlayState;
import States.StartUpState;
import States.WorkShopState;

public class Main extends StateBasedGame {
	
	public static Font font;
	public static TrueTypeFont ttf;
	
	public static int XTotalSize = 0;
	public static int YTotalSize = 0;
	public static int XSize = 0;
	public static int YSize = 0;
	public static String CurrAnim = "";
	public static float Speed = 3f;
	
	public static int XNumOfSprites = 0;
	public static int YNumOfSprites = 0;
	
	public static int CurrX = 0;
	public static int CurrY = 0;
	public static int Scale = 10;
	
	public static float MovSpeed = 3;
	
	public static int R = 0;
	public static int G = 0;
	public static int B = 0;
	
	public static Color MyColor = new Color(Color.black);
	public static Color MyColorRev = new Color(Color.black);
	
	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SlickException, IOException {
		AppGameContainer app = new AppGameContainer(new Main("AnimTester V0.1"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.setIcon("Resources/icon.png");
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
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new StartUpState());
		this.addState(new WorkShopState());
		this.addState(new AnimationEditor());
		this.addState(new PlayState());
		
		this.enterState(0);
		
	}
}