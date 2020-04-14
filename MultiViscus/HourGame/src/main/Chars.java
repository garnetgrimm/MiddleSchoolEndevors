package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import networking.Server;

public class Chars {

	public static SpriteSheet PLeft;
	public static SpriteSheet PRight;
	public static SpriteSheet PStand;
	
	public static SpriteSheet P1Left;
	public static SpriteSheet P1Right;
	public static SpriteSheet P1Stand;
	
	public static Animation PLeftWalk;
	public static Animation PRightWalk;
	public static Animation PStanding;
	
	public static Animation P1LeftWalk;
	public static Animation P1RightWalk;
	public static Animation P1Standing;
	
	public static int P2DrawX = 39 - (GameState.P1X / 5) + GameState.P2X / 5;
	
	public static void init() throws SlickException {
		P1Left = new SpriteSheet("res/foxiL.png", 12, 32);
		P1Right = new SpriteSheet("res/foxiR.png", 12, 32);
		P1Stand = new SpriteSheet("res/foxi.png", 12, 32);
		
		PLeft = new SpriteSheet("res/PLeft.png", 8, 20);
		PRight = new SpriteSheet("res/PRight.png", 8, 20);
		PStand = new SpriteSheet("res/PStand.png", 8, 20);

		PStanding = new Animation(PStand, 200);
		PLeftWalk = new Animation(PLeft ,200);
		PRightWalk = new Animation(PRight ,200);
		
		P1Standing = new Animation(P1Stand, 200);
		P1LeftWalk = new Animation(P1Left ,200);
		P1RightWalk = new Animation(P1Right ,200);
	}

	public static void update(int delta) {
		
		P2DrawX = 39 - GameState.P1X + GameState.P2X;
		
		P1LeftWalk.update(delta);
		P1RightWalk.update(delta);	
		
		PLeftWalk.update(delta);
		PRightWalk.update(delta);
	}

	public static void render(GameContainer container, Graphics g) throws SlickException {
		
		if(GameState.P1Dir == -1) PLeftWalk.draw(39, 40, 4f, 10f);
		else if (GameState.P1Dir == 1) PRightWalk.draw(39, 40, 4f, 10f);
		else PStanding.draw(39, 40, 4f, 10f);
	
		if(Server.Started) {
		if(GameState.P2Dir == -1) P1LeftWalk.draw(P2DrawX, 40, 4f, 10f);
		else if (GameState.P2Dir == 1) P1RightWalk.draw(P2DrawX, 40, 4f, 10f);
		else P1Standing.draw(P2DrawX, 40, 4f, 10f);
		}
	}
	
}
