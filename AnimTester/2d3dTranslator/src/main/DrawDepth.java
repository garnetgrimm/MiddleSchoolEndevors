package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import States.StartUpState;

public class DrawDepth {
	
	public static int highestPoint = 1;
	public static int hexPoint = 0;
	public static String hexColor = "0x000000";
	
	public static int convert(int n) {
		  return Integer.valueOf(String.valueOf(n), 16);
	}
	
	public static void calcHighest() {
		for(int x = 0; x < StartUpState.cat.getWidth(); x++) {
			for(int y = 0; y < StartUpState.cat.getHeight(); y++) {
				if(StartUpState.Depth[x][y] > highestPoint) highestPoint = StartUpState.Depth[x][y];
			}
		}
		
		//possible %% by 16 in case depth is greater than 16
		if(highestPoint > 16) highestPoint = 16;
		if(highestPoint < 1) highestPoint = 1;
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		if(StartUpState.started) {			
					for(int x = 0; x < StartUpState.cat.getWidth(); x++) {
						for(int y = 0; y < StartUpState.cat.getHeight(); y++) {
							//only want to draw if its touching the pink
							if(StartUpState.Pixels[x][y] != -65281) {
									if(x >= Highlighter.StartX && x < Highlighter.MouseX && y >= Highlighter.StartY && y < Highlighter.MouseY)
										g.setColor(Color.red);
									else if(x < Highlighter.StartX && x >= Highlighter.MouseX && y < Highlighter.StartY && y >= Highlighter.MouseY)
										g.setColor(Color.red);
									else 
										//fix 
										hexPoint = convert(StartUpState.Depth[x][y] % highestPoint);
										hexColor = "#" + hexPoint + "0" + hexPoint + "0" + hexPoint + "0";
										
										g.setColor(Color.decode(hexColor));
									
										g.fillRect(x, y + StartUpState.cat.getHeight() + 10, 1, 1);
									}
						}
					}
				}
	}
	
}
