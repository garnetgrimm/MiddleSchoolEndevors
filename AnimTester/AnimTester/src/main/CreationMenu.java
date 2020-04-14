package main;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import States.AnimationEditor;

public class CreationMenu {
	public static boolean CreateMenuOpen = false;
	public static int XOffset = 100;
	public static int YOffset = 100;

	public static int CurrX = 0;
	public static int CurrY = 0;
	
	public static int LockNum = 0;

	public static TextField nameField;
	public static TextField OrderField;
	
	public static boolean CancelHover = false;
	public static boolean SaveHover = false;

	public static void init(GameContainer arg0, StateBasedGame arg1) {
		nameField = new TextField(arg0, Main.ttf, CurrX + XOffset + 10, CurrY + YOffset + 50, arg0.getWidth() - (2 * (XOffset + 10)), 45);
		nameField.setBorderColor(Color.black);
		nameField.setText("Name");
		nameField.setMaxLength(30);
		
		OrderField = new TextField(arg0, Main.ttf, CurrX + XOffset + 10, CurrY + YOffset + 135, arg0.getWidth() - (2 * (XOffset + 10)), 225);
		OrderField.setBorderColor(Color.black);
		OrderField.setText("1,2,3,4,5");
	}

	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		boolean Click = AnimationEditor.Click;
		int MouseX = AnimationEditor.MouseX;
		int MouseY = AnimationEditor.MouseY;
		
		nameField.setLocation(CurrX + XOffset + 10, CurrY + YOffset + 50);
		OrderField.setLocation(CurrX + XOffset + 10, CurrY + YOffset + 135);
		
		if(MouseX > 75 && MouseX < 100 && MouseY > 4 && MouseY < 37 && arg0.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(LockNum == 0) LockNum = 1;
			else LockNum = 0;
		}

		if(MouseX > 111 + CurrX && MouseX < 390 + CurrX && MouseY > 480 + CurrY && MouseY < 505 + CurrY) {
			SaveHover = true;
			if(Click) {
				OnSave.SavingAnim = true;
				OnSave.Save();
				
				JOptionPane.showMessageDialog(null, "Animation Saved!", "Information", JOptionPane.INFORMATION_MESSAGE);
				
				CreateMenuOpen = false;
				nameField.setText("Name");
				OrderField.setText("1,2,3,4,5");
				LockNum = 0;
				CurrX = 0;
				CurrY = 0;
			}
		} else SaveHover = false;
		
		if(MouseX > 611 + CurrX && MouseX < 727 + CurrX && MouseY > 480 + CurrY && MouseY < 505 + CurrY) {
			CancelHover = true;
			if(Click) {
			CreateMenuOpen = false;
			nameField.setText("Name");
			OrderField.setText("1,2,3,4,5");
			LockNum = 0;
			CurrX = 0;
			CurrY = 0;
			}
		} else CancelHover = false;
		
		if (MouseX > CurrX + XOffset && MouseX < CurrX + (arg0.getWidth() - XOffset)) {
			if (MouseY > CurrY + YOffset && MouseY < CurrY + (arg0.getHeight() - YOffset)) {
				if(Click && LockNum != 0) { 
					int XLength = ((arg0.getWidth() - XOffset) - XOffset);
					int YLength = ((arg0.getHeight() - YOffset) - YOffset);
					
					CurrX = MouseX - XLength / 2;
					CurrY = MouseY - YLength / 2;
					
				}
			}
		}
	}

	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if (CreateMenuOpen) {
			g.resetTransform();
			g.setColor(Color.darkGray);
			g.fillRect(CurrX + XOffset, CurrY + YOffset, arg0.getWidth() - (2 * XOffset), arg0.getHeight() - (2 * YOffset));
			g.setColor(Color.white);
			g.drawString("Animation Name", CurrX + XOffset + 10, CurrY + YOffset + 10);
			nameField.render(arg0, g);
			g.drawString("Order", CurrX + XOffset + 10, CurrY + YOffset + 95);
			OrderField.render(arg0, g);
			g.setColor(Color.white);
			if(SaveHover) g.setColor(Color.gray);
			g.drawString("Save Animation", CurrX + XOffset + 10, CurrY + arg0.getHeight() - (2 * YOffset) + 40);
			g.setColor(Color.white);
			if(CancelHover) g.setColor(Color.gray);
			g.drawString("Cancel", CurrX + XOffset + 510, CurrY + arg0.getHeight() - (2 * YOffset) + 40);
			g.setColor(Color.white);
		}
	}
}
