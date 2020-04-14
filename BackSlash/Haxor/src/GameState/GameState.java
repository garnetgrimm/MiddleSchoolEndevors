package GameState;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import PasswordState.CheckReadied;
import lock.lockAnim;
import main.Main;

public class GameState extends BasicGameState {
	
	public static String drawnPassword = "";
	
	@Override
	public void init(GameContainer container, StateBasedGame menu) throws SlickException {
		ConsoleType.init(container, menu);
		lockAnim.init();
		Attacks.init();
		Icons.init();
	}

	@Override
	public void update(GameContainer container, StateBasedGame menu, int delta) throws SlickException {
		ConsoleType.update(container, menu, delta);
		lockAnim.update();
		Main.update();
		Attacks.update();
		Icons.update(container, menu, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		g.setBackground(new Color(0x000000));
		g.drawString("YOUR PASSWORD: " + CheckReadied.password, 650, 7);
		g.drawString("ENEMY PASSWORD: " + drawnPassword, 350, 7);
		//g.drawRect(485, 5, 64, 32);
		g.drawRect(491, 5, 42, 24);
		g.drawRect(782, 5, 42, 24);
		
		g.drawString("root@unknown>:~$ ", 5, 50);
		Attacks.render(container, menu, g);
		
		ConsoleType.render(container, menu, g);
		
		g.scale(2, 2);
		Icons.render(container, menu, g);
		lockAnim.render(container, menu, g);
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
