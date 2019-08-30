package model;

public class Salary {
	private int id;
	private int salary;
	
	public Salary() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", salary=" + salary + "]";
	}
	
	
	
	
}
