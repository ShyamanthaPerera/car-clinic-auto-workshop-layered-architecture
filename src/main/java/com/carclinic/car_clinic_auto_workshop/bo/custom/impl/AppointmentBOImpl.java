package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.AppointmentBO;
import com.carclinic.car_clinic_auto_workshop.dto.AppointmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBO {
    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existAppointment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewAppointmentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
