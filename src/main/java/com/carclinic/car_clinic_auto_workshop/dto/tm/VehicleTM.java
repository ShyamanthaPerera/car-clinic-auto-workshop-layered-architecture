package com.carclinic.car_clinic_auto_workshop.dto.tm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class VehicleTM {

    private String vcl_id;
    private String cus_id;
    private String cus_name;
    private String vcl_ctgry;
    private String manufacturer;
    private String model;
    private HBox btn;
}
