package com.nagarro.nagp.schooldata.data.javaclasses;

public class Teacher extends Person{
	
	private int employeeId;
	private float salary;
	private float bonus;

	public Teacher() {
	}

	public Teacher(String firstName, String middleName, String lastName, String contactNumber, String address,
			float salary, float bonus) {
		super(firstName, middleName, lastName, contactNumber, address);
		this.salary = salary;
		this.bonus = bonus;
	}
	
	public Teacher(String firstName, String middleName, String lastName, String contactNumber, String address,
			int empId, float salary, float bonus) {
		super(firstName, middleName, lastName, contactNumber, address);
		this.employeeId= empId;
		this.salary = salary;
		this.bonus = bonus;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getBonus() {
		return bonus;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public String toString() {
		Object[] data = { this.getEmployeeId(), this.getFirstName(), this.getMiddleName(), this.getLastName(),
				this.getContactNumber(), this.getAddress(), this.getSalary(), this.getBonus() };
		return String.format("|%-15d|%-15s|%-15s|%-15s|%-15s|%-30s\t|%.2f\t\t|%.2f\t|", data);

	}


}
