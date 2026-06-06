package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static void main(String[] args) {

	    getConnection();

	}

  public static Connection getConnection() {

   Connection connection = null;

   try {

   connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/teacher_management",
                    "root",
                    ""
            );

  System.out.println("Database Connected");

  } catch (Exception e) {

   System.out.println(e);

        }

        return connection;
    }
}
