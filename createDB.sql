CREATE TABLE IF NOT EXISTS Person (
					id INTEGER PRIMARY KEY AUTO_INCREMENT,
					firstName VARCHAR(50) NOT NULL,
					middleName VARCHAR(50),
					lastName VARCHAR(50) NOT NULL,
					contactNumber VARCHAR(15),
					address VARCHAR(100)
                    );
                    
 CREATE TABLE IF NOT EXISTS Student (
					rollNumber INT PRIMARY KEY NOT NULL,
					marks DECIMAL(5, 2),
					FOREIGN KEY (rollNumber) REFERENCES Person(id) ON DELETE CASCADE
                    );  
                    
CREATE TABLE IF NOT EXISTS Teacher (
					employeeId INT PRIMARY KEY NOT NULL,
					salary DECIMAL(10, 2),
					bonus DECIMAL(10, 2),
					FOREIGN KEY (employeeId) REFERENCES Person(id) ON DELETE CASCADE
                    );                    