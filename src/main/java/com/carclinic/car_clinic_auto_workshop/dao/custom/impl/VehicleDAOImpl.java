package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.custom.VehicleDAO;
import com.carclinic.car_clinic_auto_workshop.entity.VehicleEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ArrayList<VehicleEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(VehicleEntity customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(VehicleEntity customerDTO) throws SQLException, ClassNotFoundException {
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
    public ArrayList<VehicleEntity> search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
