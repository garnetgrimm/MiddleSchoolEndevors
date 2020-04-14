package main;

import org.newdawn.slick.GameContainer;

import States.WorkShopState;

public class HexColor {
	
	public static int R = 0;
	public static int G = 0;
	public static int B = 0;
	
	private static String rawR = "0";
	private static String rawG = "0";
	private static String rawB = "0";
	
	public static void update(GameContainer arg0, int arg1) {
		
		rawR = WorkShopState.textField[0].getText();
		rawG = WorkShopState.textField[1].getText();
		rawB = WorkShopState.textField[2].getText();

		System.out.println(rawR);
		
		try{ R = Integer.parseInt(rawR); }
		catch(Exception e) {}
		try { G = Integer.parseInt(rawG); }
		catch(Exception e) {}
		try { B = Integer.parseInt(rawB); }
		catch(Exception e) {}

		
	}
	
}
