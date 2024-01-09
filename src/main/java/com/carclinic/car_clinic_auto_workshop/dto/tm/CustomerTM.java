package com.carclinic.car_clinic_auto_workshop.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
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
