package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MathGoblin {

	public static Image monster = null;
	
	public static Image arm1 = null;
	public static Image body = null;
	public static Image arm2 = null;
	
	public static int[] armRot = { 0, 0 };
	public static boolean armInc = false;
	
	public static int x = 50;
	public static int y = 9;
	
	public static int[] CurrAnimList = { 2,3,4 };
	public static int CurrAnimNum = 0;
	public static int TickNum = 0;
	
	public static int Health = 100;
	
	public static int LastAnimNum = 0;
	
	public static void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		monster = new Image("Resources/MathGoblin.png", false, Image.FILTER_NEAREST);
		
		arm1 = monster.getSubImage(37, 0, 37, 54).getFlippedCopy(false, false);
		body = monster.getSubImage(74, 0, 37, 54).getFlippedCopy(false, false);
		arm2 = monster.getSubImage(0, 0, 37, 54).getFlippedCopy(false, false);
	}

	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
		if(armRot[0] >= 30) armInc = false;
		if(armRot[0] <= -30) armInc = true;
		
		if(armInc) armRot[0]++;
		else armRot[0]--;

		armRot[1] = -armRot[0];
		
		if(Health <= 0) Health = 0;
		if(Health == 0) {
			Main.EllieX = 2;
			arg1.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		
	}
	
	public static void playAnim(int AnimNum) {
		
		if(AnimNum == 0) { CurrAnimList[0] = 2; CurrAnimList[1] = 2; CurrAnimList[2] = 2; }
		if(AnimNum == 1) { Health--; CurrAnimList[0] = 2; CurrAnimList[1] = 3; CurrAnimList[2] = 4; }
		
		if(TickNum >= 8) {
			
			if(AnimNum == LastAnimNum) {
				if(CurrAnimNum >= CurrAnimList.length - 1) CurrAnimNum = CurrAnimList.length - 1;
				else CurrAnimNum++;
			} else {
				CurrAnimNum = 0;
			}
			
			TickNum = 0;
		}
		
		TickNum++;
		LastAnimNum = AnimNum;
	}
	
	public static void checkBounds(int x1) {
		if(x1 > 50) playAnim(1);
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		arm1.setCenterOfRotation(21, 11);
		arm2.setCenterOfRotation(21, 11);
		
		//System.out.println(arm1.getCenterOfRotationX() + " " + arm1.getCenterOfRotationY());

		arm1.setRotation(armRot[0]);
		arm2.setRotation(armRot[1]);
		
		body = monster.getSubImage(37 * CurrAnimList[CurrAnimNum], 0, 37, 54).getFlippedCopy(false, false);
		
		arm1.draw(x,y);
		body.draw(x,y);
		arm2.draw(x,y);
		
		//g.drawRect(0, 0, arm1.getCenterOfRotationX(), arm1.getCenterOfRotationY());
	}
	
}
