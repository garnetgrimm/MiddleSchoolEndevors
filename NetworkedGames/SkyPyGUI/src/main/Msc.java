package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.newdawn.slick.Color;

public class Msc {
	public static String getHexFromJSColor(String color) {
		try {
			InputStream in = Msc.class.getResourceAsStream("/Resources//colorList.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line = br.readLine();
			
			while(line != null) {
				String[] tokens = line.split("#");
				if(color.toUpperCase().equals(tokens[0].toUpperCase())) return tokens[1];
				line = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return color;
	}
	
	public static Color hexToColor(String colorStr) {
		
		String c = "#";
		c += colorStr;
		
	    return new Color(
	            Integer.valueOf( c.substring( 1, 3 ), 16 ),
	            Integer.valueOf( c.substring( 3, 5 ), 16 ),
	            Integer.valueOf( c.substring( 5, 7 ), 16 ) );
	}
}
