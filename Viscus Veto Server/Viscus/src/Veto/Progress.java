package Veto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import States.IntroState;

public abstract class Progress {

	//CANNOT ALTER JAR FILE, MUST FIND WORKAROUND
	public static String SAVE_PATH = ("Save");
	
    private static File file = new File(SAVE_PATH);
	private static FileInputStream inputStream;
	
	public static int Data;
		
	public static void access() throws IOException {
		
		inputStream = new FileInputStream(SAVE_PATH);
		
	    	try {
	    	    String SaveData = IOUtils.toString(inputStream);
	    	    Data = Integer.parseInt(SaveData);
	    	} finally {
	    	    inputStream.close();
	    	}
	}
	
	public static void save() throws IOException
	{//save	
		  try {
		    String content = Integer.toString(IntroState.x);
		    FileWriter fw = new FileWriter(file.getAbsoluteFile());
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(content);
		    bw.close();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		}
	
}
