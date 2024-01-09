package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDTO {

    private String orderId;
    private String app_id;
    private String amount;
    private String date;
    private String time;
}
