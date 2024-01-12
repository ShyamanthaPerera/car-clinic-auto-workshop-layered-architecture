package com.carclinic.car_clinic_auto_workshop.bo;

import com.carclinic.car_clinic_auto_workshop.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        APPOINTMENT,CUSTOMER,EMPLOYEE,ITEM,SLOT,SUPPLIER,USER,VEHICLE
    }

    public SuperBO getBOObjects(BOTypes boTypes){
        switch (boTypes){
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case SLOT:
                return new SlotBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            default:
                return null;
        }
    }
}
