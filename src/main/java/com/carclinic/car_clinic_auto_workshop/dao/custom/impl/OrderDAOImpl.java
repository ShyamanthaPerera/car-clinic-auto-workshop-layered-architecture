package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.SQLUtil;
import com.carclinic.car_clinic_auto_workshop.dao.custom.ItemDAO;
import com.carclinic.car_clinic_auto_workshop.dao.custom.OrderDAO;
import com.carclinic.car_clinic_auto_workshop.entity.ItemEntity;
import com.carclinic.car_clinic_auto_workshop.entity.OrderEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.SAVE_ORDER;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<OrderEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderEntity orderEntity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(SAVE_ORDER,
                orderEntity.getOrderId(),
                orderEntity.getApp_id(),
                orderEntity.getAmount(),
                orderEntity.getDate(),
                orderEntity.getTime(),
                "system",
                new java.sql.Date(new java.util.Date().getTime()),
                null,
                null
        );
    }

    @Override
    public boolean update(OrderEntity customerDTO) throws SQLException, ClassNotFoundException {
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
    public ArrayList<OrderEntity> search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
