package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.VehicleBO;
import com.carclinic.car_clinic_auto_workshop.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
    @Override
    public ArrayList<VehicleDTO> getAllVehicles() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveVehicles(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateVehicles(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existVehicles(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteVehicles(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewVehicleId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
