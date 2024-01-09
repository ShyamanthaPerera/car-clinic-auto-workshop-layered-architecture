package com.carclinic.car_clinic_auto_workshop.dto.tm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserTM {
    private String username;
    private String password;
    private String name;
    private HBox btn;
}
