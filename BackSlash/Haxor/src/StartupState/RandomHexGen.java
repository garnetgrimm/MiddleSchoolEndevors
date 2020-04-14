package StartupState;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class RandomHexGen {

	public int xPos = 0;
	public int yPos = 0;
	private static int length;
	private static Random random = new Random();
	private static int[] HexValue; 
	private static char[] RandomChar;
	
	public RandomHexGen(int x, int y, int l) {
		xPos = x;
		yPos = y;
		length = l;
		HexValue = new int[l];
		RandomChar = new char[l];
	}
	
	public static void update() {		
		for(int i = 0; i < length; i++) {
		HexValue[i] = random.nextInt(16);
		if(HexValue[i] == 0) { RandomChar[i] = '0'; }
		if(HexValue[i] == 1) { RandomChar[i] = '1'; }
		if(HexValue[i] == 2) { RandomChar[i] = '2'; }
		if(HexValue[i] == 3) { RandomChar[i] = '3'; }
		if(HexValue[i] == 4) { RandomChar[i] = '4'; }
		if(HexValue[i] == 5) { RandomChar[i] = '5'; }
		if(HexValue[i] == 6) { RandomChar[i] = '6'; }
		if(HexValue[i] == 7) { RandomChar[i] = '7'; }
		if(HexValue[i] == 8) { RandomChar[i] = '8'; }
		if(HexValue[i] == 9) { RandomChar[i] = '9'; }
		if(HexValue[i] == 10) { RandomChar[i] = 'a'; }
		if(HexValue[i] == 11) { RandomChar[i] = 'b'; }
		if(HexValue[i] == 12) { RandomChar[i] = 'c'; }
		if(HexValue[i] == 13) { RandomChar[i] = 'd'; }
		if(HexValue[i] == 14) { RandomChar[i] = 'e'; }
		if(HexValue[i] == 15) { RandomChar[i] = 'f'; }
		}
	}
	
	public void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		update();
		
		for(int i = 0; i < length; i++) {
			g.drawString(RandomChar[i] + "", xPos, yPos + (i * 15));
		}
		
		for(int i = 0; i < 19; i++) {
			g.drawString(RandomChar[i] + "", xPos - 30, (yPos + 40) + (i * 15));
		}
		
		for(int i = 0; i < length; i++) {
			g.drawString(RandomChar[i] + "", xPos + 450, (yPos - 8) + (i * 15));
		}
		
		for(int i = 0; i < 19; i++) {
			g.drawString(RandomChar[i] + "", xPos + 575, (yPos + 240) + (i * 15));
		}
		
		for(int i = 0; i < 17; i++) {
			g.drawString(RandomChar[i] + "", xPos + 180 + (i * 15), (yPos + 450));
		}
		
	} 
	
}
