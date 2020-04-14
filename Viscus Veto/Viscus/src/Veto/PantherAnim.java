package Veto;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import States.IntroState;

public class PantherAnim {

	public static SpriteSheet PLeft;
	public static SpriteSheet PRight;
	public static SpriteSheet PStand;
	
	public static Animation PLeftWalk;
	public static Animation PRightWalk;
	public static Animation PStanding;
	public static Animation CurAnim;
	
	public static void init() throws SlickException {
		PLeft = new SpriteSheet("res/PLeft.png", 8, 20);
		PRight = new SpriteSheet("res/PRight.png", 8, 20);
		PStand = new SpriteSheet("res/PStand.png", 8, 20);
		
		PStanding = new Animation(PStand, 200);
		PLeftWalk = new Animation(PLeft ,200);
		PRightWalk = new Animation(PRight ,200);
	}

	public static void update(int delta) {
		PLeftWalk.update(delta);
		PRightWalk.update(delta);
	}

	public static void render(GameContainer container, Graphics g) throws SlickException {
		if(IntroState.Dir == 1) PLeftWalk.draw(39, 29, 4f, 10f);
		else if(IntroState.Dir == -1) PRightWalk.draw(39, 29, 4f, 10f);
		else PStanding.draw(39, 29, 4f, 10f);
	}
	
}
