package startupComponents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.ImgButton;

public class Toolbar {
	
	static ImgButton pencilTool = null;
	
	public static void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Image pencil = new Image("res/pencil.png", false, Image.FILTER_NEAREST);
		pencilTool = new ImgButton(pencil, 3, 90, 50);
	}
	
	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		System.out.println(pencilTool.isMouseOver(arg0));
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.scale(3, 3);
		pencilTool.draw(arg0, g);
		g.resetTransform();
	}
}
