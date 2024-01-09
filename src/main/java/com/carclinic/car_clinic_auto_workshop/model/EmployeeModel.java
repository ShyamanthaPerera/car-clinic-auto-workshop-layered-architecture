package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class EmployeeModel {

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_EMPLOYEE);

        statement.setString(1, dto.getEmpId());
        statement.setString(2, dto.getName());
        statement.setString(3, dto.getAddress());
        statement.setString(4, dto.getTelNum());
        statement.setString(5, dto.getDesignation());
        statement.setString(6, "system");
        statement.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(8, null);
        statement.setString(9, null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE);

        statement.setString(1, dto.getName());
        statement.setString(2, dto.getAddress());
        statement.setString(3, dto.getAddress());
        statement.setString(4, dto.getTelNum());
        statement.setString(5, dto.getEmpId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteEmployee(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);
        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public EmployeeDTO searchEmployee(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_EMPLOYEE);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        EmployeeDTO dto = null;

        if(resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String emp_name = resultSet.getString(2);
            String emp_address = resultSet.getString(3);
            String emp_tel = resultSet.getString(4);
            String emp_designation = resultSet.getString(5);

            dto = new EmployeeDTO(emp_id, emp_name, emp_address, emp_tel, emp_designation);
        }
        return dto;
    }

    public List<EmployeeDTO> getAllEmployeeBySearch(String searchVal) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_EMPLOYEE_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();
        ArrayList<EmployeeDTO> employeeDtoList = new ArrayList<>();

        while(resultSet.next()) {
            employeeDtoList.add(
                    new EmployeeDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return employeeDtoList;
    }

    public List<EmployeeDTO> getAllEmployee() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_EMPLOYEE);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<EmployeeDTO> EmployeeDtoList = new ArrayList<>();

        while(resultSet.next()) {
            EmployeeDtoList.add(
                    new EmployeeDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return EmployeeDtoList;
    }

    public List<EmployeeDTO> getAllEmployeeIDAndName() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_EMPLOYEE);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<EmployeeDTO> EmployeeDtoList = new ArrayList<>();

        while(resultSet.next()) {
            EmployeeDtoList.add(
                    new EmployeeDTO(
                            resultSet.getString(1),
                            resultSet.getString(2)
                    )
            );
        }
        return EmployeeDtoList;
    }

    public String generateNextCustomerId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_EMPLOYEE_ID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return splitEmployeeId(resultSet.getString(1));
        }
        return splitEmployeeId(null);
    }

    private String splitEmployeeId(String currentEmployeeId) {
        if(currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("E0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "E00" + id;
        } else {
            return "E001";
        }
    }
}
