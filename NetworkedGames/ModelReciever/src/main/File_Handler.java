package main;

import java.util.ArrayList;
import java.util.List;

public class File_Handler {
	
	static String url_decode(String line) {
		line = line.replace("*_SPAC_*", " ");
		line = line.replace("*_L_SH_*", "<");
		line = line.replace("*_R_SH_*", ">");
		line = line.replace("*_F_SL_*", "/");
		line = line.replace("*_HSHT_*", "#");
		line = line.replace("*_PRCT_*", "%");
		line = line.replace("*_SMCN_*", ";");
		line = line.replace("*_QUST_*", "?");
		line = line.replace("*_BREAK_*", "\n");
		//dissallowed
		//"<" | ">" | "#" | "%" | <">
		//reserved
		//";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" | "$" | ","
		//possible issues
		//"{" | "}" | "|" | "\" | "^" | "[" | "]" | "`"
		return line;
	}
	
	static List<Arg> json_to_args(String json) {
		json = json.substring(1, json.length() - 2);
		
		String[] tokens = json.split("],");
		List<Arg> args = new ArrayList<Arg>();
		
		for(int i = 0; i < tokens.length; i++) {
			String curr_token = tokens[i];
			curr_token = curr_token.replace("[", ",  ");
			curr_token = curr_token.replace(" ", "");
			curr_token = curr_token.replace("\"", "");
			curr_token = curr_token.replace(":", "");
			String[] vals = curr_token.split(",");
			
			args.add(new Arg(vals));
		}
		
		return args;
	}

}
