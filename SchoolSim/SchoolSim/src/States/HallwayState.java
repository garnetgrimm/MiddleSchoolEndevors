package States;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Ellie;
import main.Garnet;
import main.Main;

public class HallwayState extends BasicGameState {
	
	public static int x = -275 / 2;
	public static Image[] back = new Image[3];
	public static int[] mapNum = { 2,0,2,2,0,1,0,2,2,0,2 };
	
	public static Font font;
	public static TrueTypeFont ttf;
	
	public static boolean hideWarn = false;
	public static boolean firstHideWarn = true;
	public static int hideWarnTime = 0;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		font = new Font("Verdana", Font.BOLD, 32);
		ttf = new TrueTypeFont(font, true);
		
		Garnet.init(arg0);
		Ellie.init(arg0);
		
		back[0] = new Image("Resources/Hall.png", false, Image.FILTER_NEAREST);
		back[1] = new Image("Resources/Bland.png", false, Image.FILTER_NEAREST);
		back[2] = new Image("Resources/Lockers.png", false, Image.FILTER_NEAREST);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		
		Main.EllieX = 35;
		
		if(arg0.getInput().isKeyPressed(Input.KEY_E)) {  
			Ellie.hide();
			
			if(-x / 36 == 0) {
				firstHideWarn = false;
				hideWarn = false;
				Main.EllieX = 1;
				Ellie.dir = 1;
				arg1.enterState(1);
			}
		}
		
		if(arg0.getInput().isKeyDown(Input.KEY_A) && !Ellie.Hiding) { x += 1; Ellie.playAnim(-1, arg1); }
		else if(arg0.getInput().isKeyDown(Input.KEY_D) && !Ellie.Hiding) { x -= 1; Ellie.playAnim(1, arg1); }
		else Ellie.playAnim(0, arg1);
		
		if(x <= -275) x = -275;
		if(x >= 0) x = 0;
		
		if(x - 20 <= -60 && x - 20 >= -100 && !Ellie.Hiding) Garnet.playAnim(1);
		else Garnet.playAnim(0);
		
		if(mapNum[((x / 36) * -1) + 1] == 2 && firstHideWarn) {
			hideWarn = true;
			hideWarnTime++;
			if(hideWarnTime == 200) {
				hideWarn = false; hideWarnTime = 0; firstHideWarn = false;
			}
		}
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.scale(10, 10);
		
		for(int i = 0; i < mapNum.length; i++) {
			back[mapNum[i]].draw(i * 36 + x, 0);	
		}
		
		g.resetTransform();
		g.setColor(Color.white);
		g.drawString("math", 660 + (x * 10), 190);
		g.scale(10, 10);
		
		Ellie.render(arg0, arg1, g, Main.EllieY);
		Garnet.render(arg0, g);
		
		g.resetTransform();
		g.setFont(ttf);
		g.setColor(Color.black);
		if(hideWarn) g.drawString("Press E to Interact With Objects", 130, 260);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
