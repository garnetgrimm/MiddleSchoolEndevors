package main;

import org.newdawn.slick.geom.Rectangle;

public class Highlighter {
	public static int StartX = 0;
	public static int StartY = 0;
	public static int EndX = 0;
	public static int EndY = 0;
	public static int MouseX = 0;
	public static int MouseY = 0;
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	public static void update(boolean Down, int MouseX, int MouseY) {	
		
		//selection only works going from top to bottom
		//this is because of negitive ints i think
		
		if(Down) { 
			Highlighter.MouseX = MouseX / 10;
			Highlighter.MouseY = MouseY / 10;
			
			EndX = Highlighter.MouseX - StartX;
			EndY = Highlighter.MouseY - StartY;
		}
		
		rect = new Rectangle(StartX, StartY, EndX, EndY);
	}
	
}
