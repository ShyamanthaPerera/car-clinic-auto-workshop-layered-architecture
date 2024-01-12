package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.SlotBO;
import com.carclinic.car_clinic_auto_workshop.dto.SlotDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SlotBOImpl implements SlotBO {
    @Override
    public ArrayList<SlotDTO> getAllSlots() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveSlots(SlotDTO slotDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSlots(SlotDTO slotDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existSlots(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteSlots(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewSlotId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
