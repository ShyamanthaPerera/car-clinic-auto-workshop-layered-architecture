package com.carclinic.car_clinic_auto_workshop.entity;

public class EmployeeEntity {

    private String empId;
    private String name;
    private String address;
    private String telNum;
    private String designation;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String empId, String name, String address, String telNum, String designation) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.telNum = telNum;
        this.designation = designation;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telNum='" + telNum + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
