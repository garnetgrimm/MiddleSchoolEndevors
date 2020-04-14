package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Armor {

	public static Image P1Sword, P1Sword1, P2Sword, P2Sword1;
	public static boolean P1SwordChange, P2SwordChange, P1SwordDown, P2SwordDown = false;
	public static float SwordRot, P1RotInt, P2RotInt = 0f;

	public static void init() throws SlickException {
		P1Sword1 = new Image("res/RaperLeft.png");
		P1Sword = new Image("res/RaperRight.png");

		P2Sword = new Image("res/RaperLeft.png");
		P2Sword1 = new Image("res/RaperRight.png");
	}

	public static void update(int delta) {
		P1RotInt = P1Sword.getRotation();

		if (P1SwordChange)
			SwordRot = 10f;
		else
			SwordRot = -10f;

		if (P1RotInt >= 10) {
			P1SwordDown = true;
		} else
			P1SwordDown = false;

		if (P1RotInt >= 90 && P1SwordChange)
			SwordRot = 0;
		if (P1RotInt <= 0 && !P1SwordChange)
			SwordRot = 0;
	}
	
	public static void render(GameContainer container, Graphics g) {
		P1Sword1.setCenterOfRotation(5, 25);
		P1Sword1.rotate(-SwordRot);

		P1Sword.setCenterOfRotation(10, 25);
		P1Sword.rotate(SwordRot);
		
	}

}
