package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import States.StartUpState;

public class Grouping {
	
	public static List<Rectangle> sectors = new ArrayList<Rectangle>();
	public static List<Tuple> cords = new ArrayList<Tuple>();
	public static boolean currDown = false;
	public static Button ClearBoxes = new Button("Clear Groups", Main.WIDTH - 237, 565);
	private static String[] options = { "Grouping", "Depth" };
	public static OptionBoxTick Modes = new OptionBoxTick("Select Mode", Main.WIDTH - 230, 475, options);
	
	public static int[] groupLine = null;
	public static String[] groupString = null;
	
	public static void setGroups() {
		for(int x = 0; x < StartUpState.cat.getWidth(); x++) {
			for(int y = 0; y < StartUpState.cat.getHeight(); y++) {
				for(int i = 0; i < sectors.size(); i++) {
					int xStart = (int) (cords.get(i).getX() / 10);
					int yStart = (int) (cords.get(i).getY() / 10);
					int width = (int) sectors.get(i).getWidth();
					int height = (int) sectors.get(i).getHeight();
					
					boolean xPositive = true;
					boolean yPositive = true;
					
					if(width < 0) {  xPositive = false; }
					if(height < 0) { yPositive = false; }
					
					boolean group = false;
					
					if(!xPositive && !yPositive && x < xStart && x > xStart + width && y < yStart && y > yStart + height) group = true;
					if(xPositive && yPositive && x > xStart && x < xStart + width && y > yStart && y < yStart + height) group = true;
					if(!xPositive && yPositive && x < xStart && x > xStart + width && y > yStart && y < yStart + height) group = true;
					if(xPositive && !yPositive && x > xStart && x < xStart + width && y < yStart && y > yStart + height) group = true;
					
					if(group) {
						StartUpState.Group[x][y] = i + 1;
					}
					
				}
			}
		}
	}
	
	public static String[] sortFaces(String[] faces) {
		
		//AVG FINDS CENTER OF FACE, IF CENTER FACE IS WITHIN GROUPING BOX IT GETS GROUPED
		
		for(int i = 0; i < faces.length; i++) {
			
			if(!(faces[i] == null)) {
				
				double xavg = 0;
				double yavg = 0;
				
				for(int v = 0; v < 4; v++) {
					double x = Transform.getX(Transform.getVertFromFace(faces[i], v));
					double y = Transform.getY(Transform.getVertFromFace(faces[i], v));
					xavg+=x;
					yavg+=y;
				}
				
				xavg /= 4;
				yavg /= 4;
				
				System.out.println(i + ":" + xavg + " " + yavg);
				
			}		
		}
		return faces;
	}
	
	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		int MouseX = arg0.getInput().getMouseX();
		int MouseY = arg0.getInput().getMouseY();
		
		for(int i = 0; i < sectors.size(); i++) {
			int width = (int) sectors.get(i).getWidth();
			int height = (int) sectors.get(i).getHeight();
			if(width == 0 && !currDown || height == 0 && !currDown) { sectors.remove(i); cords.remove(i); }
		}
		
		for(int i = 0; i < options.length; i++) {
			if(Modes.isMouseOver(arg0, i) && StartUpState.Click) Modes.setActive(i);
		}
		
		if(StartUpState.Click) {
			if(ClearBoxes.isMouseOver(arg0)) {
				while(sectors.size() > 0) sectors.remove(sectors.size() - 1);
				while(cords.size() > 0) cords.remove(cords.size() - 1);
			}
			else {
				if(Modes.activeNum == 0) {
					cords.add(new Tuple((double)MouseX, (double) MouseY, 0.0));
					sectors.add(new Rectangle(1,1,10,10));
					currDown = true;		
				}
			}
		}
		
		if(!StartUpState.Down) currDown = false;
		
		if(currDown) {
			int StartX = (int) cords.get(cords.size() - 1).getX() / 10;
			int StartY = (int) cords.get(cords.size() - 1).getY() / 10;
			sectors.set(sectors.size() - 1, new Rectangle(StartX, StartY, (MouseX / 10) - StartX, (MouseY / 10) - StartY));
		}
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		
		g.scale(10, 10);
		g.setColor(Color.green);
		
		for(int i = 0; i < sectors.size(); i++) {
			g.draw(sectors.get(i));
		}
		g.resetTransform();
		
		for(int i = 0; i < sectors.size(); i++) {
			g.drawString(i + "", (int) cords.get(i).getX(), (int) cords.get(i).getY());
		}
		
		ClearBoxes.draw(arg0, g);
		Modes.draw(arg0, g);
	}
}
