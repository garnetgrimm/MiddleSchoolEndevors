package main;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class Badge {
	
	public String name = "";
	public String src = "";
	public String c1 = "";
	public String c2 = "";
	public String title = "";
	public String desc = "";
	
	static Font titleFont = null;
	static TrueTypeFont trueTypeTitleFont = null;
	static Font descFont = null;
	static TrueTypeFont trueTypeDescFont = null;
	static Font userFont = null;
	static TrueTypeFont trueTypeUserFont = null;
	static Image badge_icon = null;
	
	
	public Badge(String name, String src, String c1, String c2, String title, String desc) {
		this.name = name;
		this.src = src;
		this.c1 = Msc.getHexFromJSColor(c1);
		this.c2 = Msc.getHexFromJSColor(c2);
		this.title = title;
		this.desc = desc;
	}
	
	public static void init() throws SlickException {
		titleFont = StringHelper.getFont("PressStart2P-Regular.ttf", 15);
		trueTypeTitleFont = new TrueTypeFont(titleFont, true);
		
		descFont = StringHelper.getFont("PressStart2P-Regular.ttf", 12);
		trueTypeDescFont = new TrueTypeFont(descFont, true);
		
		userFont = StringHelper.getFont("PressStart2P-Regular.ttf", 18);
		trueTypeUserFont = new TrueTypeFont(userFont, true);
		
		badge_icon = new Image("images/Default_Badge.png", false, Image.FILTER_NEAREST);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		
		int borderHeight = 275;
		int borderLength = StringHelper.getWidth(descFont, desc) + 50;
		int borderX = (Main.Width / 2) - (borderLength / 2);
		int borderY = 50;
		
		g.setFont(trueTypeTitleFont);
		g.setColor(Msc.hexToColor(c1));
		g.fillRect(borderX, borderY, borderLength, borderHeight);
		g.setColor(Msc.hexToColor(c2));
		g.drawString(title,(Main.Width / 2) - (StringHelper.getWidth(titleFont, title) / 2), 64);
		g.setFont(trueTypeDescFont);
		g.drawString(desc, (Main.Width / 2) - (StringHelper.getWidth(descFont, desc) / 2), 84);
		g.scale(7, 7);
		badge_icon.draw(((Main.Width / 2) - (badge_icon.getWidth() * 7 / 2)) / 7, 18);
		g.drawRect(((Main.Width / 2) - (badge_icon.getWidth() * 7 / 2)) / 7, 18, badge_icon.getWidth(), badge_icon.getHeight());
		g.resetTransform();
		g.setLineWidth(4);
		g.drawRect(borderX, borderY, borderLength, borderHeight);
		g.setFont(trueTypeUserFont);
		g.setColor(Color.white);
		g.drawString("Congratulations " + name + "!", (Main.Width / 2) - (StringHelper.getWidth(userFont, "Congratulations " + name + "!") / 2), 275);
	}
}
