package com.carclinic.car_clinic_auto_workshop.dao.custom.impl;

import com.carclinic.car_clinic_auto_workshop.dao.SQLUtil;
import com.carclinic.car_clinic_auto_workshop.dao.custom.AppointmentDAO;
import com.carclinic.car_clinic_auto_workshop.entity.AppointmentEntity;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.SAVE_APPOINTMENT;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public ArrayList<AppointmentEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(AppointmentEntity appointmentEntity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(SAVE_APPOINTMENT,
                appointmentEntity.getAppointmentId(),
                appointmentEntity.getSlotId(),
                appointmentEntity.getVehicleId(),
                appointmentEntity.getVehicleModel(),
                appointmentEntity.getCustomerId(),
                appointmentEntity.getCustomerName(),
                appointmentEntity.getDate(),
                appointmentEntity.getTime(),
                appointmentEntity.getIssue(),
                appointmentEntity.getStatus(),
                "system",
                new java.sql.Date(new java.util.Date().getTime()),
                null,
                null
        );
    }

    @Override
    public boolean update(AppointmentEntity appointmentEntity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<AppointmentEntity> search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

}
