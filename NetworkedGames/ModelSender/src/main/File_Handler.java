package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class File_Handler {

	public static String file_to_string(File file) {
		String lines = "";
		
		try {
			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while(line != null) {
				line += "*_BREAK_*";
				line = url_encode(line);
				lines += line;
				line = br.readLine();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	static String url_encode(String line) {
		line = line.replace(" ", "*_SPAC_*");
		line = line.replace("<", "*_L_SH_*");
		line = line.replace(">", "*_R_SH_*");
		line = line.replace("/", "*_F_SL_*");
		line = line.replace("#", "*_HSHT_*");
		line = line.replace("%", "*_PRCT_*");
		line = line.replace(";", "*_SMCN_*");
		line = line.replace("?", "*_QUST_*");
		//dissallowed
		//"<" | ">" | "#" | "%" | <">
		//reserved
		//";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" | "$" | ","
		//possible issues
		//"{" | "}" | "|" | "\" | "^" | "[" | "]" | "`"
		return line;
	}

}
