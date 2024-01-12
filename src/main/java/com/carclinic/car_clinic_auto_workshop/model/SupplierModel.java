package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class SupplierModel {

    public boolean saveSupplier(SupplierDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_SUPPLIER);

        statement.setString(1, dto.getSupId());
        statement.setString(2, dto.getName());
        statement.setString(3, dto.getAddress());
        statement.setString(4, dto.getTelNum());
        statement.setString(5, "system");
        statement.setDate(6,new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(7,null);
        statement.setString(8,null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateSupplier(SupplierDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_SUPPLIER);

        statement.setString(1, dto.getName());
        statement.setString(2, dto.getAddress());
        statement.setString(3, dto.getTelNum());
        statement.setString(4, dto.getSupId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteSupplier(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SUPPLIER);

        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public SupplierDTO searchSupplier(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_SUPPLIER);

        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        SupplierDTO dto = null;

        if(resultSet.next()) {
            String sup_id = resultSet.getString(1);
            String sup_name = resultSet.getString(2);
            String sup_address = resultSet.getString(3);
            String sup_tel = resultSet.getString(4);

            dto = new SupplierDTO(sup_id, sup_name, sup_address, sup_tel);
        }
        return dto;
    }

    public List<SupplierDTO> getAllSupplier() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_SUPPLIER);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<SupplierDTO> SupplierDtoList = new ArrayList<>();

        while(resultSet.next()) {
            SupplierDtoList.add(
                    new SupplierDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return SupplierDtoList;
    }

    public List<SupplierDTO> getAllSupplierBySearch(String searchVal) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_SUPPLIER_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();

        ArrayList<SupplierDTO> supplierDtoList = new ArrayList<>();

        while(resultSet.next()) {
            supplierDtoList.add(
                    new SupplierDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(5)
                    )
            );
        }
        return supplierDtoList;
    }

    public String generateNextSupplierId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_SUPPLIER_ID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return splitSupplierId(resultSet.getString(1));
        }
        return splitSupplierId(null);
    }

    private String splitSupplierId(String currentSupplierId) {
        if(currentSupplierId != null) {
            String[] split = currentSupplierId.split("S0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "S00" + id;
        } else {
            return "S001";
        }
    }


}
