package com.carclinic.car_clinic_auto_workshop.bo.custom;

import com.carclinic.car_clinic_auto_workshop.bo.SuperBO;
import com.carclinic.car_clinic_auto_workshop.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBO {

    boolean save(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;

    boolean updateSlot(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
    boolean saveOrder(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetailsList(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
    boolean updateItemList(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;

    ArrayList<AppointmentDTO> getAllAppointments() throws SQLException, ClassNotFoundException;

    boolean saveAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;

    boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;

    boolean existAppointment(String id) throws SQLException, ClassNotFoundException;

    boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException;

    String generateNewAppointmentId() throws SQLException, ClassNotFoundException;
}
