package model;

public class EmployeeInfo{
	
	private Employee employee;
	private Salary salary;
	private Salary_Range salaryRange;
	private Designation_Group designatioGroup;
	
	public EmployeeInfo(Employee employee, Salary salary, Salary_Range salaryRange, Designation_Group designatioGroup) {
		this.employee = employee;
		this.salary = salary;
		this.salaryRange = salaryRange;
		this.designatioGroup = designatioGroup;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Salary_Range getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(Salary_Range salaryRange) {
		this.salaryRange = salaryRange;
	}

	public Designation_Group getDesignatioGroup() {
		return designatioGroup;
	}

	public void setDesignatioGroup(Designation_Group designatioGroup) {
		this.designatioGroup = designatioGroup;
	}

	@Override
	public String toString() {
		return "Id " + employee.getId() +" Name: " + employee.getFirstName() + " " + employee.getLastName();
	}
	
	
}
