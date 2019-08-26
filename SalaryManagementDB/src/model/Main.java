package model;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		List<Employee> employeeList = datasource.queryEmployees();
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
