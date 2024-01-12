package com.carclinic.car_clinic_auto_workshop.dao;

import com.carclinic.car_clinic_auto_workshop.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        APPOINTMENT,CUSTOMER,EMPLOYEE,ITEM,ORDER,ORDER_DETAIL,SLOT,SUPPLIER,VEHICLE
    }

    public SuperDAO getDAOObject(DAOTypes daoTypes){
        switch (daoTypes){
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case SLOT:
                return new SlotDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            default:
                return null;
        }
    }
}
