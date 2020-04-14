package States;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Button;
import main.DrawDepth;
import main.Grouping;
import main.Highlighter;
import main.Main;
import main.SaveModel;

public class StartUpState extends BasicGameState {

	public static boolean FillHighlight = false;
	public static int[][] Depth = null;
	public static int[][] Group = null;
	
	public static boolean started = false;
	public static File path;
	public static Image cat = null;
	public static int[][] Pixels = null;
	public static BufferedImage img = null;
	public static int[] highestPixel = null;
	public static int[] lowestPixel = null;
	public static Button save = new Button("Save", Main.WIDTH - 100, 0);
	public static Button open = new Button("Open PNG", Main.WIDTH - 194, 45);
	public static Button done = new Button("Done", Main.WIDTH - 110, 138);
	
	public static int drawingNum = 0;
	public static String[] drawingPixels = null;
	public static String[] extrudedPixels = null;
	public static String[] forFaces1 = null;
	public static String[] forFaces2 = null;
	
	public static int borderNum = 0;
	public static String[] borderPixels = null;
	
	public static TextField textField = null;
	
	public static String ImageName = "";
	
	public static boolean Down = false;
	public static boolean Click = false;
	public static int MouseX = 0;
	public static int MouseY = 0;
	
	public static void setDepths() {
		for(int x = 0; x < cat.getWidth(); x++) {
			for(int y = 0; y < cat.getHeight(); y++) {
				//only want to draw if its touching the pink
				if(Pixels[x][y] != -65281) {
						if(x >= Highlighter.StartX && x < Highlighter.MouseX && y >= Highlighter.StartY && y < Highlighter.MouseY)
							Depth[x][y] = Integer.parseInt(textField.getText());
						if(x < Highlighter.StartX && x >= Highlighter.MouseX && y < Highlighter.StartY && y >= Highlighter.MouseY)
							Depth[x][y] = Integer.parseInt(textField.getText());
				}
			}
		}
	}
	
