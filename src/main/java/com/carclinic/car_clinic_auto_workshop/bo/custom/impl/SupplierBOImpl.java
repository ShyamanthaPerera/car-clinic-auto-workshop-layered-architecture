package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.SupplierBO;
import com.carclinic.car_clinic_auto_workshop.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveSuppliers(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSuppliers(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existSuppliers(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewSupplierId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
