package com.nagarro.nagp.schooldata.utill.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nagarro.nagp.schooldata.data.javaclasses.Person;
import com.nagarro.nagp.schooldata.data.javaclasses.Student;
import com.nagarro.nagp.schooldata.data.javaclasses.Teacher;


public class DataBaseOperator {
		
			
	//Add Student Details into DB
    public static void addIntoStudent(Student student) {   	
    	//Create DB Tables
    	createDBTables();
    	String id=null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Person (firstName, middleName, lastName, contactNumber, address) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getMiddleName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getContactNumber());
            pstmt.setString(5, student.getAddress());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getString(1);
                    try (PreparedStatement pstmtStudent = conn.prepareStatement("INSERT INTO Student (rollNumber, marks) VALUES (?,?)")) {
                        pstmtStudent.setString(1, id);
                        pstmtStudent.setFloat(2, student.getMarks());
                        pstmtStudent.executeUpdate();
                        System.out.println("New Student with StudentID: "+ id+ " created successfully");
                    }
                } else {
                    throw new SQLException("Adding Student failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
        	deletePersonData("Person", id);
            e.printStackTrace();
        }
    }
 // Delete Student Data from DB
    public static void deleteFromStudent(String tableName, String id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM " + tableName + " WHERE rollnumber = ?")) {
            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(tableName + " Data deleted successfully.");
                
                try (PreparedStatement pstmtStudent = conn.prepareStatement("DELETE FROM Person WHERE id = ?")) 
                {    
                	pstmtStudent.setString(1, id);
                	int affectedPersonRows= pstmtStudent.executeUpdate();
                	if (affectedPersonRows > 0) 
                	{
                        System.out.println("Person Removed successfully.");
                    }
                	else {
                        throw new SQLException("Removing Person failed, no ID obtained.");
                    }
                }catch (SQLException e){
                	e.printStackTrace();
                }
                
            }   
            else {
                System.out.println("No data found with the given ID.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // Add Teacher Details into DB
    public static void addIntoTeacher(Teacher teacher) {
    	
    	createDBTables();
    	String id= null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Person (firstName, middleName, lastName, contactNumber, address) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, teacher.getFirstName());
            pstmt.setString(2, teacher.getMiddleName());
            pstmt.setString(3, teacher.getLastName());
            pstmt.setString(4, teacher.getContactNumber());
            pstmt.setString(5, teacher.getAddress());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding Teacher failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getString(1);
                    try (PreparedStatement pstmtTeacher = conn.prepareStatement("INSERT INTO Teacher (employeeId, salary, bonus) VALUES (?, ?, ?)")) {
                        pstmtTeacher.setString(1, id);
                        pstmtTeacher.setDouble(2, teacher.getSalary());
                        pstmtTeacher.setDouble(3, teacher.getBonus());
                        pstmtTeacher.executeUpdate();
                        System.out.println("New Teacher with EmployeeID: "+ id+ " created successfully");
                        
                    }
                } else {
                    throw new SQLException("Adding Teacher failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
        	deletePersonData("Person", id);
            e.printStackTrace();
        }
    }
    // Delete Teacher Data from DB
    public static void deleteFromTeacher(String tableName, String id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM " + tableName + " WHERE employeeId = ?")) {
            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(tableName + "with EmployeeId" + ":"+id +" removed successfully.");
                
                try (PreparedStatement pstmtStudent = conn.prepareStatement("DELETE FROM Person WHERE id = ?")) 
                {    
                	pstmtStudent.setString(1, id);
                	int affectedPersonRows= pstmtStudent.executeUpdate();
                	if (affectedPersonRows > 0) 
                	{
                        System.out.println("And Person data also removed successfully.");
                    }
                	else {
                        throw new SQLException("Removing Person failed, no ID obtained.");
                    }
                }catch (SQLException e){
                	e.printStackTrace();
                }
                
            }   
            else {
                System.out.println("No data found with the given ID.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // Delete Person Data from DB
    public static void deletePersonData(String tableName, String id) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM "+tableName+" WHERE id = ?")) {
			pstmt.setString(1, id);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				
			} else {
				throw new SQLException("Constraint violation while inserting the Data in the table");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Query data from the database based on table name
	public static void queryDatausingTableName(String tableName) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
            Statement stmt = conn.createStatement()) {
            String query;
            if (tableName.equalsIgnoreCase("Person")) {
                query = "SELECT * FROM Person";
            } else if (tableName.equalsIgnoreCase("Student")) {
                query = "SELECT * FROM Person INNER JOIN Student ON Person.id = Student.rollNumber";
            } else if (tableName.equalsIgnoreCase("Teacher")) {
                query = "SELECT * FROM Person INNER JOIN Teacher ON Person.id = Teacher.employeeId";
            } else {
                System.out.println("Invalid table name.");
                return;
            }
            ResultSet rs = stmt.executeQuery(query);
            // Process and display results
            // This depends on how you want to query the results
            
            while (rs.next()) {
                String firstname = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String contactNo = rs.getString("contactNumber");
                String adress = rs.getString("address");

                // Formatting the Output Result
                System.out.format("%10s%10s%10s%10s%20s\n", firstname,middleName, lastName, contactNo, adress);
                
            }
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	// Create DB Tables IF NOT EXISTS
    private static void createDBTables() 
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
            Statement stmt = conn.createStatement()) 
        {
            String createPersonTable = 	"CREATE TABLE IF NOT EXISTS Person (" +
                    					"id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    					"firstName VARCHAR(50) NOT NULL," +
                    					"middleName VARCHAR(50)," +
                    					"lastName VARCHAR(50) NOT NULL," +
                    					"contactNumber VARCHAR(15)," +
                    					"address VARCHAR(100))";
            stmt.executeUpdate(createPersonTable);
            String createStudentTable = "CREATE TABLE IF NOT EXISTS Student (" +
                    					"rollNumber INT PRIMARY KEY NOT NULL," +
                    					"marks DECIMAL(5, 2)," +
                    					"FOREIGN KEY (rollNumber) REFERENCES Person(id) ON DELETE CASCADE)";
            stmt.executeUpdate(createStudentTable);
            String createTeacherTable = "CREATE TABLE IF NOT EXISTS Teacher (" +
                    					"employeeId INT PRIMARY KEY NOT NULL," +
                    					"salary DECIMAL(10, 2)," +
                    					"bonus DECIMAL(10, 2)," +
                    					"FOREIGN KEY (employeeId) REFERENCES Person(id) ON DELETE CASCADE)";
            stmt.executeUpdate(createTeacherTable);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    	
    public static List<Person> readPersonData() {
        List<Person> personList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
             Statement stmt = conn.createStatement()) {
            String query;
            
            query = "SELECT * FROM Person";
           
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String contactNumber = rs.getString("contactNumber");
                String address = rs.getString("address");
    
                personList.add(new Person(firstName, middleName, lastName, contactNumber, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }
    
    public static List<Student> readStudentData() {
	    List<Student> studentList = new ArrayList<>();
	    String query = "SELECT * FROM Person INNER JOIN Student ON Person.id = Student.rollNumber";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	            String firstName = rs.getString("firstName");
	            String middleName = rs.getString("middleName");
	            String lastName = rs.getString("lastName");
	            String contactNumber = rs.getString("contactNumber");
	            String address = rs.getString("address");
	            int rollNumber = rs.getInt("rollNumber");
	            float marks = rs.getFloat("marks");
	            
	            studentList.add(new Student(firstName, middleName, lastName, contactNumber, address, rollNumber, marks));
	       
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return studentList;
	}
       
    public static List<Teacher> readTeacherData() {
        List<Teacher> teacherList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nagp2024","root", "root");
             Statement stmt = conn.createStatement()) {
            String query;
            
            query = "SELECT * FROM Person INNER JOIN Teacher ON Person.id = Teacher.employeeId";
           
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String contactNumber = rs.getString("contactNumber");
                String address = rs.getString("address");
                int employeeId = rs.getInt("employeeId");
                float salary = rs.getFloat("salary");
                float bonus = rs.getFloat("bonus");
                teacherList.add(new Teacher(firstName, middleName, lastName, contactNumber, address, employeeId, salary, bonus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }
    
    
}
