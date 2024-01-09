package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ItemDTO {

    private String itemId;
    private String model;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
}
