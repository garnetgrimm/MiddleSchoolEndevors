package lock;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class lockAnim {

	public static int currlock = 1;
	
	public static Image lock1;
	public static Image lock2;
	public static Image lock3;
	public static Image lock4;
	public static Image lock5;
	
	public static void init() throws SlickException {
		lock1 = new Image("lock/lock i.png", false, Image.FILTER_NEAREST);
		lock2 = new Image("lock/lock ii.png", false, Image.FILTER_NEAREST);
		lock3 = new Image("lock/lock iii.png", false, Image.FILTER_NEAREST);
		lock4 = new Image("lock/lock iv.png", false, Image.FILTER_NEAREST);
		lock5 = new Image("lock/lock v.png", false, Image.FILTER_NEAREST);
	}
	
	public static void update() {
		if(currlock > 5) currlock = 1;
	}
	
	public static void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		if(currlock == 1) lock1.draw();
		if(currlock == 2) lock2.draw();
		if(currlock == 3) lock3.draw();
		if(currlock == 4) lock4.draw();
		if(currlock == 5) lock5.draw();
	}
}
