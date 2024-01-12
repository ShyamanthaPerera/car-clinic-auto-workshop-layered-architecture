package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.SlotDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SlotBO extends SuperBO {

    ArrayList<SlotDTO> getAllSlots() throws SQLException, ClassNotFoundException;

    boolean saveSlots(SlotDTO slotDTO) throws SQLException, ClassNotFoundException;

    boolean updateSlots(SlotDTO slotDTO) throws SQLException, ClassNotFoundException;

    boolean existSlots(String id) throws SQLException, ClassNotFoundException;

    boolean deleteSlots(String id) throws SQLException, ClassNotFoundException;

    String generateNewSlotId() throws SQLException, ClassNotFoundException;
}
