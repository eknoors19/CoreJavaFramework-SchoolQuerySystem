package com.nagarro.nagp.schooldata.system;

import static com.nagarro.nagp.schooldata.utill.constant.Constants.MAX_PASSWORD_RETRY;

import java.io.IOException;

import com.nagarro.nagp.schooldata.data.javaclasses.Student;
import com.nagarro.nagp.schooldata.data.javaclasses.Teacher;
import com.nagarro.nagp.schooldata.data.operations.DBOperation;
import com.nagarro.nagp.schooldata.data.operations.DBQuery;
import com.nagarro.nagp.schooldata.utill.constant.Input;
import com.nagarro.nagp.schooldata.utill.db.DataBaseOperator;


public class Menu {
	
	public static void Menu() throws Exception {
		System.out.println("\n***********************************************************************************************\n");
		System.out.println("\n******************************\tSchool Data Simple Query System\t******************************\n");
		System.out.println("\n***********************************************************************************************\n");
		
		UserEntry();
	}

	public static void UserEntry() throws IOException {
		clear(2);
		if(true)
		{
			int retry = 1;
			while (retry <= MAX_PASSWORD_RETRY) {
				System.out.println("Enter the password to Login: " );
				String inputPassword = Input.getInstance().takeStringInput();

				if("Password".equals(inputPassword))
				{
					Login();
					return;
				}
				if (MAX_PASSWORD_RETRY - retry == 0) {
					System.out.println("Sorry you have exhausted all your retries. Hence exiting from the application");
					exit(0);
				}
				System.out.println("Please enter correct password. Remaining number of retries: "+ (MAX_PASSWORD_RETRY - retry));
				retry += 1;
			}
		} 
	}

	public static void Login() {
		clear(2);
		do
			try {
				System.out.println(
						"Please Select an option from the below menu:\n\n 1. Add Student Details\n 2. Remove Student Details \n 3. Add Teacher Details \n 4. Remove Teacher Details \n 5. Query Data Details\n 6. Exit\n");
				int option = Input.getInstance().takeMenuInput(6);
				switch (option) {
				case 1:
					addStudentDetails();
					break;
				case 2:
					removeStudentDetails();
					break;
				case 3:
					addTeacherDetails();
					break;
				case 4:
					removeTeacherDetails();
					break;
				case 5:
					executeQueryData();
					break;
				case 6:
					exit(0);
				}
			} catch (Exception e) {
				exit(1);
			}
		while (true);
	}

	public static void addStudentDetails() throws Exception {
		clear(2);
		System.out.println("Enter the First Name:");
		String firstName = Input.getInstance().takeStringInput();
		System.out.println("Enter the Middle Name:");
		String middleName = Input.getInstance().takeStringInput();
		System.out.println("Enter the Last Name:");
		String lastName = Input.getInstance().takeStringInput();
		System.out.println("Enter the contact number:");
		String contactNumber = Input.getInstance().takeStringInput();
		System.out.println("Enter the address:");
		String address = Input.getInstance().takeStringInput();
		System.out.println("Enter the marks of the student:");
		float marks = Input.getInstance().takeFloatInput();
		Student student = new Student(firstName, middleName, lastName, contactNumber, address, marks);
		
		DBOperation dbO= new DBOperation();
		dbO.addStudent(student);
		
	}

	public static void removeStudentDetails() {
		clear(2);
		System.out.println("Enter the RollNumber of the Student");
		int rollNumber = Input.getInstance().takeIntegerInput();
		
		DBOperation dbO= new DBOperation();
		dbO.removeStudent(rollNumber);
	}
	

	public static void addTeacherDetails() throws Exception {
		clear(2);
		System.out.println("Enter the First Name:");
		String firstName = Input.getInstance().takeStringInput();
		System.out.println("Enter the Middle Name:");
		String middleName = Input.getInstance().takeStringInput();
		System.out.println("Enter the Last Name:");
		String lastName = Input.getInstance().takeStringInput();
		System.out.println("Enter the contact number:");
		String contactNumber = Input.getInstance().takeStringInput();
		System.out.println("Enter the address:");
		String address = Input.getInstance().takeStringInput();
		System.out.println("Enter the salary:");
		float salary = Input.getInstance().takeFloatInput();
		System.out.println("Enter the bonus:");
		float bonus = Input.getInstance().takeFloatInput();
		Teacher teacher = new Teacher(firstName, middleName, lastName, contactNumber, address, salary, bonus);
		
		DBOperation dbO= new DBOperation();
		dbO.addTeacher(teacher);
		
	}

	
	public static void removeTeacherDetails() {
		clear(2);
		System.out.println("Enter the EmployeeId of the Teacher:");
		int employeeId = Input.getInstance().takeIntegerInput();
		
		DBOperation dbO= new DBOperation();
		dbO.removeTeacher(employeeId);
		
		}

	
	public static void executeQueryData() {
		clear(2);
		System.out.println("Accepted formats for the query are:");
		System.out.println("1. SELECT * FROM TABLENAME");
		System.out.println("2. SELCT * FROM TABLENAME WHERE EMPLOYEEID/ROLLNUMBER/NAME = VALUE");
		System.out.println(
				"*NOTE*: Query is Case Insensative but space and special character(=) are required and Query should be in single line\n\tValid Values for TABLENAME are Person,Student,Teacher\n\tValid Column name combination are Student - rollnumber, Person - name( as firstname), Teacher - employeeId");
		System.out.println("Enter the query: ");
		String inputQuery = Input.getInstance().takeStringInput();
		DBQuery query = new DBQuery(inputQuery);
		
		if (query.validateQuery()) {
			query.executeQuery();
		} 
		else {
			System.out.println("Query is not in correct format\n Press 1. for retry\n Press 2 for main menu\n");
			switch (Input.getInstance().takeMenuInput(2)) {
			case 1:
				executeQueryData();
				break;
			case 2:
				Login();
				break;
			}
		}
	}
	public static void exit(int code) {
		clear(5);
		if (code == 1) {
			System.out.println("Something Went wrong with the application. Exiting the application.");
		}
		System.out.println("Thanks for Using the application.\nFor further opeartions, Start the application again.");
		System.exit(0);
	}

	public static void clear(int length) {
		for (int clear = 0; clear < length; clear++) {
			System.out.println("");
		}
	}
}
