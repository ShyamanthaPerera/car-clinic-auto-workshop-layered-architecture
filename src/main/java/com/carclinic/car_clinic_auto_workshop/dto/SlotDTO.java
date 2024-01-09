package com.carclinic.car_clinic_auto_workshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SlotDTO {

    private String slotId;
    private String status;
    private String space;
    private String chargingOutlet;


}
