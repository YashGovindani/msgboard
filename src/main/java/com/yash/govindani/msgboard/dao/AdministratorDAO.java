package com.yash.govindani.msgboard.dao;

import java.sql.*;
import com.yash.govindani.msgboard.dto.*;

public class AdministratorDAO {
    public static void add(Administrator administrator) throws DAOException {
        try {
            Connection connection = DAOConnection.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into administrator values(?, ?, ?)"); 
            preparedStatement.setString(1, administrator.getUsername());
            preparedStatement.setString(2, administrator.getPassword());
            preparedStatement.setString(3, administrator.getPasswordKey());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException); // add to log
            throw new DAOException("Unable to add administrator");
        }
    }
}