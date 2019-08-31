package model;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		
//		List<Employee> employees = datasource.getEmployeeByLastName("Johnson");
//		if(employees.isEmpty()) {
//			System.out.println("No employee found");
//		}
//		else {
//			for(Employee e : employees) {
//				System.out.println("Id: " + e.getId()
//						+ " Name: " + e.getFirstName() + " " + e.getLastName()
//						+ " Designation: " + e.getDesignation() 
//						+ " Experience: " + e.getExperience());
//			}
//		}
		
//		List<Employee> employees = datasource.getEmployeeById(3);
//		if(employees.isEmpty()) {
//			System.out.println("No employee found");
//		}
//		else {
//			for(Employee e : employees) {
//				System.out.println("Id: " + e.getId()
//						+ " Name: " + e.getFirstName() + " " + e.getLastName()
//						+ " Designation: " + e.getDesignation() 
//						+ " Experience: " + e.getExperience());
//			}
//		}
		
//		datasource.queryEmployeeMetaData();

//		datasource.queryEmployeeSalary("Johnson", "Emma");

		
//		boolean changeName = datasource.updateEmployeeLastName("Riss", "Johnson", "Emma");
//		boolean changeDesignation = datasource.updateEmployeeDesignation("Java Developer", "Riss", "Emma");
//		boolean changeExperience = datasource.updateEmployeeExperience(3, "Riss", "Emma");
//		
//		System.out.println("Change name successful: " + changeName);
//		System.out.println("Change designation successful: " + changeDesignation);
//		System.out.println("Change experience successful: " + changeExperience);
//	
//		if(!datasource.open()) {
//			System.out.println("Can't open datasource");
//			return;
//		}
//		List<Employee> employeeList = datasource.queryEmployees(Datasource.ORDER_BY_NONE);
//		if(employeeList == null) {
//			System.out.println("No employee found");
//		}
//		else {
//			for(Employee e : employeeList) {
//				System.out.println("Id: " + e.getId()
//						+ " Name: " + e.getFirstName() + " " + e.getLastName()
//						+ " Designation: " + e.getDesignation() 
//						+ " Experience: " + e.getExperience());
//			}
//		}
		
//		EmployeeInfo employeeInfo = datasource.queryEmployeeAllInfo("Johnson", "Emma");
//		System.out.println(employeeInfo.toString());
		datasource.insertNewEmployee("Hasworth", "Liana", "Project Manager", 3, 3800);
		
		List<Employee> employeeList = datasource.queryEmployees(Datasource.ORDER_BY_DESC);
		if(employeeList == null) {
			System.out.println("No employee found");
		}
		else {
			for(Employee e : employeeList) {
				System.out.println("Id: " + e.getId()
						+ " Name: " + e.getFirstName() + " " + e.getLastName()
						+ " Designation: " + e.getDesignation() 
						+ " Experience: " + e.getExperience());
			}
		}

		datasource.close();
	}

}
