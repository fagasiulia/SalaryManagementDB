package tables;

public class Constants {
	
	public static final String DB_NAME = "SalaryManagement.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
	
	/*
	 * In order to be able to distinguish between columns that have similar names I
	 * put in front of their names the initials of the table they're coming from
	 * so DGT stands for DESIGNATION_GROUP_TABLE and so on
	 */
	public static final String DESIGNATION_GROUP_TABLE = "Designation_Group";
	public static final String DGT_DESIGNATION_COULMN = "Designation";
	public static final String DGT_SALARY_GROUP_COLUMN = "Salary_Group";
	
	public static final String EMPLOYEE_TABLE = "Employee";
	public static final String ET_ID_COLUMN = "Id";
	public static final String ET_FIRST_NAME_COLUMN = "First_Name";
	public static final String ET_LAST_NAME_COLUMN = "Last_Name";
	public static final String ET_DESIGNATION_COULMN = "Designation";
	public static final String ET_EXPERIENCE_COULMN = "Experience";
	
	
	public static final String SALARY_RANGE_TABLE = "Salary_Range";
	public static final String SRT_SALARY_GROUP_COLUMN = "Salary_Group";
	public static final String SRT_EXPERIENCE_COULMN = "Experience";
	public static final String SRT_RANGE_COULMN = "Range";
	
	public static final String SALARY_TABLE = "Salary";
	public static final String ST_EMPLOYEE_ID_COLUMN = "Employee_Id";
	public static final String ST_CURRENT_SALARY_COLUMN = "Current_Salary";
	
	
}
