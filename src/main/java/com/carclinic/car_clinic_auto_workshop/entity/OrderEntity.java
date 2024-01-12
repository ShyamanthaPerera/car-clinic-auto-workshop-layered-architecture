package com.carclinic.car_clinic_auto_workshop.entity;

public class OrderEntity {

    private String orderId;
    private String app_id;
    private String amount;
    private String date;
    private String time;

    public OrderEntity() {
    }

    public OrderEntity(String orderId, String app_id, String amount, String date, String time) {
        this.orderId = orderId;
        this.app_id = app_id;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", app_id='" + app_id + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
