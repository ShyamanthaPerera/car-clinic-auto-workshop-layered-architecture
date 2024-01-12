package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetailDTO {

    private String orderId;
    private String itemId;
    private int quantity;
}
