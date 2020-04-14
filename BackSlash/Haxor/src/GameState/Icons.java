package GameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Icons {
	
	public static Image PyDos, BatDos, Trace;
	public static int numIcons = 3;
	public static int[] iconX = new int[numIcons];
	public static int selIcon = 0;
	public static int MosX = 0;
	public static int MosY = 0;
	
	//TO ADD A NEW ICON SIMPLY ADD IT TO INIT DRAW IT IN RENDER AND SET NUMICONS ONE HIGHER
	
	public static void init() throws SlickException {
		BatDos = new Image("res/batch dos.png", false, Image.FILTER_NEAREST);
		PyDos = new Image("res/py dos.png", false, Image.FILTER_NEAREST);
		//need replacement image for trace
		Trace = new Image("res/py dos.png", false, Image.FILTER_NEAREST);
	}
	
	public static void update(GameContainer container, StateBasedGame menu, int delta) throws SlickException {
		MosX = container.getInput().getMouseX();
		MosY = container.getInput().getMouseY();
		
		if(container.getInput().isKeyPressed(Input.KEY_S) && !ConsoleType.textField.hasFocus()) { selIcon++; }
		if(container.getInput().isKeyPressed(Input.KEY_W) && !ConsoleType.textField.hasFocus()) { selIcon--; }
		if(selIcon < 0) selIcon = numIcons - 1;
		if(selIcon > numIcons - 1) selIcon = 0;
		
		if(MosX >= 750) ConsoleType.textField.setFocus(false);
		if(MosX < 750) ConsoleType.textField.setFocus(true);
		
		if(iconX[selIcon] < 10) iconX[selIcon]++;
		for(int i = 0; i < numIcons; i++) {
			if(iconX[i] != 0 && i != selIcon) iconX[i] = 0;
			
			if(MosX > 750 && MosY > (20 + (i * 30)) * 2 && MosY < (50 + (i * 30)) * 2) { 
				selIcon = i;
					if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
						if(i == 0) ConsoleType.textField.setText("PyDos.exe");
						if(i == 1) ConsoleType.textField.setText("BatDos.exe");
						if(i == 2) ConsoleType.textField.setText("trace");
					}
			}
		}
	}
	
	public static void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		PyDos.draw(385 - iconX[0], 20);
		BatDos.draw(385 - iconX[1], 50);
		Trace.draw(385 - iconX[2], 80);
	}
	
	
}
