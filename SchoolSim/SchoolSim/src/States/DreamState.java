package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Ellie;
import main.Main;
import main.MathGoblin;
import main.QueenKionna;

public class DreamState extends BasicGameState {

	public static Image back = null;
	public static Image chair = null;
	
	public static Image punch = null;
	
	public static int StatsX = 2;
	public static int StatsY = 1;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		back = new Image("Resources/Colosseum.png", false, Image.FILTER_NEAREST);
		chair = new Image("Resources/Chair.png", false, Image.FILTER_NEAREST);
		
		MathGoblin.init(arg0, arg1);
		QueenKionna.init(arg0);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		MathGoblin.update(arg0, arg1, arg2);
		
		if(arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) { 
			
			Ellie.playingSit = false; 
			MathGoblin.checkBounds(Main.EllieX);
			
			if(arg0.getInput().isKeyDown(Input.KEY_D)) {
				Main.EllieX += 1;
				Ellie.playAnim(4, arg1);
			} else if(arg0.getInput().isKeyDown(Input.KEY_A)) {
				Main.EllieX -= 1;
				Ellie.playAnim(-4, arg1);
			} else {
				Ellie.playAnim(3, arg1); 
			}
		} else {
			
			MathGoblin.playAnim(0);
			if(arg0.getInput().isKeyDown(Input.KEY_A)) { Ellie.playingSit = false; Main.EllieX -= 1; Ellie.playAnim(-1, arg1); }
			else if(arg0.getInput().isKeyDown(Input.KEY_D)) { Ellie.playingSit = false; Main.EllieX += 1; Ellie.playAnim(1, arg1); }
			else Ellie.playAnim(0, arg1);
		}
		
		if(Main.EllieX <= 0)Main.EllieX = 0;
		
		QueenKionna.update();
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {		
		g.scale(10,10);
		back.draw();
		chair.draw(5,41);
		QueenKionna.render(arg0, g);
		MathGoblin.render(arg0, arg1, g);
		Ellie.render(arg0, arg1, g, 35);
		
		g.setColor(Color.red);
		g.fillRect(0 + StatsX, 2 + StatsY, MathGoblin.Health / 5, 1);
		g.setColor(Color.black);
		g.drawRect(0 + StatsX, 2 + StatsY, 100 / 5, 1);
		
		g.resetTransform();
		g.drawString("Math Goblin Health", 14 + (StatsX * 10), 0 + (StatsY * 10));
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
