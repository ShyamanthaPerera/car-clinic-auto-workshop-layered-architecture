package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class OrderDetailModel {

    public boolean saveOrderDetail(OrderDetailDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_ORDER_DETAIL);

        statement.setString(1, dto.getOrderId());
        statement.setString(2, dto.getItemId());
        statement.setInt(3, dto.getQuantity());

        return statement.executeUpdate() > 0;
    }

//    public boolean updateOrderDetail(OrderDetailDTO dto) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_DETAIL);
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
//    public boolean deleteOrderDetail(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_DETAIL);
//
//        statement.setString(1, id);
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public OrderDetailDTO searchOrderDetail(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection ();
//        PreparedStatement statement = connection.prepareStatement(SEARCH_ORDER_DETAIL);
//
//        statement.setString(1, id);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        OrderDetailDTO dto = null;
//
//        if(resultSet.next()) {
//            String cus_id = resultSet.getString(1);
//            String cus_user = resultSet.getString(2);
//            String cus_name = resultSet.getString(3);
//            String cus_address = resultSet.getString(4);
//            String cus_tel = resultSet.getString(5);
//
//            dto = new OrderDetailDTO(cus_id, cus_user, cus_name, cus_address, cus_tel);
//        }
//        return dto;
//    }



    public List<OrderDetailDTO> getAllOrderDetail() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_ORDER_DETAIL);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<OrderDetailDTO> OrderDetailDtoList = new ArrayList<>();

        while(resultSet.next()) {
            OrderDetailDtoList.add(
                    new OrderDetailDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)
                    )
            );
        }
        return OrderDetailDtoList;
    }




}
