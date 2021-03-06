package GameState;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import Modding.ModReader;
import Msc.EnemyStats;

public class ConsoleType {
	
	static Font font;
	public static TextField textField;
	public static String currLine = "";

	public static boolean Message = false;
	private static int UnkY = 0;
	private static int MessageTimer = 0;
	private static String lastCommand = "";
	private static String prevCommand = "";
	
	public static String CurrMessage = "Unknown Command";
	
    public static void init(GameContainer gc, StateBasedGame sb) {
    	  font = new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.ITALIC, 26));
    	  textField = new TextField(gc, gc.getDefaultFont(), 160, 50, 650, 20); 
    	  textField.setBorderColor(Color.black);
    	  textField.setFocus(true);
    	  textField.setText("");
    } 
    
	public static void update(GameContainer container, StateBasedGame menu, int delta) throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ENTER) && textField.hasFocus() && UnkY == 0) { currLine = textField.getText(); readCurrLine(currLine); }
		if(container.getInput().isKeyPressed(Input.KEY_UP)) { prevCommand = textField.getText(); textField.setText(lastCommand); }
		if(container.getInput().isKeyPressed(Input.KEY_DOWN)) { textField.setText(prevCommand); }
		if(Message && MessageTimer <= 0) UnkY++;
		if(UnkY >= 30) MessageTimer++;
		if(MessageTimer >= 30) { Message = false; }
		if(MessageTimer > 0 && !Message) { UnkY--; MessageTimer--; }
	
	}
    
	
    public static void readCurrLine(String line) {
		ModReader.checkModCommand(line);
    	lastCommand = line;
    	CurrMessage = "Unknown Command";
    	String randCountry = "";
    	for(int i = 0; i < Attacks.difficulty; i++)
    	{ randCountry = Attacks.country[Attacks.randomCountry[i]]; }
    	
    	if(line.equals("PyDos.exe")) { line = "dos " + randCountry + " 20"; }
    	if(line.equals("BatDos.exe")) { line = "dos " + randCountry + " 10"; }
    	
    	String[] tokens = line.split(" ");
    	for(int i = 0; i < tokens.length; i++) {
    		Attacks.checkDos(tokens, i);
    	}
    	
    	if(line.equals("Bruite.exe")) { Attacks.bruiteForce(); CurrMessage = "Started Bruite.exe"; }
    	if(line.equals("DictAttack.exe")) { Attacks.dictionaryAttack(); CurrMessage = "Started DictAttack.exe";}
    	
    	if(line.equals("trace") && Attacks.traceTime <= 1) { Attacks.trace(); CurrMessage = "Tracing enemy ip.."; }
    	if(line.equals("trace") && Attacks.traceTime > 1) { CurrMessage = "Command already in use"; }
    	if(line.equals("clear") && !Attacks.runningProcess) { Attacks.clear(); CurrMessage = "Terminal cleared"; }
    	if(line.equals("clear") && Attacks.runningProcess) { CurrMessage = "Cannot clear terminal"; }
    	if(line.equals("get enemy password")) { GameState.drawnPassword = EnemyStats.enemyPassword; }
    	
    	Message = true;
    	
    	textField.setText("");
    }
	
    public static void render(GameContainer gc, StateBasedGame sb, Graphics g) {
        textField.render(gc, g);
        if(UnkY > 0) { g.drawString(CurrMessage, 60, UnkY - 15); }
    }
    
}