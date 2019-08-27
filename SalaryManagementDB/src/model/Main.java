package model;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
//		List<Employee> employeeList = datasource.queryEmployees(Datasource.ORDER_BY_ASC);
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
		datasource.queryEmployeeMetaData();
	
		List<Employee> employeeList = datasource.getEmployeeByLastName("Johnson");
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
