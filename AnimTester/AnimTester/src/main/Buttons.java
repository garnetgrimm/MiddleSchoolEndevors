package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

public class Buttons {

	static boolean D = false;
	static boolean A = false;
	static boolean W = false;
	static boolean S = false;
	
	private static int mouseFactor = 60;
	private static int MouseX = 0;
	private static int MouseY = 0;
	private static boolean Click = false;
	static Font font;
	
	public static Image back;
	
    public static void init(GameContainer gc) throws SlickException {
    	
      back = new Image("Resources/backarrow.png", false, Image.FILTER_NEAREST);
  	  font = new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.ITALIC, 26));
  } 
	
	public static void update(GameContainer arg0, int arg1, StateBasedGame arg2) {		
		MouseX = arg0.getInput().getMouseX();
		MouseY = arg0.getInput().getMouseY();
		Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		if(MouseY > 44 && MouseY < 77) {
			if(MouseX >= 510 && MouseX < 540) { A = true; } else A = false;
			if(MouseX >= 540 && MouseX < 570) { S = true; } else S = false;
			if(MouseX >= 570 && MouseX < 600) { D = true; } else D = false;
		} else { D = false; A = false; S = false; } 
		if(MouseX >= 540 && MouseX < 570 && MouseY > 10 && MouseY < 44) { W = true; }
		else W = false;
		
		if(arg0.getInput().isKeyDown(Input.KEY_D) || D) Main.CurrX += Main.MovSpeed;
		if(arg0.getInput().isKeyDown(Input.KEY_A) || A) Main.CurrX -= Main.MovSpeed;
		if(arg0.getInput().isKeyDown(Input.KEY_W) || W) Main.CurrY -= Main.MovSpeed;
		if(arg0.getInput().isKeyDown(Input.KEY_S) || S) Main.CurrY += Main.MovSpeed;
		
		for(int i = 0; i < Anim.animNames.length; i++) {		
			if(MouseX > 675) {
			if(MouseY > i * mouseFactor && MouseY < ((i + 1) * mouseFactor)) {
				Main.CurrAnim = Anim.animNames[i];
			}
			}
			
		}
		
		if(arg0.getInput().isKeyPressed(Input.KEY_ESCAPE) || MouseX > 0 && MouseX < 67 && MouseY > 0 && MouseY < 40 && Click) { arg2.enterState(1); }
	
	}
	
	public static void render(GameContainer arg0, Graphics g) throws SlickException {
		
		back.draw();
		
		g.resetTransform();
		g.setFont(Main.ttf);
		
		if(arg0.getInput().isKeyDown(Input.KEY_W) || W) g.setColor(Main.MyColorRev);
		else g.setColor(Color.white);
		g.drawString("W", 540, 10);
		if(arg0.getInput().isKeyDown(Input.KEY_A) || A) g.setColor(Main.MyColorRev);
		else g.setColor(Color.white);
		g.drawString("A", 510, 44);
		if(arg0.getInput().isKeyDown(Input.KEY_S) || S) g.setColor(Main.MyColorRev);
		else g.setColor(Color.white);
		g.drawString("S", 540, 44);
		if(arg0.getInput().isKeyDown(Input.KEY_D) || D) g.setColor(Main.MyColorRev);
		else g.setColor(Color.white);
		g.drawString("D", 570, 44);
		g.setColor(Color.white);
		
		for(int i = 0; i < Anim.animNames.length; i++) {
			g.setColor(Color.white);
			g.drawString(Anim.animNames[i], 675, i * 60);
		}
	}
	
}
