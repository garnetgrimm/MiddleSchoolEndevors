package main;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class OptionBoxTick {
	
	Font optionsFont = new Font("Verdana", Font.BOLD, 20);
	Font titleFont = new Font("Verdana", Font.BOLD, 32);
	TrueTypeFont ofont = new TrueTypeFont(optionsFont, true);
	TrueTypeFont tfont = new TrueTypeFont(titleFont, true);
	
	public String title = "";
	public int x = 0;
	public int y = 0;
	public String[] options = null;
	public int activeNum = 0;
	private int Width = 0;
	
	public OptionBoxTick(String title, int x, int y, String[] options) {
		this.title = title;
		this.x = x;
		this.y = y;
		this.options = options;
		
		BufferedImage img1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm1 = img1.getGraphics().getFontMetrics(titleFont);
		
		Width = fm1.stringWidth(title);
		
		for (int i = 0; i < options.length; i++) {
			BufferedImage img2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			FontMetrics fm2 = img2.getGraphics().getFontMetrics(optionsFont);
			int currWidth = fm2.stringWidth(options[i]);
			if(currWidth > Width) Width = currWidth;
		}
	}
	
	public void setActive(int optionNum) {
		activeNum = optionNum;
	}
	
	public boolean isMouseOver(GameContainer arg0, int optionNum) {
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		
		int startX = 15;
		int startY = 43 + (25 * optionNum);
		int width = 10;
		int height = 10;
		if(MouseX > x + startX && MouseX  < x + startX + width && MouseY > y + startY && MouseY  < y + startY + height) return true;
		else return false;
	}
	
	public void draw(GameContainer arg0, Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(x - 5, y, Width + 10, (options.length * 25) + 43);
		g.setColor(Color.white);
		g.setFont(tfont);
		g.drawString(title, x, y);
		g.setFont(ofont);
		for(int i = 0; i < options.length; i++) {
			g.drawRect(x + 15, y + 43 + (25 * i), 10, 10);
			g.setColor(Color.gray);
			if(i == activeNum) g.fillRect(x + 15, y + 43 + (25 * i), 10, 10);
			g.setColor(Color.white);
			g.drawString(options[i], x + 40, y + 35 + (25 * i));
		}
	}
	
}
