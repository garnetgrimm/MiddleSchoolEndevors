package main;

public class Date {
	public static String switchLayout(String oldDate) {
		
		//YYYYMMDD 20161220
		//MMDDYYYY 12202016
		int year = 0;
		
		try {
		year = Integer.parseInt(oldDate.substring(oldDate.length() - 4, oldDate.length()));
		} catch(Exception e) {
			return "";
		}
		
		
		if(year > 1300) {

			String y = oldDate.substring(oldDate.length() - 4, oldDate.length());
			String m = oldDate.substring(0, 2);
			String d = oldDate.substring(2, 4);
			
			String newDate = y + m + d;
			
			return newDate;
			
		} else {
			String y = oldDate.substring(0, 4);
			String m = oldDate.substring(4, 6);
			String d = oldDate.substring(6, 8);
			
			String newDate = m + d + y;
			
			return newDate;
		}
	}
}
