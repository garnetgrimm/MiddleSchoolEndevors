package main;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import templates.RRADemo;
import templates.RRAEnroll;
import templates.xTool;

public class XlsHandler {
	
	public static int ValueNums = 0;
	public static int currRow = 0;
	
	public static void addxTooltoRRAE() {
		for(int i = 0; i < ValueNums; i++) {
			//completely untested no idea tired by
			if(xTool.TransType[i].equals("E")) {
				
				RRAEnroll.EmployerContribution[currRow] = "4000.00";
				RRAEnroll.ElectionAmount[currRow] = "0.00";
				RRAEnroll.PlanName[currRow] = "Retiree Reimb Acct";
				
				
				RRAEnroll.EmployeeIdentifier[currRow] = xTool.SSN[i];
				//what date is this??
				RRAEnroll.EnrollmentEffectiveDate[currRow] = Date.switchLayout(xTool.Effective_Date_of_Transaction[i]);
				
				currRow++;
			
			}
		}
	}
	
	public static void addxTooltoRRAD() {
		RRADemo.LastName = xTool.Last_Name;
		RRADemo.FirstName = xTool.First_Name;
		for(int i = 0; i < ValueNums; i++)
		RRADemo.DateOfBirth[i] = Date.switchLayout(xTool.DOB[i]);
		RRADemo.AddressLine1 = xTool.Address_Line_1;
		RRADemo.AddressLine2 = xTool.Address_Line_2;
		RRADemo.City = xTool.City;
		RRADemo.State = xTool.State_code;
		RRADemo.ZipCode = xTool.Zip_Code;
		RRADemo.SSN = xTool.SSN;
		RRADemo.EmployeeIdentifier = xTool.SSN;
		RRADemo.HomePhone = xTool.Daytime_Phone_Number;
		RRADemo.Country = new String[ValueNums];
		RRADemo.StatusEffectiveDate = xTool.Disenrollment_Date;
		
		for(int i = 0; i < ValueNums; i++) {
			RRADemo.Country[i] = "US";
		}
	}
	
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
		    int TYPE = XlsHandler.findStartSheet(wb);
		    HSSFSheet sheet = wb.getSheetAt(TYPE);
		    //HSSFSheet sheet = wb.getSheetAt(1);
		    HSSFRow row;
		    HSSFCell cell;

