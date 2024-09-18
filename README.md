**********************************************************************************************************************
**************************************************READ ME FILE********************************************************
**********************************************************************************************************************

1. Assignment/Eclipse Project Name: Eknoor_3185683_SchoolDataSimpleQuerySystem
	How to RUN:- 
	Import the project in eclips.
	Navigate to class file src/com.nagarro.nagp.schooldata.system/SchoolDataSimpleQueryApp.java
	Right click on the file.
	Select Run As Java Application
	Application should be started and the Login menu should be on Console

2. Attached the Password.txt file in the zip file on GDrive. Place this file on desktop and 
   it will automatically pick the desktop path of Password.txt file.

3. MySQL DataBase Table Structure: 
	Person - id (primary key) , firstName , middleName , lastName , contactNumber , address 
	Student (extends Person) - rollNumber (foreign key) , marks
	Teacher (extends Person) - employeeId (foreign key) , salary , bonus
	
4. Added the createDataBaseTables code in the assignment. Once the code is executed, it automatically 
   creates the database tables.	
	
5. Also, attached the SQL queries in the createDB.sql file for evaluators to look into the queries. 

6. Created a video recording of all the flow of the project (Recording_NAGP_assignment.mp4)
  {add student -> add teacher -> remove student -> remove teacher ->query data(student/teacher/person) -> exit}
  
7. JDBC URL used in code:  jdbc:mysql://localhost:3306/nagp2024
   JDBC DATABASE used in code: nagp2024
   JDBC DB username used in code: root
   JDBC DB password used in code: root   
  
8. Also, attached the external jre file in zip folder, used for sql connection to MySQL database.   

**********************************************************************************************************************
**********************************************************************************************************************
