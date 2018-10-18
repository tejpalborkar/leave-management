package com.leave.management.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static final String url = "jdbc:mysql://localhost:3306/leavemanagement";
	public static final String user = "root";
	public static final String pwd = "root";

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection(url, user, pwd);

			System.out.println("DB connection successfull");

			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Driver not found.. Please check if the mysql-connector is added in build path");
			e.printStackTrace();
			throw e;
		}
	}
}
