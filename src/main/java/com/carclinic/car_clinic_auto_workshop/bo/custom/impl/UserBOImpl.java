package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.UserBO;
import com.carclinic.car_clinic_auto_workshop.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existUsers(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteUsers(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewUserId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
