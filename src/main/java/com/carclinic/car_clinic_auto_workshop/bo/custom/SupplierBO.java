package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    boolean saveSuppliers(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean updateSuppliers(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean existSuppliers(String id) throws SQLException, ClassNotFoundException;

    boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException;

    String generateNewSupplierId() throws SQLException, ClassNotFoundException;
}
