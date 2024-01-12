package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.SQLUtil;
import com.carclinic.car_clinic_auto_workshop.dao.custom.ItemDAO;
import com.carclinic.car_clinic_auto_workshop.entity.ItemEntity;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.UPDATE_NEW_QTY;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ItemEntity customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ItemEntity itemEntity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(UPDATE_NEW_QTY,
                itemEntity.getQtyOnHand(),
                itemEntity.getItemId()
        );
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
    public ArrayList<ItemEntity> search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
