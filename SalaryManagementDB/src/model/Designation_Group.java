package model;

public class Designation_Group {
	private String designation;
	private int salaryGroup;
	
	public Designation_Group() {}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalaryGroup() {
		return salaryGroup;
	}

	public void setSalaryGroup(int salaryGroup) {
		this.salaryGroup = salaryGroup;
	}

	@Override
	public String toString() {
		return "Designation_Group [designation=" + designation + ", salaryGroup=" + salaryGroup + "]";
	}
	
	
	
	
}
