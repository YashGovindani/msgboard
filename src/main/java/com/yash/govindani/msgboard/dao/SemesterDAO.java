package com.yash.govindani.msgboard.dao;

import java.sql.*;
import com.yash.govindani.msgboard.dto.*;
import java.util.*;

public class SemesterDAO {
    public static int add(Semester semester) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into semester values(?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, semester.getTitle());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int code = resultSet.getInt("code");
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return code;
        } catch(SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to add semester");
        }
    }
    public static void update(Semester semester) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("update semester title=? where code = ?");
            preparedStatement.setString(1, semester.getTitle());
            preparedStatement.setInt(2, semester.getCode());
            preparedStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to update semester");
        }
    }
    public static void delete(Semester semester) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("delete from semester where code = ?");
            preparedStatement.setInt(1, semester.getCode());
            preparedStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to update semester");
        }
    }
    public static Vector<Semester> getAll() throws DAOException {
        Vector<Semester> semesterList = new Vector<>();
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("select * from semester");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Semester semester = new Semester();
                semester.setCode(resultSet.getInt("code"));
                semester.setTitle(resultSet.getString("title"));
                semesterList.add(semester);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to update semester");
        }
        return semesterList;
    }
}