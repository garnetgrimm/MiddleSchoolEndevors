package PasswordState;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

public class PasswordBox {
	
	public static boolean passReady = false;
	static Font font;
	public static TextField passField;
	public static TextField SipField;
	public static TextField CipField;
	
    public static void init(GameContainer gc, StateBasedGame sb) {
    	  font = new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.ITALIC, 26));
    	  passField = new TextField(gc, gc.getDefaultFont(), 190 + PasswordState.disXPos, 115, 40, 25);
    	  //passField.setBackgroundColor(Color.black);
    	  passField.setMaxLength(4);
    	  passField.setText("XXXX");    
    	  
    	  SipField = new TextField(gc, gc.getDefaultFont(), 365 + PasswordState.disXPos, 85, 130, 25);
    	  SipField.setText("localhost");  
    	  
    	  CipField = new TextField(gc, gc.getDefaultFont(), 365 + PasswordState.disXPos, 145, 130, 25);
    	  CipField.setText("localhost");  
    } 
    
    public static void update(GameContainer gc, StateBasedGame sb) {
       
    }
    
    public static void render(GameContainer gc, StateBasedGame sb, Graphics g) {
        update(gc, sb);
        passField.render(gc, g);
        SipField.render(gc, g);
        CipField.render(gc, g);
    }
    
}