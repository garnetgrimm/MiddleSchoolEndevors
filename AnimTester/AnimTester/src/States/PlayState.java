package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Anim;
import main.Buttons;
import main.InfoReader;
import main.Main;

public class PlayState extends BasicGameState {

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		Buttons.init(arg0);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
		Anim.callAnim(Main.CurrAnim);
		Buttons.update(arg0, arg2, arg1);
		
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		InfoReader.render(arg0, g);
		Buttons.render(arg0, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
}
