package startupComponents;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.Button;
import Main.Server;
import Main.Slider;

public class ShipCreator {
	
	public static boolean firstClick = true;
	
	public static int maxPixels = 30;
	public static String[][] pixels = new String[maxPixels][maxPixels];
	private static int xOffset = 35;
	private static int yOffset = 15;
	public static String color = "0x000000";
	
	public static Slider red = new Slider(Color.red, "red", 250, 600, 16);
	public static Slider green = new Slider(Color.green, "green", 450, 600, 16);
	public static Slider blue = new Slider(Color.blue, "blue", 650, 600, 16);
	
	public static Button ready = new Button("Ready For Battle", (xOffset * 10) + 1, 480);
	public static boolean drawn = false;
	public static boolean readied = false;
	
	public static List<String> undoList = new ArrayList<String>(); 
	
	static int undoTime = 0;
	static boolean firstUndo = true;
	
	public static String convert(int n) {
		  if(n <= 9) return n + "";
		  else {
			  String returnVal = "";
			  if(n == 10) returnVal = "A";
			  if(n == 11) returnVal = "B";
			  if(n == 12) returnVal = "C";
			  if(n == 13) returnVal = "D";
			  if(n == 14) returnVal = "E";
			  if(n == 15) returnVal = "F";
			  return returnVal;
		  }
	}
	
	public static void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		for(int x = 0; x < maxPixels; x++) {
			for(int y = 0; y < maxPixels; y++) {
				pixels[x][y] = "0xGGGGGG";
			}
		}
		
		ready.setActive(false);
		
	}
	
	public static void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		
		
		boolean undo = (arg0.getInput().isKeyDown(Input.KEY_LCONTROL) && arg0.getInput().isKeyDown(Input.KEY_Z));
		
		red.update(arg0);
		green.update(arg0);
		blue.update(arg0);
		
		color = "0x" + convert(red.getVal()) + convert(red.getVal()) + convert(green.getVal()) + convert(green.getVal()) + convert(blue.getVal()) + convert(blue.getVal());
		
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		boolean Click = arg0.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		
		if(Click) {
			int currx = MouseX / 10 - (xOffset);
			int curry = MouseY / 10 - (yOffset);
			
			if(currx >= 0 && currx < maxPixels && curry >= 0 && curry < maxPixels) {
				pixels[currx][curry] = color;
				if(Color.decode(color) != Color.white) drawn = true;
				if(undoList.size() > 0) for(int i = 0; i < undoList.size() - 1; i++) { if(undoList.get(i).equals(currx + "," + curry)) undoList.remove(i); }
				undoList.add(currx + "," + curry);
			}
		}
		
		if(Click & ready.isMouseOver(arg0) && firstClick && ready.active) {
			readied = true;
			ready.setActive(false);
			firstClick = false;
		}
		
		if(!Click) firstClick = true;
		
		if(Server.started && drawn && !readied) ready.setActive(true);
		
		if(undo) undoTime++;
		else { undoTime = 0; firstUndo = true; }
		
		if(undo && undoTime > 50 || undo && firstUndo) {
			int x = 0;
			int y = 0;
			
			if(undoList.size() > 0) {
				String[] xy = undoList.get(undoList.size() - 1).split(",");
				x = Integer.parseInt(xy[0]);
				y = Integer.parseInt(xy[1]);
				undoList.remove(undoList.size() - 1);
				for(int i = 0; i < undoList.size() - 1; i++) {
					if(undoList.get(i).equals(undoList.get(undoList.size() - 1))) undoList.remove(i);
				}
			}
			pixels[x][y] = "0xFFFFFF";
			firstUndo = false;
		}
		
		if(undoList.size() > 120) undoList.remove(0);
		
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		
		g.resetTransform();
		g.scale(10, 10);
		
		g.setColor(Color.decode(color));
		g.fillRect(18, 57, 4, 4);
		g.setColor(Color.black);
		g.drawRect(18, 57, 4, 4);
		
		for(int x = 0; x < maxPixels; x++) {
			for(int y = 0; y < maxPixels; y++) {
				if(!pixels[x][y].equals("0xGGGGGG")) {
					g.setColor(Color.decode(pixels[x][y]));
					g.fillRect(x + xOffset, y + yOffset, 1, 1);
				}
			}
		}
		
		g.setColor(Color.black);
		
		g.drawRect(xOffset, yOffset, maxPixels, maxPixels);
		
		g.resetTransform();
		
		red.draw(arg0, g);
		green.draw(arg0, g);
		blue.draw(arg0, g);
		
		ready.draw(arg0, g);
	}
}
