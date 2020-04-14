package main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

public class WorkshopTextFields {
	
	public static int MouseX = 0;
	public static int MouseY = 0;
	private static boolean Click = false;
	
	public static boolean SaveHover = false;
	public static boolean AnimHover = false;
	public static boolean PlayHover = false;
	public static boolean PrevSaveHover = false;
	
	public static TextField[] textField = new TextField[5];
	
	public static void init(GameContainer arg0) {
		for(int i = 0; i < textField.length; i++) {
		textField[i] = new TextField(arg0, Main.ttf, 615, 30 + (i * 50), 100,40);
		textField[i].setMaxLength(4);
		}
		textField[0].setText("" + Main.XSize);
		textField[1].setText("" + Main.YSize);
		textField[2].setText("" + Main.Scale);
		textField[3].setText("" + Main.Speed * 10);
		textField[4].setText("" + Main.MovSpeed);
	}
	
	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		try {
		Main.XSize = Integer.parseInt(textField[0].getText());
		Main.YSize = Integer.parseInt(textField[1].getText());
		Main.Scale = Integer.parseInt(textField[2].getText());
		Main.Speed = Float.parseFloat(textField[3].getText()) / 10;
		Main.MovSpeed = Integer.parseInt(textField[4].getText());
		} catch(Exception e) {}
		
		MouseX = arg0.getInput().getMouseX();
		MouseY = arg0.getInput().getMouseY();
		Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		if(MouseX > 10 && MouseX < 225 && MouseY > 350 && MouseY < 375) SaveHover = true;
		else { SaveHover = false; }
		if(MouseX > 10 && MouseX < 300 && MouseY > 375 && MouseY < 420) AnimHover = true;
		else { AnimHover = false; }
		if(MouseX > 10 && MouseX < 300 && MouseY > 420 && MouseY < 465) PlayHover = true;
		else { PlayHover = false; }
		if(MouseX > 10 && MouseX < 300 && MouseY > 460 && MouseY < 505) PrevSaveHover = true;
		else { PrevSaveHover = false; }
		
		if(PrevSaveHover && Click) { 
			JFileChooser fc2 = new JFileChooser();
			// fc2.addChoosableFileFilter();

			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					}
					String filename = file.getName();
					return (filename.endsWith("." +  OnSave.FileExt));
				}

				public String getDescription() {
					return "*." +  OnSave.FileExt;
				}
			};

			fc2.setFileFilter(filter);

			int returnValue2 = fc2.showOpenDialog(null);
			if (returnValue2 == JFileChooser.APPROVE_OPTION) {
				File selectedFile2 = fc2.getSelectedFile();
				InfoReader.pathSearch[1] = selectedFile2;
				InfoReader.readSpriteInfo();
			}
		}
		if(SaveHover && Click) { OnSave.Save(); } 
		if(AnimHover && Click) { 
			if(Main.XSize == 0 || Main.YSize == 0 || Main.Scale == 0) JOptionPane.showMessageDialog(null, "XSize YSize Or Scale is Set To 0", "Error", JOptionPane.INFORMATION_MESSAGE);
			else{
			Main.XNumOfSprites = Main.XTotalSize / Main.XSize;
			Main.YNumOfSprites = Main.YTotalSize / Main.YSize;
			
			arg1.enterState(4);
			
			}
		}
		if(PlayHover && Click) {
			
			if(Main.XSize == 0 || Main.YSize == 0 || Main.Scale == 0) JOptionPane.showMessageDialog(null, "XSize YSize Or Scale is Set To 0", "Error", JOptionPane.INFORMATION_MESSAGE);
			else{
			Main.XNumOfSprites = Main.XTotalSize / Main.XSize;
			Main.YNumOfSprites = Main.YTotalSize / Main.YSize;
			
			if(Anim.Started) {
				Main.CurrAnim = Anim.animNames[0];
				arg1.enterState(2);
			}
			else JOptionPane.showMessageDialog(null, "Must Create Animations Before Testing Them!", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	public static void Default() {
		textField[0].setText("" + Main.XSize);
		textField[1].setText("" + Main.YSize);
		textField[2].setText("" + Main.Scale);
		textField[3].setText("" + (int) Main.Speed * 10);
		textField[4].setText("" + (int) Math.round(Main.MovSpeed));
	}
	
	public static void render(GameContainer arg0, Graphics g) {
		for(int i = 0; i < textField.length; i++) {
			textField[i].render(arg0, g);
		}
	}
}
