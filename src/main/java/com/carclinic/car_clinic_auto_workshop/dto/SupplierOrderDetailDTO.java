package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierOrderDetailDTO {

    private String supId;
    private String itemId;
    private int qty;
}
