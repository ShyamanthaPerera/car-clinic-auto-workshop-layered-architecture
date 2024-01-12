package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    boolean saveEmployees(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean updateEmployees(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean existEmployees(String id) throws SQLException, ClassNotFoundException;

    boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException;

    String generateNewEmployeeId() throws SQLException, ClassNotFoundException;
}
