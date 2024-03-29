package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			+ EMPLOYEE_TABLE + " ORDER BY " + ET_LAST_NAME_COLUMN
			+ " COLLATE NOCASE =?";

	public static final String QUERY_EMPLOYEE_BY_LAST_NAME = "SELECT * FROM "
			+ EMPLOYEE_TABLE + " WHERE " + ET_LAST_NAME_COLUMN + " = ?";

	public static final String QUERY_EMPLOYEE_BY_FULL_NAME = "SELECT * FROM "
			+ EMPLOYEE_TABLE + " WHERE " + ET_LAST_NAME_COLUMN + " = ? AND "
			+ ET_FIRST_NAME_COLUMN + "=?";

	public static final String QUERY_EMPLOYEE_BY_ID = "SELECT * FROM "
			+ EMPLOYEE_TABLE + " WHERE " + ET_ID_COLUMN + " = ?";

	public static final String QUERY_EMPLOYEE_SALARY = "SELECT "
			+ ST_CURRENT_SALARY_COLUMN + " FROM " + SALARY_TABLE
			+ " INNER JOIN " + EMPLOYEE_TABLE + " ON " + EMPLOYEE_TABLE + "."
			+ ET_ID_COLUMN + " = " + SALARY_TABLE + "." + ST_EMPLOYEE_ID_COLUMN
			+ " WHERE " + ET_LAST_NAME_COLUMN + " = ? AND "
			+ ET_FIRST_NAME_COLUMN + "=?";
	
	public static final String QUERY_EMPLOYEE_SALARY_BASED_ON_ID = "SELECT * FROM " + SALARY_TABLE
			+ " WHERE " + ST_EMPLOYEE_ID_COLUMN + " = ?";
	
	public static final String QUERY_DESIGNATION_GROUP = "SELECT * FROM " + DESIGNATION_GROUP_TABLE 
			+ " WHERE " + DGT_DESIGNATION_COULMN + " = ?";
	
	public static final String QUERY_SALARY_RANGE = "SELECT * FROM " + SALARY_RANGE_TABLE 
			+ " WHERE " + SRT_SALARY_GROUP_COLUMN + " = ? AND " + SRT_EXPERIENCE_COULMN
			+ "=?";

	/*
	 * SELECT Employee.Id, Employee.First_Name, Employee.Last_Name,
	 * Employee.Designation, Employee.Experience, Salary.Current_Salary,
	 * Salary_Range.Range FROM Employee INNER JOIN Salary ON Salary.Employee_Id =
	 * Employee.Id INNER JOIN Designation_Group ON Designation_Group.Designation =
	 * Employee.Designation INNER JOIN Salary_Range ON Salary_Range.Salary_Group =
	 * Designation_Group.Salary_Group AND Salary_Range.Experience =
	 * Employee.Experience WHERE Employee.Last_Name = "Johnson" AND Employee.
	 * First_Name = "Emma";
	 */
	
	/*
	 * public static final String QUERY_EMPLOYEE_ALL_INFORMATION = "SELECT " +
	 * EMPLOYEE_TABLE + "." + ET_ID_COLUMN + ", " + EMPLOYEE_TABLE + "." +
	 * ET_FIRST_NAME_COLUMN + ", " + EMPLOYEE_TABLE + "." + ET_LAST_NAME_COLUMN +
	 * ", " + EMPLOYEE_TABLE + "." + ET_DESIGNATION_COULMN + ", " + EMPLOYEE_TABLE +
	 * "." + ET_EXPERIENCE_COULMN + ", " + SALARY_TABLE + "." +
	 * ST_CURRENT_SALARY_COLUMN + ", " + SALARY_RANGE_TABLE + "." + SRT_RANGE_COULMN
	 * + " FROM " + EMPLOYEE_TABLE + " INNER JOIN " + SALARY_TABLE + " ON " +
	 * SALARY_TABLE + "." + ST_EMPLOYEE_ID_COLUMN + " = " + EMPLOYEE_TABLE + "." +
	 * ET_ID_COLUMN + " INNER JOIN " + DESIGNATION_GROUP_TABLE + " ON " +
	 * DESIGNATION_GROUP_TABLE + "." + DGT_DESIGNATION_COULMN + " = " +
	 * EMPLOYEE_TABLE + "." + ET_DESIGNATION_COULMN + " INNER JOIN " +
	 * SALARY_RANGE_TABLE + " ON " + SALARY_RANGE_TABLE + "." +
	 * SRT_SALARY_GROUP_COLUMN + " = " + DESIGNATION_GROUP_TABLE + "." +
	 * DGT_DESIGNATION_COULMN + " AND " + SALARY_RANGE_TABLE + "." +
	 * SRT_EXPERIENCE_COULMN + " = " + EMPLOYEE_TABLE + "." + ET_EXPERIENCE_COULMN +
	 * " WHERE " + EMPLOYEE_TABLE + "." + ET_LAST_NAME_COLUMN + "=? AND " +
	 * EMPLOYEE_TABLE + "." + ET_FIRST_NAME_COLUMN + "=?";
	 */
	
	public static final String QUERY_EMPLOYEE_ID = "SELECT " + ET_ID_COLUMN
			+ " FROM " + EMPLOYEE_TABLE + " WHERE " + ET_LAST_NAME_COLUMN
			+ "=? AND " + ET_FIRST_NAME_COLUMN + "=?";

