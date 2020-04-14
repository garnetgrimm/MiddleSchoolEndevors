package StartupState;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartupState extends BasicGameState {

	RandomHexGen matrix = new RandomHexGen(110, 20, 26);
	
	Image title;
	boolean rendKey = false;
	int KeyTime = 0;
	int MaxKeyFlash = 20;
	
	@Override
	public void init(GameContainer container, StateBasedGame menu) throws SlickException {
		title = new Image("res/bbackslash.png", false, Image.FILTER_NEAREST);
	}

	@Override
	public void update(GameContainer container, StateBasedGame menu, int delta) throws SlickException {
		for(int i = 0; i < 100; i++) {
			if(container.getInput().isKeyPressed(i)) { menu.enterState(1); }
		}
		
		if(!rendKey) KeyTime--;
		if(rendKey) KeyTime++;
		
		if(KeyTime > MaxKeyFlash) rendKey = false;
		if(KeyTime < -MaxKeyFlash) rendKey = true;
	
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {	
		g.setBackground(new Color(0x000000));
		g.setColor(new Color(0x1DE228));
		if(rendKey) g.drawString("Press any key to continue..", 290, 450);
		matrix.render(container, menu, g);
		g.scale(6, 6);
		title.draw(30,5);
	} 
	
	@Override
	public int getID() {
		return 0;
	}

}
