package States;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.AJAX;
import main.Badge;

public class ConvertState extends BasicGameState implements MusicListener {
	
	public static List<Badge> badges = new ArrayList<Badge>();
	public static Music BGM = null;	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		(new Thread(new AJAX())).start();
		Badge.init();
		
	    BGM = new Music("Resources/Super_Mario_Bros.ogg");
	    BGM.addListener(this);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		//boolean Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		if(arg0.getInput().isKeyDown(Input.KEY_ESCAPE)) System.exit(0);
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		if(badges.size() > 0) badges.get(0).render(arg0, arg1, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void musicEnded(Music arg0) {
		// TODO Auto-generated method stub
		badges.remove(0);
		if(badges.size() > 0) BGM.play();
	}

	@Override
	public void musicSwapped(Music arg0, Music arg1) {
		// TODO Auto-generated method stub
		
	}
}
