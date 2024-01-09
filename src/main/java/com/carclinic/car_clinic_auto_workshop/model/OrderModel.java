package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class OrderModel {

//    public boolean saveOrder(OrderDTO dto) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(SAVE_ORDER);
//
//        statement.setString(1, dto.getOrderId());
//        statement.setString(2, dto.getAmount());
//        statement.setString(3, dto.getDate());
//        statement.setString(4, dto.getTime());
//        statement.setString(5, dto.getSlotId());
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public boolean updateOrder(OrderDTO dto) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER);
//
//        statement.setString(1, dto.getAmount());
//        statement.setString(2, dto.getDate());
//        statement.setString(3, dto.getTime());
//        statement.setString(4, dto.getSlotId());
//        statement.setString(5, dto.getOrderId());
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public boolean deleteOrder(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(DELETE_ORDER);
//
//        statement.setString(1, id);
//
//        return statement.executeUpdate() > 0;
//    }
//
//    public OrderDTO searchOrder(String id) throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection ();
//        PreparedStatement statement = connection.prepareStatement(SEARCH_ORDER);
//
//        statement.setString(1, id);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        OrderDTO dto = null;
//
//        if(resultSet.next()) {
//            String ord_id = resultSet.getString(1);
//            String amount = resultSet.getString(2);
//            String date = resultSet.getString(3);
//            String time = resultSet.getString(4);
//            String slot_id = resultSet.getString(5);
//
//            dto = new OrderDTO(ord_id, amount, date, time, slot_id);
//        }
//        return dto;
//    }
//
//    public List<OrderDTO> getAllOrder() throws SQLException {
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_ORDER);
//        ResultSet resultSet = statement.executeQuery();
//
//        ArrayList<OrderDTO> OrderDtoList = new ArrayList<>();
//
//        while(resultSet.next()) {
//            OrderDtoList.add(
//                    new OrderDTO(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4),
//                            resultSet.getString(5)
//                    )
//            );
//        }
//        return OrderDtoList;
//    }

        public String generateNextOrderId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_ORDER_ID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return splitOrderId(resultSet.getString(1));
            }
            return splitOrderId(null);
        }

        private String splitOrderId(String currentOrderId) {
            if (currentOrderId != null) {
                String[] split = currentOrderId.split("O0");

                int id = Integer.parseInt(split[1]); //01
                id++;
                return "O00" + id;
            } else {
                return "O001";
            }
        }



}
