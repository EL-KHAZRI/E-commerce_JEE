package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
    private static String url = "jdbc:mysql://sql8.freemysqlhosting.net/sql8640886";
    private static String user = "sql8640886";
    private static String password = "gwEbscYA4U";
    private static Connection connection;
    
    public static Connection connectToDB() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}

        return connection;
    }
}

