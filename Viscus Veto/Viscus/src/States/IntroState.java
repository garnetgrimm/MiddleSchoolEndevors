package States;

import java.awt.Font;
import java.io.IOException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Veto.Clamothy;
import Veto.Client;
import Veto.Dialogue;
import Veto.PantherAnim;
import Veto.Progress;

public class IntroState extends BasicGameState {

	private TiledMap map;
	public static int x = -1000;
	public static int y = -140;
	public static int Dir = 0;
	public static int WalkSpeed = 2;
	
	public static int LogNum = 0;
	
	public static TrueTypeFont font;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		
		Font awtFont = new Font("Agency", Font.BOLD, 25);
	    font = new TrueTypeFont(awtFont, false);
	   
	    map = new TiledMap("res/Level1.tmx");
		PantherAnim.init();
		Clamothy.init();
		Dialogue.init();
		
		try {
			Progress.access();
			x = Progress.Data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Client.Send();
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		
		if (container.getInput().isKeyDown(Input.KEY_LCONTROL)) { WalkSpeed = 4; }
		else WalkSpeed = 2;
		
		if(!Dialogue.Ask) {
			if (container.getInput().isKeyDown(Input.KEY_A) && x < 0) { Dir = 1; x += WalkSpeed; }
			else if (container.getInput().isKeyDown(Input.KEY_D) && x > -840 * 10) { Dir = -1; x -= WalkSpeed; }
			else { Dir = 0; }
			if(container.getInput().isKeyPressed(Input.KEY_S)) { try { Progress.save(); } catch (IOException e) { e.printStackTrace();} }
		}
		
		if(Dialogue.Ask) {
			if (container.getInput().isKeyPressed(Input.KEY_A)) { Dialogue.Selection--; }
			if (container.getInput().isKeyPressed(Input.KEY_D)) { Dialogue.Selection++; }
			if (container.getInput().isKeyPressed(Input.KEY_ENTER)) { Dialogue.Choose(); LogNum++; Dialogue.Text(LogNum); }
		}
		
		if (container.getInput().isKeyPressed(Input.KEY_T) && !Dialogue.Ask) { if(Dialogue.InRange) { Dialogue.OpenTimer = 0; Dialogue.Opened = true; } LogNum += 1; Dialogue.Text(LogNum); }
		PantherAnim.update(delta);
		Clamothy.update(delta);
		Dialogue.update();
	}
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale(10, 10);
		map.render(x /10, y);

		PantherAnim.render(container, g);
		
		if(Clamothy.Speaking)Clamothy.Talking.draw(x / 10 + 20, y / 10 + 31, 15.5f, 22f);
		else Clamothy.Idleing.draw(x / 10 + 20, y / 10 + 31, 15.5f, 22f);
		
		Dialogue.render(container, g);
	
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
