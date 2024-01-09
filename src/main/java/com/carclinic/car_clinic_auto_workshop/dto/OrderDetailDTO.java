package com.carclinic.car_clinic_auto_workshop.dto;

import com.carclinic.car_clinic_auto_workshop.dto.tm.ItemTM;
import lombok.*;

import java.util.List;

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
