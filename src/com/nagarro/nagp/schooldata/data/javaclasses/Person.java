package com.nagarro.nagp.schooldata.data.javaclasses;

public class Person {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String contactNumber;
	private String address;
	
	public Person(String firstName, String middleName, String lastName, String contactNumber, String address) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.address = address;
	}
	
	public Person() {
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public final String personString() {
		Object[] data = { this.getFirstName(), this.getMiddleName(), this.getLastName(), this.getContactNumber(),
				this.getAddress() };
		return String.format("|%-15s|%-15s|%-15s|%-15s\t|%-40s\t\t\t\t|", data);
	}
	
}
