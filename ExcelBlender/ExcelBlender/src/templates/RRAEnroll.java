package templates;

import main.XlsHandler;

public class RRAEnroll {
	public static String[] EmployeeIdentifier = null;
	public static String[] ElectionAmount = null;
	public static String[] PlanName = null;
	public static String[] EnrollmentEffectiveDate = null;
	public static String[] EmployerContribution = null;
	
	public static void initStrings() {
		int ValueNums = XlsHandler.ValueNums;
		
		EmployeeIdentifier = new String[ValueNums];
		ElectionAmount = new String[ValueNums];
		PlanName = new String[ValueNums];
		EnrollmentEffectiveDate = new String[ValueNums];
		EmployerContribution = new String[ValueNums];
	}
}
