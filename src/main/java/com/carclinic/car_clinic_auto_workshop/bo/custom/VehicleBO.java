package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO extends SuperBO {

    ArrayList<VehicleDTO> getAllVehicles() throws SQLException, ClassNotFoundException;

    boolean saveVehicles(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;

    boolean updateVehicles(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;

    boolean existVehicles(String id) throws SQLException, ClassNotFoundException;

    boolean deleteVehicles(String id) throws SQLException, ClassNotFoundException;

    String generateNewVehicleId() throws SQLException, ClassNotFoundException;
}
