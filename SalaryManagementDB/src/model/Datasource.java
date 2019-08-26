package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

	public static final String DB_NAME = "SalaryManagement.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

	/*
	 * In order to be able to distinguish between columns that have similar
	 * names I put in front of their names the initials of the table they're
	 * coming from so DGT stands for DESIGNATION_GROUP_TABLE and so on
	 */
	public static final String DESIGNATION_GROUP_TABLE = "Designation_Group";
	public static final String DGT_DESIGNATION_COULMN = "Designation";
	public static final String DGT_SALARY_GROUP_COLUMN = "Salary_Group";
	public static final int DGT_INDEX_DESIGNATION_COLUMN = 1;
	public static final int DGT_INDEX_SALARY_GROUP_COLUMN = 2;

	public static final String EMPLOYEE_TABLE = "Employee";
	public static final String ET_ID_COLUMN = "Id";
	public static final String ET_FIRST_NAME_COLUMN = "First_Name";
	public static final String ET_LAST_NAME_COLUMN = "Last_Name";
	public static final String ET_DESIGNATION_COULMN = "Designation";
	public static final String ET_EXPERIENCE_COULMN = "Experience";
	public static final int ET_INDEX_ID_COLUMN = 1;
	public static final int ET_INDEX_FIRST_NAME_COLUMN = 2;
	public static final int ET_INDEX_LAST_NAME_COLUMN = 3;
	public static final int ET_INDEX_DESIGNATION_COLUMN = 4;
	public static final int ET_INDEX_EXPERIENCE_COLUMN = 5;

	public static final String SALARY_RANGE_TABLE = "Salary_Range";
	public static final String SRT_SALARY_GROUP_COLUMN = "Salary_Group";
	public static final String SRT_EXPERIENCE_COULMN = "Experience";
	public static final String SRT_RANGE_COULMN = "Range";
	public static final int SRT_INDEX_SALARY_GROUP_COLUMN = 1;
	public static final int SRT_INDEX_EXPERIENCE_COLUMN = 2;
	public static final int SRT_INDEX_RANGE_COLUMN = 3;

	public static final String SALARY_TABLE = "Salary";
	public static final String ST_EMPLOYEE_ID_COLUMN = "Employee_Id";
	public static final String ST_CURRENT_SALARY_COLUMN = "Current_Salary";
	public static final int ST_INDEX_ID_COLUMN = 1;
	public static final int ST_INDEX_CURRENT_SALARY_COLUMN = 2;

	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;

	public static final String QUERY_EMPLOYEES = "SELECT * FROM "
			+ EMPLOYEE_TABLE;

	public static final String QUERY_EMPLOYEE_BY_LAST_NAME = "SELECT * FROM "
			+ EMPLOYEE_TABLE + " WHERE " + ET_LAST_NAME_COLUMN + "= ?";

	public static final String QUERY_EMPLOYEE_BY_ID = "SELECT * FROM "
			+ EMPLOYEE_TABLE + " WHERE " + ET_ID_COLUMN + "= ?";

	public static final String QUERY_EMPLOYEE_SALARY = "SELECT "
			+ ST_CURRENT_SALARY_COLUMN + " FROM " + SALARY_TABLE + " WHERE "
			+ ST_EMPLOYEE_ID_COLUMN + " = ?";

	/*
	 * SELECT Employee.Id, Employee.First_Name, Employee.Last_Name,
	 * Employee.Designation, Employee.Experience, Salary.Current_Salary,
	 * Salary_Range.Range FROM Employee INNER JOIN Salary ON Salary.Employee_Id
	 * = Employee.Id INNER JOIN Designation_Group ON
	 * Designation_Group.Designation = Employee.Designation INNER JOIN
	 * Salary_Range ON Salary_Range.Salary_Group =
	 * Designation_Group.Salary_Group AND Salary_Range.Experience =
	 * Employee.Experience WHERE Employee.Last_Name = "Johnson" AND Employee.
	 * First_Name = 'Emma';
	 */
	public static final String QUERY_EMPLOYEE_ALL_INFORMATION = "SELECT "
			+ EMPLOYEE_TABLE + "." + ET_ID_COLUMN + ", " + EMPLOYEE_TABLE + "."
			+ ET_FIRST_NAME_COLUMN + ", " + EMPLOYEE_TABLE + "."
			+ ET_LAST_NAME_COLUMN + ", " + EMPLOYEE_TABLE + "."
			+ ET_DESIGNATION_COULMN + ", " + EMPLOYEE_TABLE + "."
			+ ET_EXPERIENCE_COULMN + ", " + SALARY_TABLE + "."
			+ ST_CURRENT_SALARY_COLUMN + ", " + SALARY_RANGE_TABLE + "."
			+ SRT_RANGE_COULMN + " FROM " + EMPLOYEE_TABLE + " INNER JOIN "
			+ SALARY_TABLE + " ON " + SALARY_TABLE + "."
			+ ST_EMPLOYEE_ID_COLUMN + " = " + EMPLOYEE_TABLE + "."
			+ ET_ID_COLUMN + "INNER JOIN " + DESIGNATION_GROUP_TABLE + " ON "
			+ DESIGNATION_GROUP_TABLE + "." + DGT_DESIGNATION_COULMN + " = "
			+ EMPLOYEE_TABLE + "." + ET_DESIGNATION_COULMN + "INNER JOIN "
			+ SALARY_RANGE_TABLE + " ON " + SALARY_RANGE_TABLE + "."
			+ SRT_SALARY_GROUP_COLUMN + " = " + DESIGNATION_GROUP_TABLE + "."
			+ DGT_DESIGNATION_COULMN + " AND " + SALARY_RANGE_TABLE + "."
			+ SRT_EXPERIENCE_COULMN + " = " + EMPLOYEE_TABLE + "."
			+ ET_EXPERIENCE_COULMN + " WHERE " + EMPLOYEE_TABLE + "."
			+ ET_LAST_NAME_COLUMN + "=? AND " + EMPLOYEE_TABLE + "."
			+ ET_FIRST_NAME_COLUMN + "=?";

	public static final String INSERT_EMPLOYEE = "INSERT INTO "
			+ EMPLOYEE_TABLE + " ( " + ET_FIRST_NAME_COLUMN + ", "
			+ ET_LAST_NAME_COLUMN + ", " + ET_DESIGNATION_COULMN + ", "
			+ ET_EXPERIENCE_COULMN + ") VALUES ( =?, =?, =?, =?)";

	public static final String UPDATE_EMPLOYEE_LAST_NAME = "UPDATE "
			+ EMPLOYEE_TABLE + " SET " + ET_LAST_NAME_COLUMN + "= ? WHERE "
			+ ET_LAST_NAME_COLUMN + "=? AND " + ET_FIRST_NAME_COLUMN + "=?";

	public static final String UPDATE_EMPLOYEE_DESIGNATION = "UPDATE "
			+ EMPLOYEE_TABLE + " SET " + ET_DESIGNATION_COULMN + "= ? WHERE "
			+ ET_LAST_NAME_COLUMN + "=? AND " + ET_FIRST_NAME_COLUMN + "=?";

	public static final String UPDATE_EMPLOYEE_EXPERIENCE = "UPDATE "
			+ EMPLOYEE_TABLE + " SET " + ET_EXPERIENCE_COULMN + "= ? WHERE "
			+ ET_LAST_NAME_COLUMN + "=? AND " + ET_FIRST_NAME_COLUMN + "=?";

	private Connection conn;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			return true;

		} catch (SQLException e) {
			System.out.println("Couldn't connect to database: "
					+ e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Unable to close the database: "
					+ e.getMessage());
		}
	}

	public List<Employee> queryEmployees(int sortOrder) {

		StringBuilder sb = new StringBuilder(QUERY_EMPLOYEES);

		if (sortOrder != ORDER_BY_NONE) {
			sb.append(" ORDER BY ");
			sb.append(ET_LAST_NAME_COLUMN);
			sb.append(" COLLATE NOCASE ");
			if (sortOrder == ORDER_BY_DESC) {
				sb.append("DESC");
			} else {
				sb.append("ASC");
			}
		}

		try (Statement statement = conn.createStatement();
				ResultSet results = statement.executeQuery(sb.toString())) {

			List<Employee> employeeList = new ArrayList<>();

			while (results.next()) {
				Employee newEmployee = new Employee();
				newEmployee.setId(results.getInt(ET_INDEX_ID_COLUMN));
				newEmployee.setFirstName(results
						.getString(ET_INDEX_FIRST_NAME_COLUMN));
				newEmployee.setLastName(results
						.getString(ET_INDEX_LAST_NAME_COLUMN));
				newEmployee.setDesignation(results
						.getString(ET_INDEX_DESIGNATION_COLUMN));
				newEmployee.setExperience(results
						.getInt(ET_INDEX_EXPERIENCE_COLUMN));

				employeeList.add(newEmployee);
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Unable to query employees" + e.getMessage());
			return null;

		}
	}

	public List<Employee> getEmployeeByLastName(String lastName) {
		try (Statement statement = conn.createStatement();
				ResultSet results = statement
						.executeQuery(QUERY_EMPLOYEE_BY_LAST_NAME)) {
			List<Employee> employeeList = new ArrayList<>();

			while (results.next()) {
				Employee newEmployee = new Employee();
				newEmployee.setId(results.getInt(ET_INDEX_ID_COLUMN));
				newEmployee.setFirstName(results
						.getString(ET_INDEX_FIRST_NAME_COLUMN));
				newEmployee.setLastName(results
						.getString(ET_INDEX_LAST_NAME_COLUMN));
				newEmployee.setDesignation(results
						.getString(ET_INDEX_DESIGNATION_COLUMN));
				newEmployee.setExperience(results
						.getInt(ET_INDEX_EXPERIENCE_COLUMN));

				employeeList.add(newEmployee);
			}
			return employeeList;

		} catch (SQLException e) {
			System.out.println("Unable to search employee by last name: "
					+ e.getMessage());
			return null;
		}
	}
	
	public void queryEmployeeMetaData(){
		try(Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(QUERY_EMPLOYEES)){
			
			ResultSetMetaData meta = result.getMetaData();
			int numColumns = meta.getColumnCount();
			for(int i = 1; i <= numColumns; i++){
				System.out.format("Column %d in Employee table is named %s\n",
						i, meta.getColumnName(i));
			}
			
		}catch(SQLException e){
			System.out.println("Query failed: " + e.getMessage());
		}
	}
}
