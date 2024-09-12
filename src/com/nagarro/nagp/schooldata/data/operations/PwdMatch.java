package com.nagarro.nagp.schooldata.data.operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PwdMatch {
	
	public PwdMatch()
	{
	}
	
	public static String readPasswordFile() throws IOException
	{
		String password= null;
		try {
            // Get the path to the desktop directory
            Path desktopPath = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Desktop");
            System.out.println("Desktop  Path: "+ desktopPath);
            // Construct the full path to the password file
            Path passwordFilePath = desktopPath.resolve("Password.txt");
            System.out.println("Password  Path: "+ passwordFilePath);
            password = readPasswordFromFile(passwordFilePath.toString());
            System.out.println("Password read from file: " + password);
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading the password from the file: " + e.getMessage());
        }
	     return password;
	}
	
	private static String readPasswordFromFile(String filePath) throws IOException {
        StringBuilder password = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                password.append(line);
            }
        }
        return password.toString();
    }

}
