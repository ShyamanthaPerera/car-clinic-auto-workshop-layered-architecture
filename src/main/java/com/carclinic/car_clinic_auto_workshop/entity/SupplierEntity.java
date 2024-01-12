package com.carclinic.car_clinic_auto_workshop.entity;

public class SupplierEntity {

    private String supId;
    private String name;
    private String address;
    private String telNum;

    public SupplierEntity() {
    }

    public SupplierEntity(String supId, String name, String address, String telNum) {
        this.supId = supId;
        this.name = name;
        this.address = address;
        this.telNum = telNum;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Override
    public String toString() {
        return "SupplierEntity{" +
                "supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
}
