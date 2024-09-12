package com.nagarro.nagp.schooldata.data.javaclasses;

public class Student extends Person{
	
	private float marks;
	private int rollNumber;

	public Student() {
	}
	
	  public Student(String firstName, String middleName, String lastName, String
	  contactNumber, String address, float marks) 
	  { 
		  super(firstName, middleName, lastName, contactNumber, address); 
		  this.setMarks(marks); 
	  }
	 
		
		  public Student(String firstName, String middleName, String lastName, String contactNumber, String address, int rollNumber, float marks) 
		  {
			  super(firstName, middleName, lastName, contactNumber, address);
			  this.setRollNumber(rollNumber); 
			  this.setMarks(marks); 
		  }

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public float getMarks() {
		return marks;
	}

	public void setMarks(float marks) {
		this.marks = marks;
	}

	public String toString() {
		Object[] data = { this.getRollNumber(), this.getFirstName(), this.getMiddleName(), this.getLastName(),
				this.getContactNumber(), this.getAddress(), this.getMarks() };
		return String.format("|%-15d|%-15s|%-15s|%-15s|%-15s|%-40s\t\t|%.2f\t|", data);
	}


}
