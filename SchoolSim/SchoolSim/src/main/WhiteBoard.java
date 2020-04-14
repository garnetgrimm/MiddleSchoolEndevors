package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class WhiteBoard {
	public static int boardWidth = 32;
	public static int boardHeight = 26;

	public static int[][] boardPixs = new int[32][26];
	

	public static void init() {
		for(int x = 0; x < boardWidth; x++) {
			for(int y = 0; y < boardHeight; y++) {
			boardPixs[x][y] = 0;
			}
		}
	}
	
	public static void drawPix(int mosX, int mosY) {
		boardPixs[mosX - 27][mosY - 5] = 1;
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		for(int x = 0; x < boardWidth; x++) {
			for(int y = 0; y < boardHeight; y++) {
				if(boardPixs[x][y] == 1) g.fillRect(x + 27, y + 5, 1, 1);
			}
		}
	}
}
