package com.yash.govindani.msgboard.dao;

import java.sql.*;

public class DAOConnection {
    private static String driver = null;
    private static String connectionString = null;
    private static String username = null;
    private static String password = null;
    private DAOConnection() {}
    public static void setDriver(String d) {
        driver = d;
    }
    public static String getDriver() {
        return driver;
    }
    public static void setConnectionString(String cs) {
        connectionString = cs;
    }
    public static String getConnectionString() {
        return connectionString;
    }
    public static void setUsername(String u) {
        username = u;
    }
    public static String getUsername() {
        return username;
    }
    public static void setPassword(String p) {
        password = p;
    }
    public static String getPassword() {
        return password;
    }
    public static Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch(Exception exception) {
            System.out.println("Unable to create db connection"); // later move to log
            throw new DAOException(exception.getMessage());
        }
        return connection;
    }
}