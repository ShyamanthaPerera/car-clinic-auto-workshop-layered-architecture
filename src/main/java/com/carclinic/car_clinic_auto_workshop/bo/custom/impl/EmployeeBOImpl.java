package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.EmployeeBO;
import com.carclinic.car_clinic_auto_workshop.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveEmployees(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateEmployees(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existEmployees(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewEmployeeId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
