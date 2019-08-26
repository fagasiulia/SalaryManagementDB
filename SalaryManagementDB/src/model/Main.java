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
				e.getLastName();
			}
		}
	
		datasource.close();
	}

}
