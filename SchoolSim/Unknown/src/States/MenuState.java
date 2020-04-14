package States;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Main;
import main.StringHelper;

public class MenuState extends BasicGameState {	
	String title = "WHO NEEDS TITLES ANYWAY?";
	String avg = "Press Enter To Continue";
	
	Font titleFont = null;
	TrueTypeFont trueTypeTitleFont = null;
	
	Font avgFont = null;
	TrueTypeFont trueTypeAvgFont = null;
	
	int titlex = 0;
	int titley = 0;

	int avgx = 0;
	int avgy = 0;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		titleFont = StringHelper.getFont("PressStart2P-Regular.ttf", 24);
		trueTypeTitleFont = new TrueTypeFont(titleFont, true);
		
		avgFont = StringHelper.getFont("PressStart2P-Regular.ttf", 15);
		trueTypeAvgFont = new TrueTypeFont(avgFont, true);
		
		titlex = (Main.Width / 2) - (StringHelper.getWidth(titleFont, title) / 2);
		titley = 100;
		
		avgx = (Main.Width / 2) - (StringHelper.getWidth(avgFont, avg) / 2);
		avgy = 500;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(trueTypeTitleFont);
		g.drawString(title, titlex, titley);
		g.setFont(trueTypeAvgFont);
		g.drawString(avg, avgx, avgy);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		if(arg0.getInput().isKeyDown(Input.KEY_ENTER)) { arg1.enterState(1); }
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
