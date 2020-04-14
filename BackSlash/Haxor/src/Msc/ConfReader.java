package Msc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class ConfReader {

	// CANNOT ALTER JAR FILE, MUST FIND WORKAROUND
	public static String SAVE_PATH = ("settings/ipDefaults.conf");
	public static String FOLDER_PATH = ("settings");

	private static FileReader fr1;
	private static FileReader fr2;

	public static String[] ips;
	public static String[] names;
	public static int lines = 0;
	public static int Data;
	
	public static void init() {

		try {
		File f = new File(FOLDER_PATH);
		if(!f.exists()) { (f).mkdir(); System.out.println("A new settings folder was created."); }
		else System.out.println("Settings file found.");
		}
		catch(Exception e) { System.out.println("Config folder does not exist."); }
		
		try {
			fr1 = new FileReader(SAVE_PATH);
			fr2 = new FileReader(SAVE_PATH);
			BufferedReader br = new BufferedReader(fr1);
			BufferedReader lineCounter = new BufferedReader(fr2);

			String b = lineCounter.readLine();

			while (b != null) {
				lines++;
				b = lineCounter.readLine();
			}

			if (lines > 0) {
				ips = new String[lines];
				names = new String[lines];

				for (int i = 0; i < lines; i++) {
					String s = br.readLine();
					String[] tokens = s.split(" = ");
					names[i] = tokens[0];
					ips[i] = tokens[1];
				}
			}
		} catch (IOException e) {
			System.out.println("Config file not found.");
			try {
				File f = new File(SAVE_PATH);
				f.createNewFile();
				System.out.println("A new Config file was created.");
			} catch (Exception e1) {
				System.out.println("A new Config file could not be created.");
			}

		}
	}

}
