package main;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class XlsHandler {
	
	public static int findStartSheet(HSSFWorkbook hsswb) {
		try {
			@SuppressWarnings("unused")
			HSSFSheet sheetProbe = hsswb.getSheetAt(1);
		} catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
	@SuppressWarnings("deprecation")
	public static void readFile(File f) {
		try {
		    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(f));
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    //FOR XTOOL WB.GETSHEETAT(1) //FOR RRA DEMO WB.GETSHEETAT(0)
		    HSSFSheet sheet = wb.getSheetAt(XlsHandler.findStartSheet(wb));
		    //HSSFSheet sheet = wb.getSheetAt(1);
		    HSSFRow row;
		    HSSFCell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }

		    for(int r = 0; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                    // Your code here
		                	System.out.println(cell.toString());
		                }
		            }
		        }
		    }
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	}
}
