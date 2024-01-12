package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.SQLUtil;
import com.carclinic.car_clinic_auto_workshop.dao.custom.CustomerDAO;
import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerEntity> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute(LOAD_ALL_CUSTOMER);
        ArrayList<CustomerEntity> getAllCustomers = new ArrayList<>();

        while (resultSet.next()) {
            CustomerEntity customerEntity = new CustomerEntity(
                    resultSet.getString("cus_id"),
                    resultSet.getString("cus_name"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("tel_num")
            );
            getAllCustomers.add(customerEntity);
        }
        return getAllCustomers;
    }

    @Override
    public boolean save(CustomerEntity customerEntity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute(SAVE_CUSTOMER,
                customerEntity.getCusId(),
                customerEntity.getCusName(),
                customerEntity.getAddress(),
                customerEntity.getEmail(),
                customerEntity.getTelNum(),
                "system",
                new java.sql.Date(new java.util.Date().getTime()),
                null,
                null
        );
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(UPDATE_CUSTOMER,
                customerEntity.getCusName(),
                customerEntity.getAddress(),
                customerEntity.getEmail(),
                customerEntity.getTelNum(),
                customerEntity.getCusId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(DELETE_CUSTOMER,
                id
        );
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute(GET_LAST_CUSTOMER_ID);
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

    @Override
    public ArrayList<CustomerEntity> search(String searchVal) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_CUSTOMER_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();

        ArrayList<CustomerEntity> customerDTOS = new ArrayList<>();

        while(resultSet.next()) {
            customerDTOS.add(
                    new CustomerEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return customerDTOS;
    }
}
