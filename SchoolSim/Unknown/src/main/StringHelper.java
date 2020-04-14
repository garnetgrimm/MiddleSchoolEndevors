package main;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class StringHelper {
	public static int getWidth(Font font, String text) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		return fm.stringWidth(text);
	}
	
	  public static Font getFont(String name, int size) {
		    Font font = null;

		    String fName = "/Resources/" + name;
		    try {
		      InputStream is = StringHelper.class.getResourceAsStream(fName);
		      font = Font.createFont(Font.TRUETYPE_FONT, is);
		    } catch (Exception ex) {
		      ex.printStackTrace();
		      System.err.println(fName + " not loaded.  Using serif font.");
		      font = new Font("serif", Font.PLAIN, 24);
		    }
		    
		    font = font.deriveFont(Font.BOLD, size);
		    
		    return font;
		  }
}
