package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import networking.Server;

public class Chars {

	public static SpriteSheet RawTatoLeft1;
	public static SpriteSheet RawTatoLeft2;
	public static SpriteSheet RawTatoRight1;
	public static SpriteSheet RawTatoRight2;

	public static Animation Tato1R;
	public static Animation Tato2R;
	public static Animation Tato1L;
	public static Animation Tato2L;
	
	public static int P2DrawX = 39 - (GameState.P1X / 5) + GameState.P2X / 5;
	
	public static void init() throws SlickException {
		
		RawTatoLeft1 = new SpriteSheet("res/potataL.png", 36, 21);
		RawTatoLeft2 = new SpriteSheet("res/potataL.png", 36, 21);
		RawTatoRight1 = new SpriteSheet("res/potataR.png", 36, 21);
		RawTatoRight2 = new SpriteSheet("res/potataR.png", 36, 21);
		
		Tato1R = new Animation(RawTatoRight1, 200);
		Tato2R = new Animation(RawTatoRight2 ,200);
		Tato1L = new Animation(RawTatoLeft1, 200);
		Tato2L = new Animation(RawTatoLeft2 ,200);
	}

	public static void update(int delta) {
		P2DrawX = -GameState.P1X + GameState.P2X + 190;
	}

	public static void render(GameContainer container, Graphics g) throws SlickException {
		
		g.scale(0.2f, 0.2f);
		g.scale(1, 1);
				
		if (GameState.P1Dir == 1 || GameState.P1Dir == 0)
			Armor.P1Sword.draw(220, 200, 0.12f);
		if (GameState.P1Dir == -1)
			Armor.P1Sword1.draw(185, 200, 0.12f);
		
		if(Server.Started) {
			if (GameState.P2Dir == -1 || GameState.P2Dir == 0)
				Armor.P2Sword.draw(Chars.P2DrawX - 190 + 185, 200, 0.12f);
			if (GameState.P2Dir == 1)
				Armor.P2Sword1.draw(Chars.P2DrawX - 190 + 220, 200, 0.12f);
		}
		
		if(GameState.P1Dir == -1) Tato1R.draw(190, 220);
		else Tato1L.draw(190, 220);
		
		if(Server.Started) {
			if(GameState.P2Dir == -1) Tato2R.draw(P2DrawX, 220);
			else Tato1L.draw(P2DrawX, 220);
		}
		
	}
	
}
