package main;

import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import States.AnimationEditor;

public class AnimsList {

	public static boolean showAnimList = false;

	private static boolean FoundAny = false;
	private static int Selected = 0;
	
	public static int Width = 0;

	public static void CreateBox(String s, StateBasedGame arg1) {
		int yn = JOptionPane.showConfirmDialog(null, "Would you like to test animation: " + s, "Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (yn == JOptionPane.YES_OPTION) {
			Main.CurrAnim = s;
			arg1.enterState(2);
		}
	}

	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		int MouseX = AnimationEditor.MouseX;
		int MouseY = AnimationEditor.MouseY;

		if (showAnimList) {

			if (Anim.Started) {
				for (int i = 0; i < Anim.animNames.length; i++) {
					if (MouseY > (i * 50) && MouseY < ((i + 1) * 50) && MouseX < Width) {
						Selected = i;
						FoundAny = true;
						if (AnimationEditor.Click) {
							CreateBox(Anim.animNames[i], arg1);
						}
					}
				}
				if (!FoundAny)
					Selected = -1;
			}
		}

	}

	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.resetTransform();

		if (showAnimList) {

			g.resetTransform();
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, 10, 700);
			g.setColor(Color.white);

			if (Anim.Started) {
				
				for (int i = 0; i < Anim.animNames.length; i++) {
					BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
					FontMetrics fm = img.getGraphics().getFontMetrics(Main.font);
					int currWidth = fm.stringWidth(Anim.animNames[i]);
					if(currWidth > Width) Width = currWidth;
				}
				
				g.setColor(Color.darkGray);
				g.fillRect(0, 0, Width, 700);
				g.setColor(Color.white);
				
				for (int i = 0; i < Anim.animNames.length; i++) {
					if (i == Selected)
						g.setColor(Color.gray);
					g.drawString(Anim.animNames[i].toLowerCase(), 0, (i * 50) + (AnimationEditor.Scroll * 10));
					g.setColor(Color.white);
				}
			}
		}

	}

}
