package templates;

import main.XlsHandler;

public class xTool {
	public static String[] TransType = null;
	public static String[] File_Receipt_Date = null;
	public static String[] Language_Preference = null;
	public static String[] Brand = null;
	public static String[] Employer_Group_ID = null;
	public static String[] Branch = null;
	public static String[] Bill_Group_Number = null;
	public static String[] Employer_Account_ID = null;
	public static String[] Effective_Date_of_Transaction = null;
	public static String[] Disenrollment_Date = null;
	public static String[] Disenrollment_Reason = null;
	public static String[] State_Pharmaceutical_Assistance_Program = null;
	public static String[] Medicare_Number = null;
	public static String[] Prefix = null;
	public static String[] First_Name = null;
	public static String[] Middle_Inital = null;
	public static String[] Last_Name = null;
	public static String[] Suffix = null;
	public static String[] DOB = null;
	public static String[] SSN = null;
	public static String[] Gender = null;
	public static String[] Address_Line_1 = null;
	public static String[] Address_Line_2 = null;
	public static String[] City = null;
	public static String[] State_code  = null;
	public static String[] Zip_Code = null;
	public static String[] Permanent_Address_Country_Cd = null;
	public static String[] Mailing_Address_Line_1 = null;
	public static String[] Mailing_Address_Line_2 = null;
	public static String[] Mailing_City = null;
	public static String[] Mailing_State = null;
	public static String[] Mailing_Zip_Code = null;
	public static String[] Mailing_Country_Cd = null;
	public static String[] Daytime_Phone_Number = null;
	public static String[] Daytime_Phone_Number_Extn= null;
	public static String[] Evening_Phone_Number = null;
	public static String[] Evening_Phone_Number_Extn = null;
	public static String[] Email = null;
	public static String[] Filler_1 = null;
	public static String[] Plan_Category = null;
	public static String[] Signature_Presence = null;
	public static String[] Signature_Date = null;
	public static String[] Credible_Coverage_Ind = null;
	public static String[] Institutionalized_Ind = null;
	public static String[] Hospice_Ind = null;
	public static String[] Medicaid_Ind = null;
	public static String[] ESRD_Ind = null;
	public static String[] Working_Age_Ind = null;
	public static String[] Medicare_Part_A_Effective_Date = null;
	public static String[] Medicare_Part_B_Effective_Date = null;
	public static String[] Secondary_Coverage_Indicator = null;
	public static String[] Secondary_RX_Bin = null;
	public static String[] Secondary_RX_ID = null;
	public static String[] Secondary_RX_PCN = null;
	public static String[] Secondary_RX_Group = null;
	public static String[] Election_Period = null;
	public static String[] Employers_name = null;
	public static String[] SEP_Reason = null;
	public static String[] Primary_Care_Physician = null;
	public static String[] Primary_Care_Physician_Number = null;
	public static String[] Are_you_currently_a_patient_of_the_PCP = null;
	public static String[] MPIN_Number = null;
	public static String[] ESRD_Member_of_health_care_company = null;
	public static String[] ESRD_Health_Care_Company = null;
	public static String[] Other_ESRD_Health_Insurance_ID = null;
	public static String[] Do_you_have_other_health_insurance = null;
	public static String[] Other_Health_Insurance_type = null;
	public static String[] Other_health_insurance_name = null;
	public static String[] Other_health_insurance_group_number = null;
	public static String[] Other_Health_Insurance_ID = null;
	public static String[] Filler_2 = null;
	
	public static void initStrings() {
		int ValueNums = XlsHandler.ValueNums;
		
		TransType = new String[ValueNums];
		File_Receipt_Date = new String[ValueNums];
		Language_Preference = new String[ValueNums];
		Brand = new String[ValueNums];
		Employer_Group_ID = new String[ValueNums];
		Branch = new String[ValueNums];
		Bill_Group_Number = new String[ValueNums];
		Employer_Account_ID = new String[ValueNums];
		Effective_Date_of_Transaction = new String[ValueNums];
		Disenrollment_Date = new String[ValueNums];
		Disenrollment_Reason = new String[ValueNums];
		State_Pharmaceutical_Assistance_Program = new String[ValueNums];
		Medicare_Number = new String[ValueNums];
		Prefix = new String[ValueNums];
		First_Name = new String[ValueNums];
		Middle_Inital = new String[ValueNums];
		Last_Name = new String[ValueNums];
		Suffix = new String[ValueNums];
		DOB = new String[ValueNums];
		SSN = new String[ValueNums];
		Gender = new String[ValueNums];
		Address_Line_1 = new String[ValueNums];
		Address_Line_2 = new String[ValueNums];
		City = new String[ValueNums];
		State_code  = new String[ValueNums];
		Zip_Code = new String[ValueNums];
		Permanent_Address_Country_Cd = new String[ValueNums];
		Mailing_Address_Line_1 = new String[ValueNums];
		Mailing_Address_Line_2 = new String[ValueNums];
		Mailing_City = new String[ValueNums];
		Mailing_State = new String[ValueNums];
		Mailing_Zip_Code = new String[ValueNums];
		Mailing_Country_Cd = new String[ValueNums];
		Daytime_Phone_Number = new String[ValueNums];
		Daytime_Phone_Number_Extn = new String[ValueNums];
		Evening_Phone_Number = new String[ValueNums];
		Evening_Phone_Number_Extn = new String[ValueNums];
		Email = new String[ValueNums];
		Filler_1 = new String[ValueNums];
		Plan_Category = new String[ValueNums];
		Signature_Presence = new String[ValueNums];
		Signature_Date = new String[ValueNums];
		Credible_Coverage_Ind = new String[ValueNums];
		Institutionalized_Ind = new String[ValueNums];
		Hospice_Ind = new String[ValueNums];
		Medicaid_Ind = new String[ValueNums];
		ESRD_Ind = new String[ValueNums];
		Working_Age_Ind = new String[ValueNums];
		Medicare_Part_A_Effective_Date = new String[ValueNums];
		Medicare_Part_B_Effective_Date = new String[ValueNums];
		Secondary_Coverage_Indicator = new String[ValueNums];
		Secondary_RX_Bin = new String[ValueNums];
		Secondary_RX_ID = new String[ValueNums];
		Secondary_RX_PCN = new String[ValueNums];
		Secondary_RX_Group = new String[ValueNums];
		Election_Period = new String[ValueNums];
		Employers_name = new String[ValueNums];
		SEP_Reason = new String[ValueNums];
		Primary_Care_Physician = new String[ValueNums];
		Primary_Care_Physician_Number = new String[ValueNums];
		Are_you_currently_a_patient_of_the_PCP = new String[ValueNums];
		MPIN_Number = new String[ValueNums];
		ESRD_Member_of_health_care_company = new String[ValueNums];
		ESRD_Health_Care_Company = new String[ValueNums];
		Other_ESRD_Health_Insurance_ID = new String[ValueNums];
		Do_you_have_other_health_insurance = new String[ValueNums];
		Other_Health_Insurance_type = new String[ValueNums];
		Other_health_insurance_name = new String[ValueNums];
		Other_health_insurance_group_number = new String[ValueNums];
		Other_Health_Insurance_ID = new String[ValueNums];
		Filler_2 = new String[ValueNums];
	}
}
