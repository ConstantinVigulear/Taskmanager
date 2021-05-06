package com.stefanini.taskmanager.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JavaDataBaseConneciton {
	
	
	public static Connection getConnection() {
		Connection connection = null;
		try {

			// 1. Load the properties file
			Properties properties = new Properties();
			properties.load(new FileInputStream(
					"C:\\Users\\cvigulea\\eclipse-workspace\\taskmanager\\resources\\taskmanager.properties"));

			// 2. Read the properties
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			String dbURL = properties.getProperty("dbURL");

			connection = DriverManager.getConnection(dbURL, user, password);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return connection;
	}
	
}
