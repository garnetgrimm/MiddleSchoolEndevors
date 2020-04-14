package main;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Button {
	
	public String label = "";
	public int width = 0;
	public int height = 0;
	
	public int x = 0;
	public int y = 0;
	
	Font font = new Font("Verdana", Font.BOLD, 32);
	TrueTypeFont ttf = new TrueTypeFont(font, true);
	BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	public FontMetrics fm = img.getGraphics().getFontMetrics(font);
	
	public Button(String buttonLabel, int x, int y) {
		label = buttonLabel;
		width = fm.stringWidth(label);
		height = fm.getHeight();
		this.x = x;
		this.y = y;
	}
	
	public boolean isMouseOver(GameContainer arg0) {
		
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		
		if(MouseX > x && MouseX  < x + width && MouseY > y && MouseY  < y + height) return true;
		else return false;
	}
	
	public void draw(GameContainer arg0, Graphics g) {
		
		g.setFont(ttf);
		
		g.setColor(Color.darkGray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawString(label, x, y);
	}
}
