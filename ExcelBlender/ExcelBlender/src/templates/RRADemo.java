package templates;

import main.XlsHandler;

public class RRADemo {
	public static String[] EmployeeIdentifier = null;
	public static String[] LastName = null;
	public static String[] FirstName = null;
	public static String[] DateOfBirth = null;
	public static String[] AddressLine1 = null;
	public static String[] AddressLine2 = null;
	public static String[] City = null;
	public static String[] State = null;
	public static String[] ZipCode = null;
	public static String[] Country = null;
	public static String[] HomePhone = null;
	public static String[] EmployeeNumber = null;
	public static String[] EmployerEmployeeID = null;
	public static String[] SSN = null;
	public static String[] Division = null;
	public static String[] Class = null;
	public static String[] Payroll = null;
	public static String[] PayrollEffectiveDate = null;
	public static String[] EmploymentStatus = null;
	public static String[] StatusEffectiveDate = null;
	public static String[] HireDate = null;
	public static String[] IncurServices = null;
	public static String[] HoldPayrollDeductions = null;
	public static String[] HoldEmployerContributions = null;
	
	public static void initStrings() {
		int ValueNums = XlsHandler.ValueNums;
		
		EmployeeIdentifier = new String[ValueNums];
		LastName = new String[ValueNums];
		FirstName = new String[ValueNums];
		DateOfBirth = new String[ValueNums];
		AddressLine1 = new String[ValueNums];
		AddressLine2 = new String[ValueNums];
		City = new String[ValueNums];
		State = new String[ValueNums];;
		ZipCode = new String[ValueNums];
		Country = new String[ValueNums];
		HomePhone = new String[ValueNums];
		EmployeeNumber = new String[ValueNums];
		EmployerEmployeeID = new String[ValueNums];
		SSN = new String[ValueNums];
		Division = new String[ValueNums];
		Class = new String[ValueNums];
		Payroll = new String[ValueNums];
		PayrollEffectiveDate = new String[ValueNums];
		EmploymentStatus = new String[ValueNums];
		StatusEffectiveDate = new String[ValueNums];
		HireDate = new String[ValueNums];
		IncurServices = new String[ValueNums];
		HoldPayrollDeductions = new String[ValueNums];
		HoldEmployerContributions = new String[ValueNums];
	}
	
}
