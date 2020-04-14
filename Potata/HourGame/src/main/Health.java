package main;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

public class Health {
	
	public static SpriteSheet HeartSheets;
	public static Animation Hearts;
	public static float P1Health, P2Health = 0;
	public static int HealthXOffset = 127;
	public static int HealthYOffset = 0;
	public static boolean Won = false;
	public static String Winner = "PLAYER 1 WINS";
	public static int ResetTimer = 0;
	public static TrueTypeFont font;
	
	
	public static void init() throws SlickException {
		HeartSheets = new SpriteSheet("res/HealthBar.png", 32, 8);
		Hearts = new Animation(HeartSheets, 100000000);
		Font awtFont = new Font("Agency", Font.BOLD, 50);
	    font = new TrueTypeFont(awtFont, false);
	}
	
	public static void update() {
		
		if(P2Health <= -30) { Winner = "YOU WIN"; Won = true; }
		if(P1Health <= -30) { Winner = "YOU LOSE"; Won = true; }
		
		if(Won) ResetTimer += 1;
		if(ResetTimer >= 100) { P1Health = 0; Won = false; ResetTimer = 0; }
		
		if(GameState.P2X <= GameState.P1X + 5 && GameState.P2X >= GameState.P1X - 5 && Armor.P2SwordChange) {
			if(!Armor.P2SwordDown) { P1Health -= 0.5; }
			else P1Health -= 0.1;
		}
		if(P1Health <= -30) P1Health = -30;
		
		if(P1Health > -7) Hearts.setCurrentFrame(0);
		else if(P1Health <= -7 && P1Health > -18) Hearts.setCurrentFrame(1);
		else if(P1Health <= -18 && P1Health > -28) Hearts.setCurrentFrame(2);
		else Hearts.setCurrentFrame(3);
	}
	
	public static void render(GameContainer container, Graphics g) {
		g.scale(0.5f,0.5f);
		g.setColor(new Color(0x75738E));
		g.fill(new Rectangle(HealthXOffset,1 + HealthYOffset,32,5));
		g.setColor(new Color(0x64637A));
		g.fill(new Rectangle(HealthXOffset,1 + HealthYOffset,32 + P2Health,5));
		g.setColor(new Color(0x082728));
		g.fill(new Rectangle(1 + HealthXOffset,2 + HealthYOffset,30,3));
		g.setColor(new Color(0xFF0017));
		g.fill(new Rectangle(1 + HealthXOffset,3 + HealthYOffset,30 + P1Health,1));
		Hearts.draw(HealthXOffset,HealthYOffset);
		
		g.scale(0.2f, 0.2f);
		g.setFont(font);
		g.setColor(new Color(ResetTimer, ResetTimer, ResetTimer));
		if(Won) g.drawString(Winner, 225, 100);
		
	}
}
