package model;

public class EmployeeInfo{
	
	private int id;
	private String firstName;
	private String lastName;
	private String designation;
	private int experience;
	private int salary;
	private String salaryRange;
	
	public EmployeeInfo() {}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public String getSalaryRange() {
		return salaryRange;
	}


	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}


	@Override
	public String toString() {
		return "EmployeeInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", designation="
				+ designation + ", experience=" + experience + ", salary=" + salary + ", salaryRange=" + salaryRange
				+ "]";
	}

}
