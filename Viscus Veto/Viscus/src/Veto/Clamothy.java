package Veto;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Clamothy {

	public static boolean Speaking = false;

	public static SpriteSheet Clamothy;
	
	public static Animation Talking;
	public static Animation Idleing;
	
	public static void init() throws SlickException {
		Clamothy = new SpriteSheet("res/ClamSprite.png", 31, 44);
				
		Talking = new Animation(Clamothy, 200);
		Idleing = new Animation(Clamothy, 1000);
		
		Idleing.setLooping(false);

	}

	public static void update(int delta) {
		Talking.update(delta);
		Idleing.setCurrentFrame(0);
	}

	public static void DrawSprite() {
	}
	
}
