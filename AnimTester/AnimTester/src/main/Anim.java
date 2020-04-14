package main;

public class Anim {

	public static int currNumY = 0;
	public static int currNumX = 0;
	public static String[] animNames;
	public static String[] animVals;
	
	public static int[] currOrder;
	public static float inc = 0f;
	public static int delta = 0;

	public static boolean Started = false;
	
	
	public static int getX(int num) {	
		int X = 0;
		X = (num % Main.XNumOfSprites);
		return X;
	}

	public static int getY(int num) {		
		int Y = 0;
		Y = (num / Main.XNumOfSprites);	
		return Y;
	}
	
	public static void update() {
		
		inc += Main.Speed;
		if(inc >= 1) { inc = 0; delta++; }
		
		try {
			if(currOrder != null) { 
				if(delta >= currOrder.length) delta = 0;
				currNumX = ((currOrder[delta] - 1) % Main.XNumOfSprites);
				currNumY = ((currOrder[delta] - 1) / Main.XNumOfSprites);
			}
		} catch(Exception e) {
			
		}
		
	}
	
	public static void getAnims(String line) {
		String[] tempAnims = line.substring(7, line.length()).split(" ");
		
		animNames = new String[tempAnims.length];
		animVals = new String[tempAnims.length];
		
		for(int i = 0; i < tempAnims.length; i++) {
		
		String[] forSorting = tempAnims[i].split("=");	
		
		animNames[i] = forSorting[0];
		animVals[i] = forSorting[1];
		
		Started = true;
		
		}
	}
	
	public static void callAnim(String animName) {
		for(int i = 0; i < animNames.length; i++) {
			
			if(animName.equals(animNames[i])) {
				String[] order = animVals[i].split(",");
				
				currOrder = new int[order.length];
				
				for(int a = 0; a < order.length; a++) {
					currOrder[a] = Integer.parseInt(order[a]);
				}
				
			}
		}
		update();
	}
	
}
