package main;

public class KeyInputReader {
	
	public static String[] Key;
	public static String[] Animation;
	
	public static boolean GetKeysRan = false;
	
	public static void GetKeys(String line) {
		String[] args = line.substring(10, line.length()).split(" ");
		
		Key = new String[args.length];
		Animation = new String[args.length];
		
		for(int i = 0; i < args.length; i++) {
			Key[i] = args[i].substring(0, 1);
			Animation[i] = args[i].substring(2, args[i].length());
		}	
		
		GetKeysRan = true;
	}

}
