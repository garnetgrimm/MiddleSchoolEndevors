package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Anim;
import main.HexColor;
import main.Main;
import main.WorkshopTextFields;

public class WorkShopState extends BasicGameState {

	public static TextField[] textField = new TextField[3];
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
	  	  for(int i = 0; i < 3; i++) {
	  	  	  textField[i] = new TextField(arg0, Main.ttf, (615 + (i * 60)), 400, 45, 45); 
	  	  	  textField[i].setBorderColor(Color.white);
	  	  	  textField[i].setText("0");
	  	  	  textField[i].setMaxLength(1);
	  	  }
		
		WorkshopTextFields.init(arg0);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		int R = Main.R;
		int G = Main.G;
		int B = Main.B;
		
		R = HexColor.R;
		G = HexColor.G;
		B = HexColor.B;
		
		String currColor = "0x"+R+R+G+G+B+B;
		
		Main.MyColor = new Color(Color.decode(currColor));
		if(!currColor.equals("0x000000")) Main.MyColorRev = new Color(Color.decode("0x" + (Integer.toHexString((255-Main.MyColor.getRed()))+(Integer.toHexString((255-Main.MyColor.getGreen()))+(Integer.toHexString((255-Main.MyColor.getBlue())))))));
		else Main.MyColorRev = new Color(0x999999);
		
		HexColor.update(arg0, arg2);
		
		WorkshopTextFields.update(arg0, arg1, arg2);
		
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setBackground(new Color(Main.MyColor));
		g.setFont(Main.ttf);
		
		g.drawString("R", 615 + 0, 360);
		g.drawString("G", 615 + 60, 360);
		g.drawString("B", 615 + 120, 360);
		for(int i = 0; i < 3; i++) { textField[i].render(arg0, g); }
		
		g.drawString("Single Sprite Size X: ", 10, 40 - 6);
		g.drawString("Single Sprite Size Y: ", 10, 90 - 6);
		g.drawString("Scale: ", 10, 140 - 6);
		g.drawString("Animation Playback Speed: ", 10, 190 - 6);
		g.drawString("Movement Speed: ", 10, 240 - 6);
		
		if(WorkshopTextFields.SaveHover) { g.setColor(Color.gray); }
		g.drawString("Save Values", 10, 340);
		g.setColor(Color.white);
		
		if(WorkshopTextFields.AnimHover) { g.setColor(Color.gray); }
		if(Main.XSize == 0 || Main.YSize == 0 || Main.Scale == 0) { if(WorkshopTextFields.AnimHover) g.setColor(Color.red); }
		g.drawString("Animaton Editor", 10, 380);
		g.setColor(Color.white);
		
		if(WorkshopTextFields.PlayHover && Anim.Started) { g.setColor(Color.gray); }
		if(WorkshopTextFields.PlayHover && !Anim.Started) { g.setColor(Color.red); }
		if(Main.XSize == 0 || Main.YSize == 0 || Main.Scale == 0) { if(WorkshopTextFields.PlayHover) g.setColor(Color.red); }
		g.drawString("Test Animations", 10, 420);
		g.setColor(Color.white);
		
		if(WorkshopTextFields.PrevSaveHover) { g.setColor(Color.gray); }
		g.drawString("Open Previous Save", 10, 460);
		g.setColor(Color.white);
		
		WorkshopTextFields.render(arg0, g);
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
