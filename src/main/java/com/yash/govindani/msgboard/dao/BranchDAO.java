package com.yash.govindani.msgboard.dao;

import java.sql.*;
import com.yash.govindani.msgboard.dto.*;
import java.util.*;

public class BranchDAO {
    public static int add(Branch branch) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into branch values(?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, branch.getTitle());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int code = resultSet.getInt("code");
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return code;
        } catch(SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to add branch");
        }
    }
    public static void update(Branch branch) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("update branch title=? where code = ?");
            preparedStatement.setString(1, branch.getTitle());
            preparedStatement.setInt(2, branch.getCode());
            preparedStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to update branch");
        }
    }
    public static void delete(Branch branch) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("delete from branch where code = ?");
            preparedStatement.setInt(1, branch.getCode());
            preparedStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to update branch");
        }
    }
    public static Vector<Branch> getAll() throws DAOException {
        Vector<Branch> branchList = new Vector<>();
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("select * from branch");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Branch branch = new Branch();
                branch.setCode(resultSet.getInt("code"));
                branch.setTitle(resultSet.getString("title"));
                branchList.add(branch);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage()); // add to logs
            throw new DAOException("Unable to update branch");
        }
        return branchList;
    }
}