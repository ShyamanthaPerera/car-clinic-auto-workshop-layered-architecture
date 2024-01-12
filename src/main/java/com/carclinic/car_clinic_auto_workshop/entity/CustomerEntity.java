package com.carclinic.car_clinic_auto_workshop.entity;

public class CustomerEntity {

    private String cusId;
    private String cusName;
    private String address;
    private String email;
    private String telNum;

    public CustomerEntity() {
    }

    public CustomerEntity(String cusId, String cusName, String address, String email, String telNum) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.address = address;
        this.email = email;
        this.telNum = telNum;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
}
