package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.EmployeeAppointmentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class EmployeeAppointmentModel {

    public boolean saveEmployeeAppointment(EmployeeAppointmentDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_EMPLOYEE_APPOINTMENT);

        statement.setString(1, dto.getAppointmentId());
        statement.setString(2, dto.getEmployeeId());

        return statement.executeUpdate() > 0;
    }

//    public boolean updateEmployeeAppointment(EmployeeAppointmentDTO dto) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_APPOINTMENT);
//
//        statement.setString(1, dto.getUserName());
//        statement.setString(2, dto.getCusName());
//        statement.setString(3, dto.getAddress());
//        statement.setString(4, dto.getTelNum());
//        statement.setString(5, dto.getCusId());
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public boolean deleteEmployeeAppointment(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_APPOINTMENT);
//
//        statement.setString(1, id);
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public EmployeeAppointmentDTO searchEmployeeAppointment(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection ();
//        PreparedStatement statement = connection.prepareStatement(SEARCH_EMPLOYEE_APPOINTMENT);
//
//        statement.setString(1, id);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        EmployeeAppointmentDTO dto = null;
//
//        if(resultSet.next()) {
//            String cus_id = resultSet.getString(1);
//            String cus_user = resultSet.getString(2);
//            String cus_name = resultSet.getString(3);
//            String cus_address = resultSet.getString(4);
//            String cus_tel = resultSet.getString(5);
//
//            dto = new EmployeeAppointmentDTO(cus_id, cus_user, cus_name, cus_address, cus_tel);
//        }
//        return dto;
//    }



    public List<EmployeeAppointmentDTO> getAllEmployeeAppointment() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_EMPLOYEE_APPOINTMENT);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<EmployeeAppointmentDTO> EmployeeAppointmentDtoList = new ArrayList<>();

        while(resultSet.next()) {
            EmployeeAppointmentDtoList.add(
                    new EmployeeAppointmentDTO(
                            resultSet.getString(1),
                            resultSet.getString(2)
                    )
            );
        }
        return EmployeeAppointmentDtoList;
    }




}
