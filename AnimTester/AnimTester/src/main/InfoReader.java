package main;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class InfoReader {

	public static File[] pathSearch = new File[2];
	static boolean modsFound = false;
	public static Image[] sprite;
	private static int picNum = 0;

	public static void readSpriteInfo() {
		for (int i = 0; i < pathSearch.length; i++) {
			String s = pathSearch[i].toString();

			if (s.substring(s.length() - 4, s.length()).equals(".png")) {
				picNum++;
			}

			// System.out.println(spriteRes.length);
			sprite = new Image[picNum];

			if (s.substring(s.length() - 4, s.length()).equals(".txt")) {
				try {
					FileReader fr1 = new FileReader(s);
					FileReader fr2 = new FileReader(s);
					@SuppressWarnings("resource")
					BufferedReader br1 = new BufferedReader(fr1);
					@SuppressWarnings("resource")
					BufferedReader br2 = new BufferedReader(fr2);
					String line = br1.readLine();
					int lines = 0;

					while (line != null) {
						lines++;
						line = br1.readLine();
					}

					String[] fullLines = new String[lines];
					lines = 0;
					line = br2.readLine();
					while (line != null) {
						fullLines[lines] = line;
						lines++;
						line = br2.readLine();
					}

					for (int readNum = 0; readNum < fullLines.length; readNum++) {
						String[] command = fullLines[readNum].split(": ");
						if (command[0].equals("XSIZE")) {
							Main.XSize = Integer.parseInt(command[1]);
						}
						if (command[0].equals("YSIZE")) {
							Main.YSize = Integer.parseInt(command[1]);
						}
						if (command[0].equals("ANIMS")) {
							Anim.getAnims(fullLines[readNum]);
						}
						if (command[0].equals("DEFAULTANIM")) {
							Main.CurrAnim = command[1];
						 }
						if (command[0].equals("SCALE")) {
							Main.Scale = Integer.parseInt(command[1]);
						}
						if (command[0].equals("ANIMSPEED")) {
							Main.Speed = Integer.parseInt(command[1]) * 0.1f;
						}
						if (command[0].equals("MOVESPEED")) {
							Main.MovSpeed = Integer.parseInt(command[1]);
						}
						if (command[0].equals("KEYBINDS")) {
							KeyInputReader.GetKeys(fullLines[readNum]);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		initSprites();

	}
	
	public static void initSprites() {
			String s = pathSearch[0].toString();
			sprite = new Image[1];

			if (s.substring(s.length() - 4, s.length()).equals(".png")) {
				
				try {
					sprite[0] = new Image(s, false, Image.FILTER_NEAREST);
					
					Main.XTotalSize = sprite[0].getWidth();
					Main.YTotalSize = sprite[0].getHeight();
					
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	public static void render(GameContainer arg0, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.scale(Main.Scale, Main.Scale);
		for (int i = 0; i < sprite.length; i++)
			sprite[i].getSubImage(Anim.currNumX * Main.XSize, Anim.currNumY * Main.YSize, Main.XSize, Main.YSize)
					.draw(Main.CurrX / 10, Main.CurrY / 10);
		g.resetTransform();
		g.scale(0.3f * 10, 0.3f * 10);
	}

}
