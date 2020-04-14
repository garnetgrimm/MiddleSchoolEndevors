package main;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QueenKionna {
	
	public static int[] CurrAnimList = new int[2];
	public static int TickNum = 0;
	public static int CurrAnimNum = 0;
	
	public static Image KionnaSprite = null;
	
	public static int x = 0;
	public static int y = 1;
	
	private static Random random = new Random();
	public static int ChooseNum = random.nextInt(2);
	public static int ChangeNum = random.nextInt(64);
	public static int randTick = 0;
	
	public static void init(GameContainer arg0) throws SlickException {
		KionnaSprite = new Image("Resources/QueenKionna.png", false, Image.FILTER_NEAREST);
	}
	
	public static void update() {
		randTick++;
		if(randTick >= ChangeNum) {
			if(CurrAnimList[0] == 0 && CurrAnimList[1] == 0) {
				playAnim(ChooseNum + 1);
			} else {
				playAnim(0);
			}
			ChangeNum = random.nextInt(100);
			ChooseNum = random.nextInt(2);
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
		else if(animNum == 2) {
			CurrAnimList[0] = 3; CurrAnimList[1] = 4;
		}
		else { CurrAnimList[0] = 0; CurrAnimList[1] = 0; }
	}
	
	public static void render(GameContainer arg0, Graphics g) throws SlickException {		
		try {
			x = CurrAnimList[CurrAnimNum] * 20;
		} catch(Exception e) {
			
		}
		
		KionnaSprite.getSubImage(x, 0, 20, 32).getFlippedCopy(false, false).draw(32, y);
	}
}
