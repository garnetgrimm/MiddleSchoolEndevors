package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Anim;
import main.AnimsList;
import main.CreationMenu;
import main.InfoReader;
import main.Main;

public class AnimationEditor extends BasicGameState {

	private static Image back;
	
	public static int MouseX = 0;
	public static int MouseY = 0;
	public static boolean Click = false;
	public static int Scroll = 0;
	
	public static float DrawAnimsX = 0;
	public static float lastX = 0;
	
	public static int MinBounds = -32;
	public static int MaxBounds = 32;
	
	public static boolean CreateHover = false;
	public static boolean ViewHover = false;
	
	public static Image[] lock = new Image[2];
	
    @Override
    public void mouseWheelMoved(int change) {
    	//Scroll += change / 120;
    	//if(Scroll < Anim.animVals.length) Scroll = Anim.animVals.length;
    }
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		  CreationMenu.init(arg0, arg1);
		
		  lock[0] = new Image("Resources/locked.png", false, Image.FILTER_NEAREST);
		  lock[1] = new Image("Resources/unlocked.png", false, Image.FILTER_NEAREST);
	      back = new Image("Resources/backarrow.png", false, Image.FILTER_NEAREST);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
		//TEST THIS
		MaxBounds = -1 * (Main.XNumOfSprites * Main.YNumOfSprites * Main.Scale) - Main.XSize;
		MinBounds = AnimsList.Width;
		
		MouseX = arg0.getInput().getMouseX();
		MouseY = arg0.getInput().getMouseY();
		Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		CreationMenu.update(arg0, arg1, arg2);
		
		System.out.println(Scroll);		
		
		if(DrawAnimsX < MinBounds) {
		if(arg0.getInput().isKeyDown(Input.KEY_LEFT) || arg0.getInput().isKeyDown(Input.KEY_A)) DrawAnimsX += 0.5f;
		}
		if(DrawAnimsX > MaxBounds) {
		if(arg0.getInput().isKeyDown(Input.KEY_RIGHT) || arg0.getInput().isKeyDown(Input.KEY_D)) DrawAnimsX -= 0.5f; 
		}
		if(MouseY >= 300 || !Click) lastX = MouseX / Main.Scale;
		if(MouseY < 300 && Click)  { DrawAnimsX -= (lastX - (MouseX / Main.Scale)) / Main.Scale; } 
		
		if(DrawAnimsX < MaxBounds) { DrawAnimsX = MaxBounds; }
		if(DrawAnimsX > MinBounds) DrawAnimsX = MinBounds;
		
		if(arg0.getInput().isKeyPressed(Input.KEY_ESCAPE) || MouseX > 0 && MouseX < 67 && MouseY > 0 && MouseY < 40 && Click && !AnimsList.showAnimList) { CreationMenu.CurrX = 0; CreationMenu.CurrY = 0; arg1.enterState(1); }
		
		if(MouseY < 35) {
			//450-750
			if(MouseX > 100 && MouseX < 410) { CreateHover = true; } else CreateHover = false;
			if(MouseX > 450 && MouseX < 750) { ViewHover = true; } else ViewHover = false;
		} else { ViewHover = false; CreateHover = false; }
		
		if(CreateHover && arg0.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(CreationMenu.CreateMenuOpen) CreationMenu.CreateMenuOpen = false;
			else CreationMenu.CreateMenuOpen = true;
		}
		
		if(ViewHover && arg0.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(AnimsList.showAnimList) AnimsList.showAnimList = false;
			else AnimsList.showAnimList = true;
		}
		
		AnimsList.update(arg0, arg1, arg2);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(Main.ttf);
		
		g.scale(Main.Scale, Main.Scale);
		for (int i = 0; i < Main.XNumOfSprites * Main.YNumOfSprites; i++) {
			InfoReader.sprite[0]
					.getSubImage(Anim.getX(i) * Main.XSize, Anim.getY(i) * Main.YSize, Main.XSize, Main.YSize)
					.draw((Main.XSize * i) + DrawAnimsX, 80 / Main.Scale);
		
		g.resetTransform();	
		g.drawString("" + (i + 1), (((Main.XSize * i) * Main.Scale) + ((Main.XSize * Main.Scale) / 2)) + (DrawAnimsX * Main.Scale), 50);	
		
		g.scale(Main.Scale, Main.Scale);
		
		}
		
		g.resetTransform();
		
		if(CreateHover) g.setColor(Color.gray);
		g.drawString("Create Animation", 100, 0);
		g.setColor(Color.white);
		if(ViewHover) g.setColor(Color.gray);
		g.drawString("View Animations", 450, 0);
		g.setColor(Color.white);
		
		g.scale(2, 2);
		lock[CreationMenu.LockNum].draw(37f, 0f);
		g.resetTransform();
		
		g.scale(0.3f * 10, 0.3f * 10);
		back.draw();
		
		AnimsList.render(arg0, arg1, g);
		CreationMenu.render(arg0, arg1, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
