package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame menu) throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame menu, int delta) throws SlickException {
		if (container.getInput().isKeyDown(Input.KEY_K)) { 	menu.enterState(1, new FadeOutTransition(), new FadeInTransition()); }
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		g.drawString("Viscus", 50, 50);
	}

	@Override
	public int getID() {
		return 0;
	}

}
