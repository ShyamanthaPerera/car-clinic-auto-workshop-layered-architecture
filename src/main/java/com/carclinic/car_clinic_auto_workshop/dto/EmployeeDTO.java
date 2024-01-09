package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeDTO {
    private String empId;
    private String name;
    private String address;
    private String telNum;
    private String designation;

    public EmployeeDTO(String empId, String name) {
        this.empId = empId;
        this.name = name;
    }
}
