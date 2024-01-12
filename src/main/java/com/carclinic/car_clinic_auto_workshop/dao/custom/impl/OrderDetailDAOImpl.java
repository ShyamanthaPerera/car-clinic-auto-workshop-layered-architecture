package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.SQLUtil;
import com.carclinic.car_clinic_auto_workshop.dao.custom.OrderDetailDAO;
import com.carclinic.car_clinic_auto_workshop.entity.OrderDetailEntity;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.SAVE_ORDER_DETAIL;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetailEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailEntity orderDetailEntity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(SAVE_ORDER_DETAIL,
                orderDetailEntity.getOrderId(),
                orderDetailEntity.getItemId(),
                orderDetailEntity.getQuantity(),
                "system",
                new java.sql.Date(new java.util.Date().getTime()),
                null,
                null
        );
    }

    @Override
    public boolean update(OrderDetailEntity customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailEntity> search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
