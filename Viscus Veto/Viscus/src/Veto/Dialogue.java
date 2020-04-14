package Veto;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import States.IntroState;

public class Dialogue {
	
	public static boolean InRange = false;
	public static Image Notify, TextField;
	public static String Speech = "...";
	public static boolean Opened, Ask = false;
	public static int OpenTimer = 0;
	public static Image CBox1, CBox2, CBox3, Select;
	public static int SelectX, SelectY, Selection, QuestNum, TextAdj;
	public static int BoxNum, Box1Y, Box2Y, Box3Y, Box1X, Box2X, Box3X = 0;
	private static String Quest1, Quest2;//, Quest3;
	
	public static void init() throws SlickException 
	{
		Ask = false;
		Selection = 1;
		Select = new Image("res/Select.png");
		CBox1 = new Image("res/Choice.png");
		CBox2 = new Image("res/Choice.png");
		CBox3 = new Image("res/Choice.png");
		Notify = new Image("res/Notification.png");
		TextField = new Image("res/TextField.png");
	}
	
	public static void update() throws SlickException 
	{
		if(Opened) { OpenTimer += 1; Clamothy.Speaking = true; }
		if(OpenTimer >= 500) { Clamothy.Speaking = false; OpenTimer = 500; }
		
		if(IntroState.x <= 0 && IntroState.x >= -250) InRange = true;
		else InRange = false;
		
		if(Selection > BoxNum + 1) Selection = 1;
		if(Selection < 0) Selection = BoxNum + 1;
		
		if(BoxNum == 2) {
			if(Selection == 1) { SelectX = 260; SelectY = 15; }
			if(Selection == 2) { SelectX = 260; SelectY = 145; }
		}
		if(BoxNum == 3) {
			if(Selection == 1) { SelectX = 260; SelectY = 15; }
			if(Selection == 2) { SelectX = 100; SelectY = 145; }
			if(Selection == 3) { SelectX = 420; SelectY = 145; }
		}
		
	}
	
	public static void Choose() {
		Ask = false; Clamothy.Speaking = false;
	}
	
	public static void Text(int LogNum) throws SlickException
	{
		if(LogNum == 1) { Speech = "Hello, young one, I am known as Clamithy."; TextAdj = -18; }
		if(LogNum == 2) { Speech = "And this is the world of reconciliation."; TextAdj = 9; MakeChoice(1); }
		if(LogNum == 3 && Selection == 1) { Speech = "My, arn't you polite."; }
		if(LogNum == 3 && Selection == 2) { LogNum = 4; }
		if(LogNum == 4) { Speech = "You are here to either save or destroy"; }
		if(LogNum == 5) { Speech = "The choice is your's..";  }
		if(LogNum == 6) { Opened = false; OpenTimer = 0; Choose(); }
		if(LogNum >= 7) { Speech = "..."; }
	}
	
	public static void MakeChoice(int ChoiceNum) {
		if(ChoiceNum == 1) { BoxNum = 2; Quest1 = "Hello Clamithy"; Quest2 = "Why am I here?"; }
		//if(ChoiceNum == 2) { BoxNum = 3; }

		QuestNum = ChoiceNum;
		Ask = true;
			
		if(BoxNum == 2) { Box1Y = 20; Box2Y = 150; Box1X = 260; Box2X = 260; }
		if(BoxNum == 3) { Box1Y = 150; Box2Y = 150; Box1X = 100; Box2X = 420; } 
	
	}
	
	public static void render(GameContainer container, Graphics g) throws SlickException {
		//DRAW CHOICE BOXES HERE
		
		if(InRange && !Opened)
		{
			Notify.draw(3,50,8f,8f);	
		}
		
		g.scale(0.1f, 0.1f);
		
		if(Ask) {
		CBox2.draw(Box1X, Box1Y);
		CBox1.draw(Box2X, Box2Y);
		CBox3.draw(260, 20);
		Select.draw(SelectX, SelectY);
		
		g.setColor(Color.black);
		g.setFont(IntroState.font);
		g.drawString(Quest1, Box1X + 60, Box1Y + 40);
		g.drawString(Quest2, Box2X + 60, Box2Y + 40);
		}
		
		if(Opened) {
			TextField.draw(100, 470);
			g.setFont(IntroState.font);
			g.setColor(new Color(0x5E1E89));
			g.drawString(Speech, 170 + TextAdj, 515);
		}
	}
	
}
