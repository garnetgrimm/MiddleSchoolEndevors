package PasswordState;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import PasswordState.PasswordBox;
import main.Main;

public class PasswordState extends BasicGameState {

	public static int disXPos = 150;
	public static Image bowTie;
	private static boolean bowInc = false;
	private static float rotTick = 0;
	public static boolean bowTieClicked = false;
	public static boolean EBowTieClicked = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame menu) throws SlickException {
		PasswordBox.init(container, menu);
		CheckReadied.init(container, menu);
		bowTie = new Image("res/boowtie.png", false, Image.FILTER_NEAREST);
	}

	@Override
	public void update(GameContainer container, StateBasedGame menu, int delta) throws SlickException {
		CheckReadied.update(container, menu);
		Main.update();
		
		if(EBowTieClicked) { menu.enterState(2); }
		
		if(CheckReadied.MosX > 126 && CheckReadied.MosX < 310 && CheckReadied.MosY > 440 && CheckReadied.MosY < 580)
		{
			if(bowInc) { rotTick += 0.8f; }
			if(!bowInc) { rotTick -= 0.8f; }
			if(rotTick >= 20) { bowInc = false; }
			if(rotTick <= -20) { bowInc = true; }
			
			if(CheckReadied.Click && CheckReadied.AllReady) { Main.sendRequest = true; bowTieClicked = true; menu.enterState(2); }
		}
		else {
			if(rotTick > 0) rotTick -= 1.6f;
			if(rotTick < 0) rotTick += 1.6f;
		}
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		g.setBackground(new Color(0x000000));
		
		g.drawString("ENTER YOUR PASSWORD", 15 + disXPos, 120);
		g.drawString("ENTER YOUR IP", 370 + disXPos, 67);
		g.drawString("ENTER CLIENTS IP", 360 + disXPos, 127);
		
		g.drawString("ENEMY PASSWORD READY", 15 + disXPos, (177 * 2) + 6);
		g.drawString("ENEMY CLIENT READY", 370 + disXPos, (205 * 2) + 6);
		g.drawString("ENEMY SERVER READY", 370 + disXPos, (175 * 2) + 6);
		
		g.drawString("CLICK BOWTIE TO INITIATE GAME", 350, 500);
		
		g.drawString("> Check the marker when completly completed a single field.", 155, 230);
		if(CheckReadied.ServerFirst)g.drawString("> Cannot start client until server has been started.", 155, 255);
		PasswordBox.render(container, menu, g);
		g.scale(2, 2);
		CheckReadied.render(container, menu, g);
		g.scale(3, 3);

		bowTie.setRotation(rotTick);
		bowTie.draw(20, 70);
	}

	@Override
	public int getID() {
		return 1;
	}

}
