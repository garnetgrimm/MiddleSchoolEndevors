package main;

public class Date {
	public static String switchLayout(String oldDate) {
		
		//YYYYMMDD 20161220
		//MMDDYYYY 12202016
		
		int year = Integer.parseInt(oldDate.substring(oldDate.length() - 4, oldDate.length()));
		
		if(year > 1300) {
			System.out.println("MMDDYYYY Layout Detected");
			System.out.println("Converting to YYYYMMDD Layout");
			
			String y = oldDate.substring(oldDate.length() - 4, oldDate.length());
			String m = oldDate.substring(0, 2);
			String d = oldDate.substring(2, 4);
			
			String newDate = y + m + d;
			
			System.out.println("Success!");
			return newDate;
			
		} else {
			System.out.println("YYYYMMDD Layout Detected");
			System.out.println("Converting to MMDDYYYY Layout");
			
			String y = oldDate.substring(0, 4);
			String m = oldDate.substring(4, 6);
			String d = oldDate.substring(6, 8);
			
			String newDate = m + d + y;
			
			System.out.println("Success!");
			return newDate;
		}
	}
}
