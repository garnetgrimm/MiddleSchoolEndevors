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
import main.Kionna;
import main.Main;
import main.MathGoblin;
import main.WhiteBoard;

public class ClassState extends BasicGameState {

	public static Image back = null;
	public static Image chair = null;
	public static Image desk = null;
	
	private static int MouseX = 0;
	private static int MouseY = 0;
	private static boolean Click = false;
	
	public static int lastMouseX = 0;
	public static int tiredness = 0;
	public static int sleepInc = 0;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		back = new Image("Resources/MathClass.png", false, Image.FILTER_NEAREST);
		chair = new Image("Resources/Chair.png", false, Image.FILTER_NEAREST);
		desk = new Image("Resources/Table.png", false, Image.FILTER_NEAREST);
		
		WhiteBoard.init();
		Kionna.init(arg0);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
		MouseX = arg0.getInput().getMouseX();
		MouseY = arg0.getInput().getMouseY();
		Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		if(Ellie.playingSit && Click && MouseX > 270 && MouseX < 580 && MouseY > 50 && MouseY < 310) {
			WhiteBoard.drawPix(MouseX / 10, MouseY / 10);
			
			if(MouseX - lastMouseX != 0) sleepInc = 20;
			else sleepInc = 0;
			
			tiredness += sleepInc;
		}
		
		if(!Ellie.playingSit) {
			if(arg0.getInput().isKeyDown(Input.KEY_A) && !Ellie.Hiding) { Main.EllieX -= 1; Ellie.playAnim(-1, arg1); }
			else if(arg0.getInput().isKeyDown(Input.KEY_D) && !Ellie.Hiding) { Main.EllieX += 1; Ellie.playAnim(1, arg1); }
			else Ellie.playAnim(0, arg1);
		} else { 
			if(tiredness / 100 != 100) Ellie.playAnim(2, arg1); 
			else Ellie.playAnim(5, arg1); 
		}
		
		if(Main.EllieX >= 7 && MathGoblin.Health > 0) {
			Ellie.playingSit = true;
		}
		
		if(Main.EllieX < -5 && !Ellie.playingSit) { arg1.enterState(0); }
		if(Main.EllieX >= 15) Main.EllieX = 15;
		
		Kionna.update();
		
		lastMouseX = MouseX;
		
		if(tiredness / 100 >= 100) tiredness = 100 * 100;
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.scale(10,10);
		back.draw();
		
		WhiteBoard.render(arg0, arg1, g);
		
		Kionna.render(arg0, g);
		
		chair.draw(5,20);
		Ellie.render(arg0, arg1, g, Main.EllieY);
		desk.draw(11,31);
		
		g.resetTransform();
		g.setColor(Color.red);
		g.drawString("percent solved", 280, 270);
		g.drawString(tiredness / 100 + "%", 280, 290);
		g.setColor(Color.black);
	
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
