package GameState;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Msc.EnemyStats;

public class Attacks {
	
	private static Random random = new Random();
	
	public static boolean firstTrace = true;
	public static boolean Tracing = false;
	public static boolean Dosing = false;
	public static boolean Bruiting = false;
	public static boolean DictAttacking = false;
	
	private static int traceResultsY = 0;
	public static int traceTime = 0;
	private static int maxTraceTime = 500;
	private static int halfMaxTrace = maxTraceTime / 2;
	private static boolean traceActive = false;
	public static int difficulty = 1;
	public static String[] country = { "USA", "Canada", "Alaska", "Mexico", "Brazil", "United Kingdom", "East Russia", "West Russia", "Austrailia", "Egypt", "South Africa" }; 
	private static boolean wasFirewallOn = false;
	public static int[] randomCountry = new int[country.length];
	
	private static String toKill = "";
	
	public static boolean runningProcess = false;
	
	public static void init() {
		for(int i = 0; i < randomCountry.length; i++) {
			randomCountry[i] = random.nextInt(5); 
		}
	}
	
	public static void checkDos(String[] tokens, int i) {
		if(firstTrace == true) { ConsoleType.CurrMessage = "Invalid Args: Trace first"; }
		else {
			if(tokens[0].equals("dos") && tokens.length != 3) { ConsoleType.CurrMessage = "Invalid Args"; }
	    	if(tokens[0].equals("dos") && tokens.length == 3)
	    	{
	    		boolean ServerFound = false;
				for(int n = 0; n < difficulty; n++) {
					if(tokens[1].toUpperCase().equals(country[randomCountry[n]].toUpperCase())) { toKill = country[randomCountry[n]]; ConsoleType.CurrMessage = "Killing server: " + toKill; ServerFound = true; }
				}
	
				int parsedInt = 0;
				boolean parsed = false;
				
				try { parsedInt = Integer.parseInt(tokens[2]); parsed = true; } 
				catch(NumberFormatException e) {} 
				
				if(parsed && ServerFound) {
					if(parsedInt > 0 && parsedInt <= 10) { ConsoleType.CurrMessage = "BatDos Started On " + toKill; }
					if(parsedInt > 10 && parsedInt <= 20) { ConsoleType.CurrMessage = "PyDos Started On " + toKill; }
				}
				if(!parsed || !ServerFound || parsedInt > 20 || parsedInt < 0) { System.out.println(parsedInt + " " + ServerFound + " " + parsed); ConsoleType.CurrMessage = "Invalid Args"; }
		    }
    	}
    }
	
	public static void dos(int level) {
		Dosing = true;
	}
	
	public static void bruiteForce() {
		Bruiting = true;
	}
	
	public static void dictionaryAttack() {
		DictAttacking = true;
	}
	
	public static void trace() {
		clear();
		wasFirewallOn = EnemyStats.IsFirewallOn;
		Tracing = true;
		traceActive = true;
	}
	
	public static void clear() {
		Tracing = false;
		traceTime = 0;
		maxTraceTime = 500;
		difficulty = 1;
	}
	
	public static void update() {
		
		//HAVE ALL RUNNING TASKS HERE
		if(traceActive) runningProcess = true;
		else runningProcess = false;
		
		if(Tracing) {
			if(traceActive) traceTime++;
			if(traceTime == halfMaxTrace && wasFirewallOn)  { difficulty += 1; maxTraceTime = maxTraceTime * 2; }
			if(traceTime >= maxTraceTime) { traceActive = false; firstTrace = false;}
		}
	}
	
	public static void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		if(Tracing) { g.drawString("> Trace Started", 10, 80); }
		if(Tracing && traceTime >= 500 && wasFirewallOn) { traceResultsY = 20; g.drawString("> Trace warning: Enemy is using vpn", 10, 100); }
		if(Tracing && traceTime >= maxTraceTime) { 
			g.drawString("> Enemy trace completed:", 10, 100 + traceResultsY);
			for(int i = 0; i < difficulty; i++) {
				g.drawString(country[randomCountry[i]], 20, 120 + (i * 20) + traceResultsY);
			}
		}
	}
	
}
