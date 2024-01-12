package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean existItems(String id) throws SQLException, ClassNotFoundException;

    boolean deleteItems(String id) throws SQLException, ClassNotFoundException;

    String generateNewItemId() throws SQLException, ClassNotFoundException;
}
