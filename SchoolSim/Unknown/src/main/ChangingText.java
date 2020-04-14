package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ChangingText {
	
	public int x = 0;
	public int y = 0; 
	public int changeNum = 0;
	public String[] textOrder = null;
	
	int firstTextNum = 0;
	boolean justChanged = false;
	float CharX = 0;
	boolean changeOnGreater = false;
	
	public ChangingText(String[] textOrder, boolean changeOnGreater, int changeNum, int x, int y) {
		changeNum *= 10;
		x *= 10;
		y *= 10;
		
		this.textOrder = textOrder;
		this.changeNum = changeNum;
		this.changeOnGreater = changeOnGreater;
		this.x = x;
		this.y = y;
	}
	
	public void update(float CharX) {
		CharX *= -10;
		
		this.CharX = CharX;
	
		if(changeOnGreater) {
			if(CharX < -changeNum && !justChanged && firstTextNum < textOrder.length - 1) { firstTextNum++; justChanged = true; }
			if(CharX > -changeNum && justChanged) justChanged = false;
		} else {
			if(CharX < -changeNum && !justChanged && firstTextNum < textOrder.length - 1) { justChanged = true; }
			if(CharX > -changeNum && justChanged) { justChanged = false; firstTextNum++; }
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawString(textOrder[firstTextNum], CharX + x, y);
	}
}
