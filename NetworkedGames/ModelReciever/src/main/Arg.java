package main;

public class Arg {
	
	public String name = "";
	public String[] args = null;
	
	public Arg(String[] vals) {
		name = vals[0];
		args = new String[vals.length - 1];
		for(int i = 0; i < vals.length - 1; i++) {
			args[i] = vals[i + 1];
		}
	}
}
