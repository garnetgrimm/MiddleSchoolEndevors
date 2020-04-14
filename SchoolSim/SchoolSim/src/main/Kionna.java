package main;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Kionna {
	
	public static int[] CurrAnimList = new int[2];
	public static int TickNum = 0;
	public static int CurrAnimNum = 0;
	
	public static Image KionnaSprite = null;
	
	public static int x = 0;
	public static int y = 8;
	
	private static Random random = new Random();
	//random tick for when she swings her feet
	public static int swingNum = random.nextInt(64);
	public static int randTick = 0;
	
	public static void init(GameContainer arg0) throws SlickException {
		KionnaSprite = new Image("Resources/KionnaSwing.png", false, Image.FILTER_NEAREST);
	}
	
	public static void update() {
		randTick++;
		if(randTick >= swingNum) {
			if(CurrAnimList[0] == 0 && CurrAnimList[1] == 0) {
				playAnim(1);
			} else {
				playAnim(0);
			}
			swingNum = random.nextInt(100);
			randTick = 0;
		}
		
		if(TickNum >= 8) {
			
			if(CurrAnimNum >= CurrAnimList.length) CurrAnimNum = 0;
			else CurrAnimNum++;
			
			TickNum = 0;
		}
		
		TickNum++;
	}
	
	public static void playAnim(int animNum) {
		if(animNum == 1) {
			CurrAnimList[0] = 1;
			CurrAnimList[1] = 2;
		}
		else { CurrAnimList[0] = 0; CurrAnimList[1] = 0; }
	}
	
	public static void render(GameContainer arg0, Graphics g) throws SlickException {		
		try {
			x = CurrAnimList[CurrAnimNum] * 21;
		} catch(Exception e) {
			
		}
		
		KionnaSprite.getSubImage(x, 0, 21, 34).getFlippedCopy(true, false).draw(55, y);
	}
}