//	INSERT INTO Employee (First_Name, Last_Name, Designation, Experience) SELECT =?, =? , =?, =?
//	WHERE NOT EXISTS (SELECT 1 FROM Employee WHERE First_Name = ? AND Last_Name = ?)
//	public static final String INSERT_EMPLOYEE = "INSERT INTO "
//			+ EMPLOYEE_TABLE + " ( " + ET_FIRST_NAME_COLUMN + ", "
//			+ ET_LAST_NAME_COLUMN + ", " + ET_DESIGNATION_COULMN + ", "
//			+ ET_EXPERIENCE_COULMN + ") SELECT ?, ?, ?, ? "
//			+ "WHERE NOT EXISTS (SELECT 1 FROM " + EMPLOYEE_TABLE 
//			+ " WHERE " + ET_FIRST_NAME_COLUMN + "=? AND "
//			+ ET_LAST_NAME_COLUMN + "=?";
	public static final String INSERT_EMPLOYEE = "INSERT INTO "
			+ EMPLOYEE_TABLE + " ( " + ET_FIRST_NAME_COLUMN + ", "
			+ ET_LAST_NAME_COLUMN + ", " + ET_DESIGNATION_COULMN + ", "
			+ ET_EXPERIENCE_COULMN + ") VALUES ( ?, ?, ?, ?)";

	public static final String INSERT_SALARY = "INSERT INTO " + SALARY_TABLE
			+ "( " + ST_EMPLOYEE_ID_COLUMN + ", " + ST_CURRENT_SALARY_COLUMN
			+ ") VALUES (?, ?)";

	public static final String UPDATE_EMPLOYEE_LAST_NAME = "UPDATE "
			+ EMPLOYEE_TABLE + " SET " + ET_LAST_NAME_COLUMN + "= ? WHERE "
			+ ET_LAST_NAME_COLUMN + "=? AND " + ET_FIRST_NAME_COLUMN + " = ?";

	public static final String UPDATE_EMPLOYEE_DESIGNATION = "UPDATE "
			+ EMPLOYEE_TABLE + " SET " + ET_DESIGNATION_COULMN + "= ? WHERE "
			+ ET_LAST_NAME_COLUMN + "=? AND " + ET_FIRST_NAME_COLUMN + "=?";

	public static final String UPDATE_EMPLOYEE_EXPERIENCE = "UPDATE "
			+ EMPLOYEE_TABLE + " SET " + ET_EXPERIENCE_COULMN + "=? WHERE "
			+ ET_LAST_NAME_COLUMN + "=? AND " + ET_FIRST_NAME_COLUMN + "=?";

	private Connection conn;
	private PreparedStatement prepStQueryEmployees;
	private PreparedStatement prepStQueryEmployeeByLastName;
	private PreparedStatement prepStQueryEmployeeById;
	private PreparedStatement prepStQueryEmployeeAllInfo;
	private PreparedStatement prepStqueryEmployeeSalary;
	private PreparedStatement prepStUpdateEmployeeLastName;
	private PreparedStatement prepStUpdateEmployeeDesignation;
	private PreparedStatement prepStUpdateEmployeeExperience;
	private PreparedStatement prepStinsertIntoEmployee;
	private PreparedStatement prepStinsertIntoSalary;
	private PreparedStatement prepStQueryEmployeeId;
	private PreparedStatement prepStQueryEmployeeFullName;
	private PreparedStatement prepStQuerySalaryBasedOnId;
	private PreparedStatement prepStQueryDesignationGroup;
	private PreparedStatement prepStQuerySalaryRange;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			prepStQueryEmployees = conn.prepareStatement(QUERY_EMPLOYEES);
			prepStQueryEmployeeByLastName = conn
					.prepareStatement(QUERY_EMPLOYEE_BY_LAST_NAME);
			prepStQueryEmployeeById = conn
					.prepareStatement(QUERY_EMPLOYEE_BY_ID);
