package com.carclinic.car_clinic_auto_workshop.dto.tm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierTM {

    private String supId;
    private String supName;
    private String address;
    private String telNum;
    private HBox btn;
}
