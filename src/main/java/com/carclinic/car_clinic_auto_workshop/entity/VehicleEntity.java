package com.carclinic.car_clinic_auto_workshop.entity;

public class VehicleEntity {

    private String vclId;
    private String cusId;
    private String vclCategory;
    private String manufacturer;
    private String model;
    private String cusName;

    public VehicleEntity() {
    }

    public VehicleEntity(String vclId, String cusId, String vclCategory, String manufacturer, String model, String cusName) {
        this.vclId = vclId;
        this.cusId = cusId;
        this.vclCategory = vclCategory;
        this.manufacturer = manufacturer;
        this.model = model;
        this.cusName = cusName;
    }

    public String getVclId() {
        return vclId;
    }

    public void setVclId(String vclId) {
        this.vclId = vclId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getVclCategory() {
        return vclCategory;
    }

    public void setVclCategory(String vclCategory) {
        this.vclCategory = vclCategory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "vclId='" + vclId + '\'' +
                ", cusId='" + cusId + '\'' +
                ", vclCategory='" + vclCategory + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", cusName='" + cusName + '\'' +
                '}';
    }
}
