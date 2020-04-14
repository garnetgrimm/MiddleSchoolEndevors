package States;

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

public class GameState extends BasicGameState {
	
	public static Image Char = null;
	static int animTickNum = 0;
	static int animNum = 0;
	static boolean right = false;
	static float x = 0;
	static int[] spaceAnim = { 0,1,0,2 };
	
	public static String[] firstText = {
		"Hi.",
		"Welcome back.",
		"What do you even see in that other text??",
	};
		
	public static String[] secondText = {
			"I see youve found our game.",
			"Stop leaving me."
	};
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}
	
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		boolean walking = false;
		if(arg0.getInput().isKeyDown(Input.KEY_A)) { walking = true; x -= 0.5f; right = true; }
		if(arg0.getInput().isKeyDown(Input.KEY_D)) { walking = true; x += 0.5f; right = false; }
		if(walking) animate();
		else animNum = 0;
		
	}
	
	public static void animate() {
		animTickNum++;
		if(animTickNum >= 10) { animTickNum = 0; animNum++; }
		if(animNum > 3) animNum = 0;
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
