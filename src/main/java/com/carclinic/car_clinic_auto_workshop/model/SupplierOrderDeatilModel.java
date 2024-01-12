package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.SupplierOrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class SupplierOrderDeatilModel {

    public boolean saveSupplierOrderDetail(SupplierOrderDetailDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_SUPPLIER_ORDER_DETAIL);

        statement.setString(1, dto.getSupId());
        statement.setString(2, dto.getItemId());
        statement.setInt(3, dto.getQty());

        return statement.executeUpdate() > 0;
    }

//    public boolean updateSupplierOrderDetail(SupplierOrderDetailDTO dto) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(UPDATE_SUPPLIER_ORDER_DETAIL);
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
//    public boolean deleteSupplierOrderDetail(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(DELETE_SUPPLIER_ORDER_DETAIL);
//
//        statement.setString(1, id);
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public SupplierOrderDetailDTO searchSupplierOrderDetail(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection ();
//        PreparedStatement statement = connection.prepareStatement(SEARCH_SUPPLIER_ORDER_DETAIL);
//
//        statement.setString(1, id);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        SupplierOrderDetailDTO dto = null;
//
//        if(resultSet.next()) {
//            String cus_id = resultSet.getString(1);
//            String cus_user = resultSet.getString(2);
//            String cus_name = resultSet.getString(3);
//            String cus_address = resultSet.getString(4);
//            String cus_tel = resultSet.getString(5);
//
//            dto = new SupplierOrderDetailDTO(cus_id, cus_user, cus_name, cus_address, cus_tel);
//        }
//        return dto;
//    }



    public List<SupplierOrderDetailDTO> getAllSupplierOrderDetail() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_SUPPLIER_ORDER_DETAIL);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<SupplierOrderDetailDTO> SupplierOrderDetailDtoList = new ArrayList<>();

        while(resultSet.next()) {
            SupplierOrderDetailDtoList.add(
                    new SupplierOrderDetailDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)
                    )
            );
        }
        return SupplierOrderDetailDtoList;
    }




}