//			prepStQueryEmployeeAllInfo = conn
//					.prepareStatement(QUERY_EMPLOYEE_ALL_INFORMATION);
			prepStqueryEmployeeSalary = conn
					.prepareStatement(QUERY_EMPLOYEE_SALARY);
			prepStUpdateEmployeeLastName = conn
					.prepareStatement(UPDATE_EMPLOYEE_LAST_NAME);
			prepStUpdateEmployeeDesignation = conn
					.prepareStatement(UPDATE_EMPLOYEE_DESIGNATION);
			prepStUpdateEmployeeExperience = conn
					.prepareStatement(UPDATE_EMPLOYEE_EXPERIENCE);
			// Because I need the employee's id to insert data
			// into Salary table, I passed another parameter to the
			// PreparedStatement
			prepStinsertIntoEmployee = conn.
					prepareStatement(INSERT_EMPLOYEE, 
					Statement.RETURN_GENERATED_KEYS);
			prepStinsertIntoSalary = conn.
					prepareStatement(INSERT_SALARY);
			prepStQueryEmployeeId = conn.
					prepareStatement(QUERY_EMPLOYEE_ID);
			prepStQueryEmployeeFullName = conn
					.prepareStatement(QUERY_EMPLOYEE_BY_FULL_NAME);
			prepStQuerySalaryBasedOnId = conn
					.prepareStatement(QUERY_EMPLOYEE_SALARY_BASED_ON_ID);
			prepStQueryDesignationGroup = conn
					.prepareStatement(QUERY_DESIGNATION_GROUP);
			prepStQuerySalaryRange = conn
					.prepareStatement(QUERY_SALARY_RANGE);
			
			return true;

		} catch (SQLException e) {
			System.out.println("Couldn't connect to database: ");
			e.printStackTrace();
			return false;
		}
	}

	public void close() {
		try {
			if (prepStQuerySalaryRange != null) {
				prepStQuerySalaryRange.close();
			}
			if (prepStQueryDesignationGroup != null) {
				prepStQueryDesignationGroup.close();
			}
			if (prepStQuerySalaryBasedOnId != null) {
				prepStQuerySalaryBasedOnId.close();
			}
			if (prepStQueryEmployeeFullName != null) {
				prepStQueryEmployeeFullName.close();
			}
			if (prepStQueryEmployeeId != null) {
				prepStQueryEmployeeId.close();
			}
			if (prepStinsertIntoSalary != null) {
				prepStinsertIntoSalary.close();
			}
			if (prepStinsertIntoEmployee != null) {
				prepStinsertIntoEmployee.close();
			}
			if (prepStUpdateEmployeeExperience != null) {
				prepStUpdateEmployeeExperience.close();
			}
			if (prepStUpdateEmployeeDesignation != null) {
				prepStUpdateEmployeeDesignation.close();
			}
			if (prepStUpdateEmployeeLastName != null) {
				prepStUpdateEmployeeLastName.close();
			}
			if (prepStqueryEmployeeSalary != null) {
				prepStqueryEmployeeSalary.close();
			}
			if (prepStQueryEmployeeAllInfo != null) {
				prepStQueryEmployeeAllInfo.close();
			}
			if (prepStQueryEmployeeById != null) {
				prepStQueryEmployeeById.close();
			}
			if (prepStQueryEmployeeByLastName != null) {
				prepStQueryEmployeeByLastName.close();
			}
			if (prepStQueryEmployees != null) {
				prepStQueryEmployees.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Unable to close the database: "
					+ e.getMessage());
		}
	}

	// Instead of .schema for Employee's table
	public void queryEmployeeMetaData() {
		try (Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(QUERY_EMPLOYEES)) {

			ResultSetMetaData meta = result.getMetaData();
			int numColumns = meta.getColumnCount();
			for (int i = 1; i <= numColumns; i++) {
				System.out.format("Column %d in Employee table is named %s\n",
						i, meta.getColumnName(i));
			}

		} catch (SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
	}

	public List<Employee> queryEmployees(int sortOrder) {

		try {
			String order;
			if (sortOrder == ORDER_BY_DESC) {
				order = "DESC";
			} else {
				order = "ASC";
			}
			prepStQueryEmployees.setString(1, order);
			ResultSet results = prepStQueryEmployees.executeQuery();

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
		try {
			prepStQueryEmployeeByLastName.setString(1, lastName);

			ResultSet results = prepStQueryEmployeeByLastName.executeQuery();
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

	public List<Employee> getEmployeeById(int id) {
		try {
			prepStQueryEmployeeById.setInt(1, id);

			ResultSet results = prepStQueryEmployeeById.executeQuery();
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

	public Employee queryEmployeeByFullName(String lastName, String firstName) {
		try {
			Employee employee = new Employee();
			prepStQueryEmployeeFullName.setString(1, lastName);
			prepStQueryEmployeeFullName.setString(2, firstName);
			ResultSet result = prepStQueryEmployeeFullName.executeQuery();
			
			while(result.next()) {
				employee.setId(result.getInt(ET_ID_COLUMN));
				employee.setFirstName(result.getString(ET_FIRST_NAME_COLUMN));
				employee.setLastName(result.getString(ET_LAST_NAME_COLUMN));
				employee.setDesignation(result.getString(ET_DESIGNATION_COULMN));
				employee.setExperience(result.getInt(ET_EXPERIENCE_COULMN));
			}
			return employee;
		}catch(Exception e) {
			System.out.println("Unable to query employee by full name: " + e.getMessage());
			return null;
		}
	}

	public void queryEmployeeSalary(String lastName, String firstName) {
		try {
			prepStqueryEmployeeSalary.setString(1, lastName);
			prepStqueryEmployeeSalary.setString(2, firstName);

			ResultSet results = prepStqueryEmployeeSalary.executeQuery();

			while (results.next()) {
				System.out.println("Employee salary: "
						+ results.getInt(ST_CURRENT_SALARY_COLUMN));
			}

		} catch (SQLException e) {
			System.out.println("Unable to search employee's salary: "
					+ e.getMessage());
		}
	}
	
	public Salary queryEmployeeSalaryBasedOnId(int id) {
		try {
			Salary salary = new Salary();
			prepStQuerySalaryBasedOnId.setInt(1, id);
			ResultSet results = prepStQuerySalaryBasedOnId.executeQuery();

			while (results.next()) {
				salary.setId(results.getInt(ST_EMPLOYEE_ID_COLUMN));
				salary.setSalary(results.getInt(ST_CURRENT_SALARY_COLUMN));
			}
			return salary;

		} catch (SQLException e) {
			System.out.println("Unable to search employee's salary based on id: "
					+ e.getMessage());
			return null;
		}
	}

	public boolean updateEmployeeLastName(String newName, String lastName,
			String firstName) {
		try {
			prepStUpdateEmployeeLastName.setString(1, newName);
			prepStUpdateEmployeeLastName.setString(2, lastName);
			prepStUpdateEmployeeLastName.setString(3, firstName);

			int update = prepStUpdateEmployeeLastName.executeUpdate();
			if (update == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Unable to update employee's last name: "
					+ e.getMessage());
			return false;
		}
	}

	public boolean updateEmployeeDesignation(String newDesignation,
			String lastName, String firstName) {
		try {
			prepStUpdateEmployeeDesignation.setString(1, newDesignation);
			prepStUpdateEmployeeDesignation.setString(2, lastName);
			prepStUpdateEmployeeDesignation.setString(3, firstName);

			int update = prepStUpdateEmployeeDesignation.executeUpdate();
			if (update == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Unable to update employee's last name: "
					+ e.getMessage());
			return false;
		}
	}

	public boolean updateEmployeeExperience(int experience, String lastName,
			String firstName) {
		try {
			prepStUpdateEmployeeExperience.setInt(1, experience);
			prepStUpdateEmployeeExperience.setString(2, lastName);
			prepStUpdateEmployeeExperience.setString(3, firstName);

			int update = prepStUpdateEmployeeExperience.executeUpdate();
			if (update == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Unable to update employee's last name: "
					+ e.getMessage());
			return false;
		}
	}

//	private int insertEmployee(String lastName, String firstName, String designation, int experience) throws SQLException {
//			prepStinsertIntoEmployee.setString(1, lastName);
//			prepStinsertIntoEmployee.setString(2, firstName);
//			prepStinsertIntoEmployee.setString(3, designation);
//			prepStinsertIntoEmployee.setInt(4, experience);
//			prepStinsertIntoEmployee.setString(5, firstName);
//			prepStinsertIntoEmployee.setString(6, lastName);
//
//			int affectedRows = prepStinsertIntoEmployee.executeUpdate();
//			if (affectedRows != 1) {
//				throw new SQLException("Couln't insert employee!");
//			}
//			ResultSet generatedKeys = prepStinsertIntoEmployee.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				// this will return employee's id
//				return generatedKeys.getInt(1);
//			} else {
//				throw new SQLException("Couldn't get Employee's id");
//			}
//	}
	
	private int insertEmployee(String lastName, String firstName, String designation, int experience) throws SQLException {

		// Check if the employee is already in the list
		prepStQueryEmployeeFullName.setString(1, lastName);
		prepStQueryEmployeeFullName.setString(2, firstName);
		ResultSet results = prepStQueryEmployeeFullName.executeQuery();

		if (results.next()){
			// If it is we return the employee id
			return results.getInt(1);
		}
		// If not, we insert it
		else {
			prepStinsertIntoEmployee.setString(1, lastName);
			prepStinsertIntoEmployee.setString(2, firstName);
			prepStinsertIntoEmployee.setString(3, designation);
			prepStinsertIntoEmployee.setInt(4, experience);

			int affectedRows = prepStinsertIntoEmployee.executeUpdate();
			if (affectedRows != 1) {
				throw new SQLException("Couln't insert employee!");
			}
			ResultSet generatedKeys = prepStinsertIntoEmployee.getGeneratedKeys();
			if (generatedKeys.next()) {
				// this will return employee's id
				return generatedKeys.getInt(1);
			} else {
				throw new SQLException("Couldn't get Employee's id");
			}
		}
	}
	
	public void insertNewEmployee(String lastName, String firstName, String designation, int experience, int salary){
		try{
			conn.setAutoCommit(false);
			int employeeId = insertEmployee(lastName, firstName, designation, experience);
			
			prepStQuerySalaryBasedOnId.setInt(1, employeeId);
			ResultSet result = prepStQuerySalaryBasedOnId.executeQuery();
			
			if(result.next()){
				System.out.println("The employee id already exists in the salary table!");
			}
			else{
				prepStinsertIntoSalary.setInt(1, employeeId);
				prepStinsertIntoSalary.setInt(2, salary);
				
				int affectedRows = prepStinsertIntoSalary.executeUpdate();
				if(affectedRows == 1){
					conn.commit();
				}
				else{
					throw new SQLException("The salary insert failed");
				}
			}
		}catch(Exception e){
			System.out.println("The employee is already in the system" + e.getMessage());
			try{
				System.out.println("Performing a rollback");
				conn.rollback();
			}catch(SQLException e2){
				System.out.println("Performing a rollback execption: " + e2.getMessage());
			}
		}finally{
			try{
				System.out.println("Resetting default commit behaviour");
				conn.setAutoCommit(true);
			}catch(SQLException e){
				System.out.println("Resetting default commit behaviour execption: " + e.getMessage());
			}
		}
	}
	
	public Designation_Group queryDesignationGroup(String designation) {
		try {
			Designation_Group designationGroup = new Designation_Group();
			prepStQueryDesignationGroup.setString(1, designation);
			ResultSet results = prepStQueryDesignationGroup.executeQuery();

			while (results.next()) {
				designationGroup.setDesignation(results.getString(DGT_DESIGNATION_COULMN));
				designationGroup.setSalaryGroup(results.getInt(DGT_SALARY_GROUP_COLUMN));
			}
			return designationGroup;

		} catch (SQLException e) {
			System.out.println("Unable to get info from Designation_Group based on designation: "
					+ e.getMessage());
			return null;
		}
		
	}
	
	public Salary_Range querySalaryRange(int salaryGroup, int experience) {
		try {
			Salary_Range salary = new Salary_Range();
			prepStQuerySalaryRange.setInt(1, salaryGroup);
			prepStQuerySalaryRange.setInt(2, experience);
			ResultSet results = prepStQuerySalaryRange.executeQuery();

			while (results.next()) {
				salary.setSalaryGroup(results.getInt(SRT_SALARY_GROUP_COLUMN));
				salary.setExperince(results.getInt(SRT_EXPERIENCE_COULMN));
				salary.setRange(results.getString(SRT_RANGE_COULMN));
			}
			return salary;

		} catch (SQLException e) {
			System.out.println("Unable to get info from Salary_Range based on salary group and experience: "
					+ e.getMessage());
			return null;
		}
		
	}
	
	public EmployeeInfo queryEmployeeAllInfo(String lastName, String firstName) {
		Employee emp = queryEmployeeByFullName(lastName, firstName);
		Salary salary = queryEmployeeSalaryBasedOnId(emp.getId());
		Designation_Group designationGroup = queryDesignationGroup(emp.getDesignation());
		Salary_Range salaryRange = querySalaryRange(designationGroup.getSalaryGroup(), emp.getExperience());
		
		return new EmployeeInfo (emp, salary, salaryRange, designationGroup);
		
	}
	
}
