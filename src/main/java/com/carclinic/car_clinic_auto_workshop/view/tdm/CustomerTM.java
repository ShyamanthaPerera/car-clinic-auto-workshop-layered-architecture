package com.carclinic.car_clinic_auto_workshop.view.tdm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerTM {
    private String cusId;
    private String cusName;
    private String address;
    private String email;
    private String telNum;
    private HBox btn;
}
