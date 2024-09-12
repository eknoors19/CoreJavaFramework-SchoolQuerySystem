package com.nagarro.nagp.schooldata.data.operations;

import static com.nagarro.nagp.schooldata.utill.constant.Constants.PEOPLE_HEADER_STRING;
import static com.nagarro.nagp.schooldata.utill.constant.Constants.QUERY_REGX_WITHOUT_WHERE;
import static com.nagarro.nagp.schooldata.utill.constant.Constants.QUERY_REGX_WITH_WHERE;
import static com.nagarro.nagp.schooldata.utill.constant.Constants.STUDENT_HEADER_STRING;
import static com.nagarro.nagp.schooldata.utill.constant.Constants.TABLE_FOOTER_STRING;
import static com.nagarro.nagp.schooldata.utill.constant.Constants.TABLE_HEADER_STRING;
import static com.nagarro.nagp.schooldata.utill.constant.Constants.TEACHER_HEADER_STRING;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nagarro.nagp.schooldata.data.javaclasses.Person;
import com.nagarro.nagp.schooldata.data.javaclasses.Student;
import com.nagarro.nagp.schooldata.data.javaclasses.Teacher;
import com.nagarro.nagp.schooldata.utill.structure.Tables;

public class DBQuery {
	
	private String query;
	private boolean where = false;

	public DBQuery(String query) {
		this.query = query.toLowerCase();
		if (query.contains(" = ")) {
			where = true;
		}
	}

	public boolean validateQuery() {
		Pattern pattern = Pattern.compile(where ? QUERY_REGX_WITH_WHERE : QUERY_REGX_WITHOUT_WHERE);
		Matcher matcher = pattern.matcher(query);
		return matcher.matches();
	}

	public void executeQuery() {
		String tableName = query.split("from ")[1].split(" ")[0].toUpperCase();
		DBOperation dbo = new DBOperation();
		System.out.println(TABLE_HEADER_STRING);
		
		switch (Tables.valueOf(tableName)) 
		{
		
		case PERSON: 
			  
			  List<Person> personList = dbo.getPersons();
			  System.out.println(PEOPLE_HEADER_STRING);
			  if (where) 
			  { 
				  String name = query.split(" = ")[1].replace(';', ' ').trim();
				  try 
				  {					  
					  System.out.println((dbo.getPerson(name)).personString()); 
					  
				  }
				  catch (NoSuchElementException e) 
				  {
				  	System.out.println("No Data found for the query"); 
				  } 
			  } 
			  else 
			  {				
				personList.forEach(item -> System.out.println(item.personString()));
			  } 
			  break;
			  
		case STUDENT:
			
			List<Student> studentList = dbo.getStudents();
			System.out.println(STUDENT_HEADER_STRING);
			if (where) 
			{
				int rollNumber = Integer.parseInt(query.split(" = ")[1]);
				try 
				{				
					System.out.println((dbo.getStudent(rollNumber)).toString());
				} 
				catch (NoSuchElementException e) 
				{
					System.out.println("No Data found for the query");
				}
			} 
			else 
			{
				for (Object obj : studentList) 
				{
                    System.out.println(obj.toString());
                }
			}
			break;
		
		case TEACHER:
			List<Teacher> teacherList = dbo.getTeachers();
			System.out.println("Size of Teacher: "+teacherList.size());
			System.out.println(TEACHER_HEADER_STRING);
			if (where) 
			{
				int employeeId = Integer.parseInt(query.split(" = ")[1]);
				try 
				{
					System.out.println((dbo.getTeacher(employeeId)).toString());
				} 
				catch (NoSuchElementException e) 
				{
					System.out.println("No Data found for the query");
				}
			} 
			else 
			{
				for (Object obj : teacherList) 
				{
                    System.out.println(obj.toString());
                }
			}
			break;
				 
		}
		System.out.println(TABLE_FOOTER_STRING);
	}


}
