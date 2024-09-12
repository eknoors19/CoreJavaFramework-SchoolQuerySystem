package com.nagarro.nagp.schooldata.utill.constant;

import java.util.Collections;

public class Constants {
	
	private Constants() {}

	// Max number of retries allowed for Password
	public static final int MAX_PASSWORD_RETRY = 4;
	// Regular expression for query with where
	public static final String QUERY_REGX_WITH_WHERE = "^select [*] from (student where rollnumber = [0-9]*|teacher where employeeid = [0-9]*|person where name = [a-zA-Z]*)$";
	// Regular expression for query without where
	public static final String QUERY_REGX_WITHOUT_WHERE = "^select [*] from (person|student|teacher)$";
	// formating for representing data on screen.
	public static final String TABLE_HEADER_STRING =  String.join(String.join("", Collections.nCopies(143, "-")), "/","\\");
	// string to be printed on screen for people header table
	public static final String PEOPLE_HEADER_STRING = "|FIRST NAME\t|MIDDLE NAME\t|LAST NAME\t|CONTACT NUMBER\t\t|ADDRESS\t\t\t\t\t\t\t\t|";
	// string to be printed on screen for student header table
	public static final String STUDENT_HEADER_STRING = "|ROLL NUMBER\t|FIRST NAME\t|MIDDLE NAME\t|LAST NAME\t|CONTACT NUMBER\t|ADDRESS\t\t\t\t\t\t|MARKS\t|";
	// string to be printed on screen for teacher header table
	public static final String TEACHER_HEADER_STRING = "|EMPLOYEE ID\t|FIRST NAME\t|MIDDLE NAME\t|LAST NAME\t|CONTACT NUMBER\t|ADDRESS\t\t\t|SALARY\t\t|BONUS\t\t|";
	// formating for representing data on screen.
	public static final String TABLE_FOOTER_STRING = String.join(String.join("", Collections.nCopies(143, "-")), "\\","/");	
		
}
