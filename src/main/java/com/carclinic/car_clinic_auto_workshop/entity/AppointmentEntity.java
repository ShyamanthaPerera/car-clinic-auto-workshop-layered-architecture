package com.carclinic.car_clinic_auto_workshop.entity;

public class AppointmentEntity {

    private String appointmentId;
    private String slotId;
    private String vehicleId;
    private String vehicleModel;
    private String customerId;
    private String customerName;
    private String date;
    private String time;
    private String issue;
    private String status;

    public AppointmentEntity() {
    }

    public AppointmentEntity(String appointmentId, String slotId, String vehicleId, String vehicleModel, String customerId, String customerName, String date, String time, String issue, String status) {
        this.appointmentId = appointmentId;
        this.slotId = slotId;
        this.vehicleId = vehicleId;
        this.vehicleModel = vehicleModel;
        this.customerId = customerId;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.issue = issue;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "appointmentId='" + appointmentId + '\'' +
                ", slotId='" + slotId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", issue='" + issue + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
