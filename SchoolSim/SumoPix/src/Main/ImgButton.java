package Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ImgButton {
	
	public Image label = null;
	public int width = 0;
	public int height = 0;
	
	public int x = 0;
	public int y = 0;
	
	public boolean active = true;
	
	public int scale = 0;
	
	public ImgButton(Image buttonLabel, int scale, int x, int y) {
		label = buttonLabel;
		width = label.getWidth();
		height = label.getHeight();
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isMouseOver(GameContainer arg0) {
		
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		
		//not workin too well
		if(MouseX > x && MouseX  < x + (width * scale) && MouseY > y && MouseY  < y + (height * scale)) return true;
		else return false;
	}
	
	public void draw(GameContainer arg0, Graphics g) {
		
		g.setColor(Color.darkGray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		label.draw(x,y);
		
		if(!active) {
			g.setColor(new Color(255, 255, 255, 127));
			g.fillRect(x, y, width, height);
		}
		
	}
}
