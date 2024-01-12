package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.ItemBO;
import com.carclinic.car_clinic_auto_workshop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existItems(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItems(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
