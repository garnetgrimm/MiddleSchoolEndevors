package Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import startupComponents.Header;
import startupComponents.ShipCreator;
import startupComponents.Toolbar;
import startupComponents.UsersList;

public class main extends BasicGameState {
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Header.init(arg0, arg1);
		UsersList.init(arg0, arg1);
		ShipCreator.init(arg0, arg1);
		Toolbar.init(arg0, arg1);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Header.update(arg0, arg1, arg2);
		UsersList.update(arg0, arg1, arg2);
		ShipCreator.update(arg0, arg1, arg2);
		Toolbar.update(arg0, arg1, arg2);
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		Header.render(arg0, arg1, g);
		UsersList.render(arg0, arg1, g);
		ShipCreator.render(arg0, arg1, g);
		Toolbar.render(arg0, arg1, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
