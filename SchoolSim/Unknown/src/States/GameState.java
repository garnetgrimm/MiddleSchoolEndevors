package States;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Controllers;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.ChangingText;

public class GameState extends BasicGameState {
	
	public static Image Char = null;
	static int animTickNum = 0;
	static int animNum = 0;
	static boolean right = false;
	static float x = 0;
	static int[] walkingAnim = { 0,1,0,2 };
	
	public static String[] firstText = {
		"Hi.",
		"Welcome back.",
		"What do you even see in that other text??",
	};
		
	public static String[] secondText = {
			"I see youve found our game.",
			"Stop leaving me."
	};
	
	public List<ChangingText> Texts = new ArrayList<ChangingText>();
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Char = new Image("Resources/bloop.png", false, Image.FILTER_NEAREST);
	
		//new ChangingText(text order, if it changed on greater or less than, num it changes on, x, y));
		Texts.add(new ChangingText(firstText, true, 40, 1, 16));
		Texts.add(new ChangingText(secondText, false, 20, 100, 16));	
	}
	
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		boolean walking = false;
		if(arg0.getInput().isKeyDown(Input.KEY_A)) { walking = true; x -= 0.5f; right = true; }
		if(arg0.getInput().isKeyDown(Input.KEY_D)) { walking = true; x += 0.5f; right = false; }
		if(walking) animate();
		else animNum = 0;
		
		for(int i = 0; i < Texts.size(); i++) {
			Texts.get(i).update(x);	
		}

		System.out.println(x);
	}
	
	public static void animate() {
		animTickNum++;
		if(animTickNum >= 10) { animTickNum = 0; animNum++; }
		if(animNum > 3) animNum = 0;
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setBackground(Color.darkGray);
		for(int i = 0; i < Texts.size(); i++) {
			Texts.get(i).render(arg0, arg1, g);	
		}
		g.scale(10, 10);
		Char.getSubImage(walkingAnim[animNum] * 7, 0, 7, 18).getFlippedCopy(right, false).draw(16, 16);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}