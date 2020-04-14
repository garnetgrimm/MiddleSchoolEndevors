package States;

import java.awt.Font;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.InfoReader;
import main.Main;
import main.OnSave;
import main.WorkshopTextFields;

public class StartUpState extends BasicGameState {

	private static boolean HoverSprite = false;
	private static boolean HoverText = false;
	
	//240 31
	//610 332

	private static int MouseX = 0;
	private static int MouseY = 0;
	private static boolean Click = false;

	private static boolean Cp1 = false;
	private static boolean Cp2 = false;

	Image face;
	Image paper;
	Image next;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		Main.font = new Font("Verdana", Font.BOLD, 32);
		Main.ttf = new TrueTypeFont(Main.font, true);
		face = new Image("Resources/Face.png", false, Image.FILTER_NEAREST);
		paper = new Image("Resources/Paper.png", false, Image.FILTER_NEAREST);
		next = new Image("Resources/arrow.png", false, Image.FILTER_NEAREST);

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub

		MouseX = arg0.getInput().getMouseX();
		MouseY = arg0.getInput().getMouseY();
		Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);

		if (MouseX > 695 && MouseX < 820 && MouseY > 547 && MouseY < 764 & Click && Cp1) {
			if (!Cp2) {
				arg1.enterState(1);
				InfoReader.initSprites();
			}
			else {
				arg1.enterState(1);
				InfoReader.readSpriteInfo();
				WorkshopTextFields.Default();
			}

		}

		if (MouseX > 115 && MouseX < 305 && MouseY > 220 && MouseY < 400) {

			HoverText = false;
			HoverSprite = true;

			if (Click) {
				JFileChooser fc1 = new JFileChooser();

				FileFilter filter = new FileFilter() {
					public boolean accept(File file) {
						if (file.isDirectory()) {
							return true;
						}
						String filename = file.getName();
						return (filename.endsWith(".png"));
					}

					public String getDescription() {
						return "*.png";
					}
				};

				fc1.setFileFilter(filter);

				int returnValue1 = fc1.showOpenDialog(null);
				if (returnValue1 == JFileChooser.APPROVE_OPTION) {
					File selectedFile1 = fc1.getSelectedFile();
					InfoReader.pathSearch[0] = selectedFile1;
					Cp1 = true;
				}
			}

		}

		if (MouseX > 505 && MouseX < 673 && MouseY > 220 && MouseY < 400) {

			HoverText = true;
			HoverSprite = false;

			if (Click) {
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
				Cp2 = true;
			}

		}
		}

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if (HoverSprite) g.scale(21, 21);
		else g.scale(20, 20);
		face.draw(6, 10);
		
		g.resetTransform();
		if (HoverText) g.scale(21, 21);
		else g.scale(20, 20);
		paper.draw(24, 10);

		g.resetTransform();
		g.scale(6, 6);
		if (Cp1)next.draw(115, 90);
		g.resetTransform();
		g.setFont(Main.ttf);
		g.drawString("load sprite", 115, 375);
		g.drawString("load save", 505, 375);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
