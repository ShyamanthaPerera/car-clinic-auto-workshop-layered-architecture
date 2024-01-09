package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO {

    private String cusId;
    private String cusName;
    private String address;
    private String email;
    private String telNum;
}
