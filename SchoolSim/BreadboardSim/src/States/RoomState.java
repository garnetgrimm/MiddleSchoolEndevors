package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class RoomState extends BasicGameState implements MusicListener {
	
	public static Image back = null;
	public static Image gabe = null;
	public static Image wire = null;
	
	public static int[] CurrAnim = { 0,0 };
	public static int CurrAnimNum = 0;
	public static int x = 0;
	public static boolean right = false;
	public static int TickNum = 0;
	
	public static boolean foundChord = false;
	public static boolean showAchive = false;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		back = new Image("Resources/MyRoom.png", false, Image.FILTER_NEAREST);
		gabe = new Image("Resources/Gabe.png", false, Image.FILTER_NEAREST);
		wire = new Image("Resources/Wire.png", false, Image.FILTER_NEAREST);
		
	    Music BGM = new Music("Resources/LoneDigger.ogg");
	    BGM.addListener(this);
	    BGM.play();
	}
	
	public static void playAnim(int AnimNum) {
		if(AnimNum == -1) { CurrAnim[0] = 2; CurrAnim[1] = 3; right = true; }
		if(AnimNum == 1) { CurrAnim[0] = 2; CurrAnim[1] = 3; right = false; }
		if(AnimNum == 0) { CurrAnim[0] = 0; CurrAnim[1] = 0; }
		
		if(TickNum < 8) TickNum++;
		else {
			TickNum = 0;
			if(CurrAnimNum < CurrAnim.length - 1) CurrAnimNum++;
			else CurrAnimNum = 0;
		}
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if(arg0.getInput().isKeyDown(Input.KEY_A)) { x -= 1; playAnim(-1); }
		else if(arg0.getInput().isKeyDown(Input.KEY_D)) { x += 1; playAnim(1); }
		else playAnim(0);
		
		if(x <= 6) x = 6;
		if(x >= 70) x = 70;
		
		if(x > 40 && !foundChord && !showAchive) { foundChord = true; showAchive = true; }
		
		if(foundChord == true && x == 6) { showAchive = false; }
		
		if(foundChord && !showAchive) arg1.enterState(1, new FadeOutTransition(), new FadeInTransition());
	
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.scale(10, 10);
		back.draw();
		
		if(!foundChord && !showAchive)wire.draw(50,45);
		if(foundChord && !showAchive) { wire.draw(3,44); }
		
		gabe.getSubImage(CurrAnim[CurrAnimNum] * 17, 0, 17, 37).getFlippedCopy(right, false).draw(x, 16);
		
		g.resetTransform();
		g.setColor(Color.red);
		if(showAchive) g.drawString("Object aquired! Printer Power Cable", 0, 0);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void musicEnded(Music arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void musicSwapped(Music arg0, Music arg1) {
		// TODO Auto-generated method stub
		
	}
}
