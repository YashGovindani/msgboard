package com.yash.govindani.msgboard.dao;

import java.sql.*;
import java.io.*;

public class DatabaseUtility {
    private DatabaseUtility() {}
    private static String getQuery(RandomAccessFile randomAccessFile) {
        String accumulator = "";
        try {
            
            while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                String line = randomAccessFile.readLine();
                if(line.length() == 0 || line.startsWith("--")) continue;
                accumulator = accumulator + line;
                if(line.endsWith(";")) break;
            }
            return accumulator;
        } catch (IOException exception) {
            exception.printStackTrace(); // add to log
            return "";
        }
    }
    public static void createTables(String driver, String connectionString, String username, String password) throws DAOException {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            File file = new File("schema.sql");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            String queryString = "";
            while(true) {
                queryString = getQuery(randomAccessFile);
                if(queryString.length() == 0) {
                    break;
                }
                System.out.println("executing sql statement: " + queryString);
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryString);
                statement.close();
            }
            randomAccessFile.close();
            connection.close();
        } catch(Exception exception) {
            System.out.println(exception); // later on remove this and add to log
            throw new DAOException("Unable to create tables");
        }
    }
}