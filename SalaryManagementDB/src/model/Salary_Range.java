package model;

public class Salary_Range {
	private int salaryGroup;
	private int experince;
	private String range;
	
	public Salary_Range(int salaryGroup, int experince, String range) {
		this.salaryGroup = salaryGroup;
		this.experince = experince;
		this.range = range;
	}

	public int getSalaryGroup() {
		return salaryGroup;
	}

	public void setSalaryGroup(int salaryGroup) {
		this.salaryGroup = salaryGroup;
	}

	public int getExperince() {
		return experince;
	}

	public void setExperince(int experince) {
		this.experince = experince;
	}

	public String getRange() {
		return range;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	
	
	
	

}
