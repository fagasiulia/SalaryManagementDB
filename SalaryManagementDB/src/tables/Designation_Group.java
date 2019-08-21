package tables;

public class Designation_Group {
	private String designation;
	private int salaryGroup;
	
	public Designation_Group(String designation, int salaryGroup) {
		this.designation = designation;
		this.salaryGroup = salaryGroup;
	}

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
	
	
	
	
}
