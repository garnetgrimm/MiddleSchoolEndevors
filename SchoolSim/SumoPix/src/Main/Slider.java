package Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Slider {
	
	String label = null;
	int x = 0;
	int y = 0;
	int highest = 0;
	Color c = null;
	
	boolean grabbing = false;
	int currx = 0;
	
	public Slider(Color c, String label, int x, int y, int highest) {
		this.label = label;
		this.x = x;
		this.y = y;
		this.highest = highest;
		this.c = c;
	}
	
	public boolean isMouseOver(GameContainer arg0) {
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		
		if(MouseX > x + currx && MouseX  < x + currx + 10 && MouseY > y - 2 && MouseY  < y + 15) return true;
		else return false;
	}
	
	public void update(GameContainer arg0) {
		boolean Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		int MouseX = arg0.getInput().getMouseX();

		if(isMouseOver(arg0) && Click) { grabbing = true; }
		if(!Click) grabbing = false;
		
		if(grabbing) currx = MouseX - x;
		
		if(currx >= (highest * 10) - 10) currx = (highest * 10) - 10;
		if(currx <= 0) currx = 0;
	}
	
	public int getVal() {
		return currx / 10;
	}
	
	public void draw(GameContainer arg0, Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, highest * 10, 10);
		g.setColor(Color.black);
		g.fillRect(x + currx, y - 2, 10, 15);
		g.drawString(label, x, y - 20);
	}
	
}
