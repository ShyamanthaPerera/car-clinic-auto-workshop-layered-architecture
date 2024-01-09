package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class UserModel {

    public boolean saveUser(UserDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_USER);

        statement.setString(1, dto.getUserName());
        statement.setString(2, dto.getPassword());
        statement.setString(3, dto.getName());
        statement.setString(4, "system");
        statement.setDate(5,new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(6,null);
        statement.setString(7,null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateUser(UserDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER);

        statement.setString(1, dto.getPassword());
        statement.setString(2, dto.getName());
        statement.setString(3, dto.getUserName());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteUser(String user_name) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USER);

        statement.setString(1, user_name);

        return statement.executeUpdate() > 0;
    }

    public UserDTO searchUser(String userName) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_USER);

        statement.setString(1, userName);

        ResultSet resultSet = statement.executeQuery();

        UserDTO dto = null;

        if(resultSet.next()) {
            String user_name = resultSet.getString(1);
            String password = resultSet.getString(2);
            String name = resultSet.getString(3);

            dto = new UserDTO(user_name, password, name);
        }
        return dto;
    }



    public List<UserDTO> getAllUser() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_USER);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<UserDTO> UserDtoList = new ArrayList<>();

        while(resultSet.next()) {
            UserDtoList.add(
                    new UserDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return UserDtoList;
    }

    public List<UserDTO> getAllCustomerBySearch(String searchVal) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_USER_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();

        ArrayList<UserDTO> userDtoList = new ArrayList<>();

        while(resultSet.next()) {
            userDtoList.add(
                    new UserDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return userDtoList;
    }



}
