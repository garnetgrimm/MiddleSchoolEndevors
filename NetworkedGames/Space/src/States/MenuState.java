package States;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Main;
import main.StringHelper;

public class MenuState extends BasicGameState {	
	String title = "FERN";
	
	Font startFont = null;
	TrueTypeFont trueTypeStartFont = null;
	
	Font titleFont = null;
	TrueTypeFont trueTypeTitleFont = null;
	
	int titlex = 0;
	int titley = 0;
	
	Image Background = null;
	
	static int cftime = 0;
	static boolean cfinc;
	
	String startStr = "";
	
	static float scale = 0;
	static int startDelay = 0;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Background = new Image("Resources/terminal.png", false, Image.FILTER_NEAREST);
		startFont = StringHelper.getFont("PressStart2P-Regular.ttf", 10);
		trueTypeStartFont = new TrueTypeFont(startFont, true);
		titleFont = StringHelper.getFont("PressStart2P-Regular.ttf", 40);
		//titleFont = StringHelper.getFont("RuthlessDrippinONE.ttf", 40);
		trueTypeTitleFont = new TrueTypeFont(titleFont, true);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if(arg0.getInput().isKeyDown(Input.KEY_ENTER)) { arg1.enterState(1); }
		boolean Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		
		if(startStr.length() < 5) {
			for(int i = 0; i < 110; i++) {
				if(arg0.getInput().isKeyPressed(i)) { startStr += nextLetter(); }
			}
		} else if(startDelay <= 25) {
			startDelay++;
		}
		
		if(startDelay >= 25 && scale < 8) {
			scale += 0.3f;
		}
	}
	
	public String nextLetter() {
		switch (startStr.length()) {
	        case 0:  return "S";
	        case 1: return "T";
	        case 2: return "A";
	        case 3: return "R";
	        case 4: return "T";
	        default: return "";
		}
	}
	
	public boolean getCurserFlash() {		
		if(cftime >= 25) cfinc = true;
		if(cftime <= -25) { cfinc = false; }
		
		if(cfinc) cftime--;
		else cftime++;
		
		return cfinc;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.scale(6 + scale, 6 + scale);
		g.setFont(trueTypeStartFont);
		Background.draw(0 - (scale * 6.5f), 0 - (scale * 5.5f));
		g.resetTransform();
		g.setColor(Color.black);
		if(startDelay < 15) {
			if(getCurserFlash()) g.drawString(">" + startStr, 460, 300);
			else g.drawString(">" + startStr + "_", 460, 300);	
		}
		g.setFont(trueTypeTitleFont);
		g.drawString(title, 550 + (scale * 50), 100);
	}

	@Override
	public int getID() {
		return 0;
	}
	
}