		    int highestRow = 0;
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
		                	if(!cell.toString().equals("")) highestRow = r;
		                }
		            }
		        }
		    }
		    
		    ValueNums = highestRow;
		    xTool.initStrings();
		    RRADemo.initStrings();
		    RRAEnroll.initStrings();

		    for(int r = 0; r < highestRow + 1; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                    // Your code here
		                	//System.out.println(r + " " + c);
		                	if(TYPE == 0) {
		                		
		                	}
		                	if(TYPE == 1) {
		                		
		                		int currRow = r - 1;
		                		
								if(r >= 1 && c == 0) xTool.TransType[currRow] = cell.toString();
			                	if(r >= 1 && c == 1) xTool.File_Receipt_Date[currRow] = cell.toString();
			                	if(r >= 1 && c == 2) xTool.Language_Preference[currRow] = cell.toString();
			                	if(r >= 1 && c == 3) xTool.Brand[currRow] = cell.toString();
			                	if(r >= 1 && c == 4) xTool.Employer_Group_ID[currRow] = cell.toString();
			                	if(r >= 1 && c == 5) xTool.Branch[currRow] = cell.toString();
			                	if(r >= 1 && c == 6) xTool.Bill_Group_Number[currRow] = cell.toString();
			                	if(r >= 1 && c == 7) xTool.Employer_Account_ID[currRow] = cell.toString();
			                	if(r >= 1 && c == 8) xTool.Effective_Date_of_Transaction[currRow] = cell.toString();
			                	if(r >= 1 && c == 9) xTool.Disenrollment_Date[currRow] = cell.toString();
			                	if(r >= 1 && c == 10) xTool.Disenrollment_Reason[currRow] = cell.toString();
			                	if(r >= 1 && c == 11) xTool.State_Pharmaceutical_Assistance_Program[currRow] = cell.toString();
			                	if(r >= 1 && c == 12) xTool.Medicare_Number[currRow] = cell.toString();
			                	if(r >= 1 && c == 13) xTool.Prefix[currRow] = cell.toString();
			                	if(r >= 1 && c == 14) xTool.First_Name[currRow] = cell.toString();
			                	if(r >= 1 && c == 15) xTool.Middle_Inital[currRow] = cell.toString();
			                	if(r >= 1 && c == 16) xTool.Last_Name[currRow] = cell.toString();
			                	if(r >= 1 && c == 17) xTool.Suffix[currRow] = cell.toString();
			                	if(r >= 1 && c == 18) xTool.DOB[currRow] = cell.toString();
			                	if(r >= 1 && c == 19) xTool.SSN[currRow] = cell.toString();
			                	if(r >= 1 && c == 20) xTool.Gender[currRow] = cell.toString();
			                	if(r >= 1 && c == 21) xTool.Address_Line_1[currRow] = cell.toString();
			                	if(r >= 1 && c == 22) xTool.Address_Line_2[currRow] = cell.toString();
			                	if(r >= 1 && c == 23) xTool.City[currRow] = cell.toString();
			                	if(r >= 1 && c == 24) xTool.State_code[currRow] = cell.toString();
			                	if(r >= 1 && c == 25) xTool.Zip_Code[currRow] = cell.toString();
			                	if(r >= 1 && c == 26) xTool.Permanent_Address_Country_Cd[currRow] = cell.toString();
			                	if(r >= 1 && c == 27) xTool.Mailing_Address_Line_1[currRow] = cell.toString();
			                	if(r >= 1 && c == 28) xTool.Mailing_Address_Line_2[currRow] = cell.toString();
			                	if(r >= 1 && c == 29) xTool.Mailing_City[currRow] = cell.toString();
			                	if(r >= 1 && c == 30) xTool.Mailing_State[currRow] = cell.toString();
			                	if(r >= 1 && c == 31) xTool.Mailing_Zip_Code[currRow] = cell.toString();
			                	if(r >= 1 && c == 32) xTool.Mailing_Country_Cd[currRow] = cell.toString();
			                	if(r >= 1 && c == 33) xTool.Daytime_Phone_Number[currRow] = cell.toString();
			                	if(r >= 1 && c == 34) xTool.Daytime_Phone_Number_Extn[currRow] = cell.toString();
			                	if(r >= 1 && c == 35) xTool.Evening_Phone_Number[currRow] = cell.toString();
			                	if(r >= 1 && c == 36) xTool.Evening_Phone_Number_Extn[currRow] = cell.toString();
			                	if(r >= 1 && c == 37) xTool.Email[currRow] = cell.toString();
			                	if(r >= 1 && c == 38) xTool.Filler_1[currRow] = cell.toString();
			                	if(r >= 1 && c == 39) xTool.Plan_Category[currRow] = cell.toString();
			                	if(r >= 1 && c == 40) xTool.Signature_Presence[currRow] = cell.toString();
			                	if(r >= 1 && c == 41) xTool.Signature_Date[currRow] = cell.toString();
			                	if(r >= 1 && c == 42) xTool.Credible_Coverage_Ind[currRow] = cell.toString();
			                	if(r >= 1 && c == 43) xTool.Institutionalized_Ind[currRow] = cell.toString();
			                	if(r >= 1 && c == 44) xTool.Hospice_Ind[currRow] = cell.toString();
			                	if(r >= 1 && c == 45) xTool.Medicaid_Ind[currRow] = cell.toString();
			                	if(r >= 1 && c == 46) xTool.ESRD_Ind[currRow] = cell.toString();
			                	if(r >= 1 && c == 47) xTool.Working_Age_Ind[currRow] = cell.toString();
			                	if(r >= 1 && c == 48) xTool.Medicare_Part_A_Effective_Date[currRow] = cell.toString();
			                	if(r >= 1 && c == 49) xTool.Medicare_Part_B_Effective_Date[currRow] = cell.toString();
			                	if(r >= 1 && c == 50) xTool.Secondary_RX_Bin[currRow] = cell.toString();
			                	if(r >= 1 && c == 51) xTool.Secondary_RX_ID[currRow] = cell.toString();
			                	if(r >= 1 && c == 52) xTool.Secondary_RX_PCN[currRow] = cell.toString();
			                	if(r >= 1 && c == 53) xTool.Secondary_RX_Group[currRow] = cell.toString();
			                	if(r >= 1 && c == 54) xTool.Election_Period[currRow] = cell.toString();
			                	if(r >= 1 && c == 55) xTool.Employers_name[currRow] = cell.toString();
			                	if(r >= 1 && c == 56) xTool.SEP_Reason[currRow] = cell.toString();
			                	if(r >= 1 && c == 57) xTool.Primary_Care_Physician[currRow] = cell.toString();
			                	if(r >= 1 && c == 58) xTool.Primary_Care_Physician_Number[currRow] = cell.toString();
			                	if(r >= 1 && c == 59) xTool.Are_you_currently_a_patient_of_the_PCP[currRow] = cell.toString();
			                	if(r >= 1 && c == 60) xTool.MPIN_Number[currRow] = cell.toString();
			                	if(r >= 1 && c == 61) xTool.ESRD_Member_of_health_care_company[currRow] = cell.toString();
			                	if(r >= 1 && c == 62) xTool.ESRD_Health_Care_Company[currRow] = cell.toString();
			                	if(r >= 1 && c == 63) xTool.Other_ESRD_Health_Insurance_ID[currRow] = cell.toString();
			                	if(r >= 1 && c == 64) xTool.Do_you_have_other_health_insurance[currRow] = cell.toString();
			                	if(r >= 1 && c == 65) xTool.Other_Health_Insurance_type[currRow] = cell.toString();
			                	if(r >= 1 && c == 66) xTool.Other_health_insurance_name[currRow] = cell.toString();
			                	if(r >= 1 && c == 67) xTool.Other_health_insurance_group_number[currRow] = cell.toString();
			                	if(r >= 1 && c == 68) xTool.Other_Health_Insurance_ID[currRow] = cell.toString();
			                	if(r >= 1 && c == 69) xTool.Filler_2[currRow] = cell.toString();               	

		                	}
		                	
		                }
		            }
		        }
		    }
		    addxTooltoRRAE();
		    addxTooltoRRAD();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	}
}
