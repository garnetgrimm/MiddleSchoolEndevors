package startupComponents;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.gameState;
import Main.init;

public class UsersList {
	
	public static List<String> ClientList = new ArrayList<String>();
	public static List<String> ReadyList = new ArrayList<String>();
	
	public static int[] readiedPeople = new int[2];
	
	public static boolean entered = false;
	
	public static void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {

	}
	
	public static void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {	
		int[] readiedPeople = new int[2];
		readiedPeople[0] = -1;
		readiedPeople[1] = -1;
		int currPerson = 0;
		
		for(int i = 0; i < ReadyList.size(); i++) {
			if(ReadyList.get(i).equals("true")) {
				readiedPeople[currPerson] = i;
				currPerson++;
			}
			if(currPerson >= 2) break;
		}
		
		if(readiedPeople[0] != -1 && readiedPeople[1] != -1) {
			if(readiedPeople[0] == init.clientNum || readiedPeople[1] == init.clientNum) {
				gameState.client1 = readiedPeople[0];
				gameState.client2 = readiedPeople[1];
				entered = true;
				arg1.enterState(1);
			}
		}
	}
	
	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawRect(0, 50, 150, Main.init.Height - 50);
		g.setColor(Color.gray);
		g.fillRect(0, 50, 150, Main.init.Height - 50);
		g.setColor(Color.black);
		g.setFont(arg0.getDefaultFont());
		for(int i = 0; i < ClientList.size(); i++) {
			String s = ClientList.get(i);
			if(s.length() > 15) {
				s = s.substring(0, 12);
				s += "...";
			}
			
			g.drawString(s, 5, 50 + (i * 20));
		}
	}
}
