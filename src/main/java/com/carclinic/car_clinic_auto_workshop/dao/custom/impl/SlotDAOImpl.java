package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.custom.SlotDAO;
import com.carclinic.car_clinic_auto_workshop.entity.SlotEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class SlotDAOImpl implements SlotDAO {
    @Override
    public ArrayList<SlotEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SlotEntity customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SlotEntity customerDTO) throws SQLException, ClassNotFoundException {
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
    public ArrayList<SlotEntity> search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
