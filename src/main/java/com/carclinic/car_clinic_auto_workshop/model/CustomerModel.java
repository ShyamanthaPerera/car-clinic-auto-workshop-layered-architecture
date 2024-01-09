package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class CustomerModel {
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_CUSTOMER);

        statement.setString(1, dto.getCusId());
        statement.setString(2, dto.getCusName());
        statement.setString(3, dto.getAddress());
        statement.setString(4, dto.getEmail());
        statement.setString(5, dto.getTelNum());
        statement.setString(6, "system");
        statement.setDate(7,new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(8,null);
        statement.setString(9,null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);

        statement.setString(1, dto.getCusName());
        statement.setString(2, dto.getAddress());
        statement.setString(3, dto.getEmail());
        statement.setString(4, dto.getTelNum());
        statement.setString(5, dto.getCusId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);

        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public CustomerDTO searchCustomer(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_CUSTOMER);

        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        CustomerDTO dto = null;

        if(resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String cus_name = resultSet.getString(2);
            String cus_address = resultSet.getString(3);
            String cus_email = resultSet.getString(3);
            String cus_tel = resultSet.getString(4);

            dto = new CustomerDTO(cus_id, cus_name, cus_address, cus_email, cus_tel);
        }
        return dto;
    }


    public List<CustomerDTO> getAllCustomerBySearch(String searchVal) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_CUSTOMER_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();

        ArrayList<CustomerDTO> customerDtoList = new ArrayList<>();

        while(resultSet.next()) {
            customerDtoList.add(
                    new CustomerDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return customerDtoList;
    }


    public List<CustomerDTO> getAllCustomer() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_CUSTOMER);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<CustomerDTO> customerDtoList = new ArrayList<>();

        while(resultSet.next()) {
            customerDtoList.add(
                    new CustomerDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return customerDtoList;
    }

    public String generateNextCustomerId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_CUSTOMER_ID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return splitCustomerId(resultSet.getString(1));
        }
        return splitCustomerId(null);
    }

    private String splitCustomerId(String currentCustomerId) {
        if(currentCustomerId != null) {
            String[] split = currentCustomerId.split("C0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "C00" + id;
        } else {
            return "C001";
        }
    }
}
