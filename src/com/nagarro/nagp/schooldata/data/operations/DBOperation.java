package com.nagarro.nagp.schooldata.data.operations;

import java.util.List;
import java.util.NoSuchElementException;

import com.nagarro.nagp.schooldata.data.javaclasses.Person;
import com.nagarro.nagp.schooldata.data.javaclasses.Student;
import com.nagarro.nagp.schooldata.data.javaclasses.Teacher;
import com.nagarro.nagp.schooldata.utill.db.DataBaseOperator;

public class DBOperation {
	
	private static DataBaseOperator processor;
	
	public DBOperation() {
		processor = new DataBaseOperator();
	}
	
	public static void addStudent(Student student) throws Exception
	{
		try {
			 processor.addIntoStudent(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void removeStudent(int rollnumber) 
	{
		processor.deleteFromStudent("Student", Integer.toString(rollnumber));
	}
	public static void addTeacher(Teacher teacher) throws Exception
	{
		try {
			 processor.addIntoTeacher(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void removeTeacher(int employeeId) 
	{
		processor.deleteFromTeacher("Teacher", Integer.toString(employeeId));
	}
	
	public List<Student> getStudents() {
		return processor.readStudentData();
	}


	public Student getStudent(int rollNumber) {
		for (Student student : getStudents()) {
			
			if (student.getRollNumber() == rollNumber)
				return student;
			
		}
		throw new NoSuchElementException("No data found for the roll number");
	}
	
	public List<Teacher> getTeachers() {
		return processor.readTeacherData();
	}

	public Teacher getTeacher(int empID) {
		for (Teacher teacher : getTeachers()) {
			if (teacher.getEmployeeId() == empID)
				return teacher;
		}
		throw new NoSuchElementException("No data found for the roll number");
	}
	
	public List<Person> getPersons() {
		return processor.readPersonData();
	}
	
	public Person getPerson(String firstName) {
		for (Person person : getPersons()) {
			if (person.getFirstName().equals(firstName))
				return person;
		}
		throw new NoSuchElementException("No data found for the roll number");
	}
}


