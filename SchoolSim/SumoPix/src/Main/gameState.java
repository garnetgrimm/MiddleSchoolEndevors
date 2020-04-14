package Main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import startupComponents.ShipCreator;

public class gameState extends BasicGameState {

	public static Image back = null;
	public static List<String> Player1 = new ArrayList<String>();
	public static List<String> Player2 = new ArrayList<String>();
	
	public static int playerNum = 0;
	
	public static int client1 = -1;
	public static int client2 = -1;
	
	public static int myX = 0;
	public static int myY = 0;
	
	public static int p1x = 13;
	public static int p1y = 35;
	
	public static int p2x = (init.Width / 10) - 13 - ShipCreator.maxPixels;
	public static int p2y = 35;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		back = new Image("res/Back.png", false, Image.FILTER_NEAREST);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		boolean A = arg0.getInput().isKeyDown(Input.KEY_A);
		boolean D = arg0.getInput().isKeyDown(Input.KEY_D);
		
		if(A && init.clientNum == client1) p1x--;
		else if(A && init.clientNum == client2)p2x--;
		if(D && init.clientNum == client1) p1x++;
		else if(D && init.clientNum == client2)p2x++;
		
		if(init.clientNum == client1) { playerNum = 1; myX = p1x; }
		else if(init.clientNum == client2) { playerNum = 2; myX = p2x; }
		
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.scale(10,10);
		back.draw();
		for(int i = 0; i < Player1.size(); i++) {
			String[] vals = Player1.get(i).split(":");
			g.setColor(Color.decode(vals[2]));
			g.fillRect(Integer.parseInt(vals[0]) + p1x, Integer.parseInt(vals[1]) + p1y, 1, 1);
		}
		for(int i = 0; i < Player2.size(); i++) {
			String[] vals = Player2.get(i).split(":");
			g.setColor(Color.decode(vals[2]));
			g.fillRect((-1 * Integer.parseInt(vals[0])) + p2x, Integer.parseInt(vals[1]) + p2y, 1, 1);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