	public static String getImageFromPath(String path) {
		System.out.println(path.toString());
		String[] tokens = path.toString().split("\\\\");
		System.out.println(tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].length() - 4));
		return tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].length() - 4);		
	}
	
	public static boolean touchingPink(int x, int y) {
		try{
			if(Pixels[x - 1][y] == -65281) return true;
			if(Pixels[x + 1][y] == -65281) return true;
			if(Pixels[x][y - 1] == -65281) return true;
			if(Pixels[x][y + 1] == -65281) return true;
		} catch(Exception e) {}	
		
		return false;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		  Main.font = new Font("Verdana", Font.BOLD, 32);
		  Main.ttf = new TrueTypeFont(Main.font, true);  
		
		  textField = new TextField(arg0, Main.ttf, Main.WIDTH - 195, 135, 80, 45); 
	  	  textField.setBorderColor(Color.white);
	  	  textField.setText("0");
	}
	
	public static void openJChooser() {
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
			path = selectedFile1;
			System.out.println(path);
			try {
				start();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void start() throws SlickException {
		
		started = true;
		cat = new Image(path.toString(), false, Image.FILTER_NEAREST);
		ImageName = getImageFromPath(path.toString());
		
		try {
			img = ImageIO.read(new File(path.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		highestPixel = new int[cat.getWidth()];
		lowestPixel = new int[cat.getWidth()];
		
		for(int i = 0; i < cat.getWidth(); i++) {
			lowestPixel[i] = 0;
			highestPixel[i] = -1;
		}
		
		Pixels = new int[cat.getWidth()][cat.getHeight()];
		Depth = new int[cat.getWidth()][cat.getHeight()];
		Group = new int[cat.getWidth()][cat.getHeight()];
		
		for(int x = 0; x < cat.getWidth(); x++) {
			for(int y = 0; y < cat.getHeight(); y++) {
				Pixels[x][y] = img.getRGB(x, y);
				Depth[x][y] = 0;
				Group[x][y] = 0;
				//if the color is not magic pink
				if(Pixels[x][y] != -65281) {
					if(y >= lowestPixel[x]) lowestPixel[x] = y;
					if(highestPixel[x] < 0) highestPixel[x] = y;
				}
			}
		}
		
		for(int x = 0; x < cat.getWidth(); x++) {
			for(int y = 0; y < cat.getHeight(); y++) {
				if(Pixels[x][y] != -65281) {
					
					if(x != 0 && x != cat.getWidth() || x == 0 || x == cat.getWidth() - 1) {
						drawingNum++;
						if(touchingPink(x, y)) borderNum++; 
					}
				}
			}
		}
		
		forFaces1 = new String[drawingNum];
		forFaces2 = new String[drawingNum];
		
		drawingPixels = new String[drawingNum * 4];
		extrudedPixels = new String[drawingNum * 4];
		borderPixels = new String[borderNum];
	}
	
	public static void setValues() {
		
		int currDPixel = 0;
		int currBPixel = 0;
		int currFPixel = 0;
		
		for(int x = 0; x < cat.getWidth(); x++) {
			for(int y = 0; y < cat.getHeight(); y++) {
				if(Pixels[x][y] != -65281) {
					if(x != 0 && x != cat.getWidth() || x == 0 || x == cat.getWidth() - 1) {
						
						forFaces1[currFPixel] = "v " + (x) + " " + ((-y + cat.getHeight())) + " " + (-Depth[x][y] / 2);
						forFaces2[currFPixel] = "v " + (x) + " " + ((-y + cat.getHeight())) + " " +(Depth[x][y] / 2);
						
						drawingPixels[currDPixel] = "v " + (x + 0.5) + " " + ((-y + cat.getHeight()) + 0.5) + " " + (-Depth[x][y] / 2);
						drawingPixels[currDPixel + 1] = "v " + (x + 0.5) + " " + ((-y + cat.getHeight()) + -0.5) + " " + (-Depth[x][y] / 2);
						drawingPixels[currDPixel + 2] = "v " + (x + -0.5) + " " + ((-y + cat.getHeight()) + 0.5) + " " + (-Depth[x][y] / 2);
						drawingPixels[currDPixel + 3] = "v " + (x + -0.5) + " " + ((-y + cat.getHeight()) + -0.5) + " " + (-Depth[x][y] / 2);

						extrudedPixels[currDPixel] = "v " + (x + 0.5) + " " + ((-y + cat.getHeight()) + 0.5) + " " + (Depth[x][y] / 2);
						extrudedPixels[currDPixel + 1] = "v " + (x + 0.5) + " " + ((-y + cat.getHeight()) + -0.5) + " " + (Depth[x][y] / 2);
						extrudedPixels[currDPixel + 2] = "v " + (x + -0.5) + " " + ((-y + cat.getHeight()) + 0.5) + " " + (Depth[x][y] / 2);
						extrudedPixels[currDPixel + 3] = "v " + (x + -0.5) + " " + ((-y + cat.getHeight()) + -0.5) + " " + (Depth[x][y] / 2);
						
						currDPixel+=4;
						currFPixel+=1;
						if(touchingPink(x, y)) {
							borderPixels[currBPixel] = "v " + x + " " + (-y + cat.getHeight()) + " 0"; 
							currBPixel++;
						}
				}
				}
			}
		}
	}
	
	public static void finished() {
		JOptionPane.showMessageDialog(null, "Finished");
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Down = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		Click = arg0.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
		MouseX = arg0.getInput().getMouseX();
		MouseY = arg0.getInput().getMouseY();
		
		if(open.isMouseOver(arg0) && Click) openJChooser();
		if(save.isMouseOver(arg0) && Click) { Grouping.setGroups(); setValues(); SaveModel.writeFile(); }
		if(done.isMouseOver(arg0) && Click) { setDepths(); DrawDepth.calcHighest(); }
	
		if(Grouping.Modes.activeNum == 1) {
			if(MouseX < Main.WIDTH - 200 || MouseY > 200) {
				if(Click) { Highlighter.StartX = MouseX / 10; Highlighter.StartY = MouseY / 10; } 
				Highlighter.update(Down, MouseX, MouseY); 
				if(Down) FillHighlight = true;
			}
			
			if(!Down) FillHighlight = false;
		} 
		
		
		Grouping.update(arg0, arg1, arg2);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setBackground(Color.blue);
		g.scale(10, 10);
		
		g.setColor(Color.red);
		//if(FillHighlight)
		//g.setColor(Color.);
		//else
		g.draw(Highlighter.rect);
		
		if(started) {
			
			//cat.draw();
			for(int x = 0; x < cat.getWidth(); x++) {
				for(int y = 0; y < cat.getHeight(); y++) {
					//only want to draw if its touching the pink
					if(Pixels[x][y] != -65281) {
							if(x >= Highlighter.StartX && x < Highlighter.MouseX && y >= Highlighter.StartY && y < Highlighter.MouseY)
								g.setColor(Color.red);
							else if(x < Highlighter.StartX && x >= Highlighter.MouseX && y < Highlighter.StartY && y >= Highlighter.MouseY)
								g.setColor(Color.red);
							else 
								g.setColor(new Color(Pixels[x][y]));
							
							g.fillRect(x, y, 1, 1);
							}
							//make so it spits out a blender readable file
							//containing x and y information of each border pixel position
				}
			}
		}
		
		DrawDepth.render(arg0, arg1, g);
		g.resetTransform();
		Grouping.render(arg0, arg1, g);
		save.draw(arg0, g);
		open.draw(arg0, g);
		g.setColor(Color.darkGray);
		g.fillRect(Main.WIDTH - 195, 90, 180, 91);
		g.setColor(Color.white);
		g.drawString("depth: ", Main.WIDTH - 162, 90);
		textField.render(arg0, g);
		done.draw(arg0, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
