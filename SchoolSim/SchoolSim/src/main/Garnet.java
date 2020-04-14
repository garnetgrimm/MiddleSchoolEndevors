package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import States.HallwayState;

public class Garnet {
	
	public static int[] CurrAnimList = new int[2];
	public static int TickNum = 0;
	public static int CurrAnimNum = 0;
	
	public static Image GarnetSprite = null;
	
	public static int x = 0;
	public static int y = 5;
	
	public static void init(GameContainer arg0) throws SlickException {
		GarnetSprite = new Image("Resources/Wave.png", false, Image.FILTER_NEAREST);
	}
	
	public static void playAnim(int animNum) {
		if(animNum == 1) {
			CurrAnimList[0] = 2;
			CurrAnimList[1] = 3;
		}
		else { CurrAnimList[0] = 0; CurrAnimList[1] = 0; }

		if(TickNum >= 10) {
			
			if(CurrAnimNum >= CurrAnimList.length) CurrAnimNum = 0;
			else CurrAnimNum++;
			
			TickNum = 0;
		}
		
		TickNum++;
	}
	
	public static void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
		try {
			x = CurrAnimList[CurrAnimNum] * 16;
		} catch(Exception e) {
			
		}
		
		GarnetSprite.getSubImage(x, 0, 16, 37).draw((275 / 2) - 50 + HallwayState.x, y);
	}
}
