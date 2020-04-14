package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;

import templates.RRADemo;
import templates.RRAEnroll;

public class XlsWriter {

	static HSSFWorkbook wb = new HSSFWorkbook();
	static HSSFSheet sheet = wb.createSheet("RRADemographic");
	static Row row = sheet.createRow(0);
	public static int NumVals = 0;

	public static void writeRRADemo(String savePath) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("RRADemographic");
		Row row = sheet.createRow(0);

		row.createCell(0).setCellValue("EmployeeIdentifier");
		row.createCell(1).setCellValue("LastName");
		row.createCell(2).setCellValue("FirstName");
		row.createCell(3).setCellValue("DateOfBirth");
		row.createCell(4).setCellValue("AddressLine1");
		row.createCell(5).setCellValue("AddressLine2");
		row.createCell(6).setCellValue("City");
		row.createCell(7).setCellValue("State");
		row.createCell(8).setCellValue("ZipCode");
		row.createCell(9).setCellValue("Country");
		row.createCell(10).setCellValue("HomePhone ");
		row.createCell(11).setCellValue("EmployeeNumber");
		row.createCell(12).setCellValue("EmployerEmployeeID");
		row.createCell(13).setCellValue("SSN");
		row.createCell(14).setCellValue("Division");
		row.createCell(15).setCellValue("Class");
		row.createCell(16).setCellValue("Payroll");
		row.createCell(17).setCellValue("PayrollEffectiveDate");
		row.createCell(18).setCellValue("EmploymentStatus");
		row.createCell(19).setCellValue("StatusEffectiveDate");
		row.createCell(20).setCellValue("HireDate");
		row.createCell(21).setCellValue("IncurServices");
		row.createCell(22).setCellValue("HoldPayrollDeductions");
		row.createCell(23).setCellValue("HoldEmployerContributions");

		Row[] rows = new Row[XlsHandler.ValueNums];
		
		for (int r = 0; r < XlsHandler.ValueNums; r++) {
			
			int i = r + 1;
			rows[r] = sheet.createRow(i);
			
			rows[r].createCell(0).setCellValue(RRADemo.EmployeeIdentifier[r]);
			rows[r].createCell(1).setCellValue(RRADemo.LastName[r]);
			rows[r].createCell(2).setCellValue(RRADemo.FirstName[r]);
			rows[r].createCell(3).setCellValue(RRADemo.DateOfBirth[r]);
			rows[r].createCell(4).setCellValue(RRADemo.AddressLine1[r]);
			rows[r].createCell(5).setCellValue(RRADemo.AddressLine2[r]);
			rows[r].createCell(6).setCellValue(RRADemo.City[r]);
			rows[r].createCell(7).setCellValue(RRADemo.State[r]);
			rows[r].createCell(8).setCellValue(RRADemo.ZipCode[r]);
			rows[r].createCell(9).setCellValue(RRADemo.Country[r]);
			rows[r].createCell(10).setCellValue(RRADemo.HomePhone[r]);
			rows[r].createCell(11).setCellValue(RRADemo.EmployeeNumber[r]);
			rows[r].createCell(12).setCellValue(RRADemo.EmployerEmployeeID[r]);
			rows[r].createCell(13).setCellValue(RRADemo.SSN[r]);
			rows[r].createCell(14).setCellValue(RRADemo.Division[r]);
			rows[r].createCell(15).setCellValue(RRADemo.Class[r]);
			rows[r].createCell(16).setCellValue(RRADemo.Payroll[r]);
			rows[r].createCell(17).setCellValue(RRADemo.PayrollEffectiveDate[r]);
			rows[r].createCell(18).setCellValue(RRADemo.EmploymentStatus[r]);
			rows[r].createCell(19).setCellValue(RRADemo.StatusEffectiveDate[r]);
			rows[r].createCell(20).setCellValue(RRADemo.HireDate[r]);
			rows[r].createCell(21).setCellValue(RRADemo.IncurServices[r]);
			rows[r].createCell(22).setCellValue(RRADemo.HoldPayrollDeductions[r]);
			rows[r].createCell(23).setCellValue(RRADemo.HoldEmployerContributions[r]);
		}
		FileOutputStream out = new FileOutputStream(new File(savePath));
		wb.write(out);
		out.close();
		wb.close();
	}
	
	public static void writeRRAEnroll(String savePath) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("RRAEnrollment");
		Row row = sheet.createRow(0);

		row.createCell(0).setCellValue("EmployeeIdentifier");
		row.createCell(1).setCellValue("ElectionAmount");
		row.createCell(2).setCellValue("PlanName");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue("EnrollmentEffectiveDate");
		row.createCell(5).setCellValue("EmployerContribution");
		
		Row[] rows = new Row[XlsHandler.ValueNums];
		
		for (int r = 0; r < XlsHandler.ValueNums; r++) {
			
			int i = r + 1;
			rows[r] = sheet.createRow(i);
			
			rows[r].createCell(0).setCellValue(RRAEnroll.EmployeeIdentifier[r]);
			rows[r].createCell(1).setCellValue(RRAEnroll.ElectionAmount[r]);
			rows[r].createCell(2).setCellValue(RRAEnroll.PlanName[r]);
			rows[r].createCell(3).setCellValue("");
			rows[r].createCell(4).setCellValue(RRAEnroll.EnrollmentEffectiveDate[r]);
			rows[r].createCell(5).setCellValue(RRAEnroll.EmployerContribution[r]);
		}
		FileOutputStream out = new FileOutputStream(new File(savePath));
		wb.write(out);
		out.close();
		wb.close();
	}

	// row.createCell(0).setCellValue("EmployeeIdentifier");
	HSSFCell cell;
}
