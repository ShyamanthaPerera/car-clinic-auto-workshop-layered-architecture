package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {

    ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

    boolean saveUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean updateUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean existUsers(String id) throws SQLException, ClassNotFoundException;

    boolean deleteUsers(String id) throws SQLException, ClassNotFoundException;

    String generateNewUserId() throws SQLException, ClassNotFoundException;
}
