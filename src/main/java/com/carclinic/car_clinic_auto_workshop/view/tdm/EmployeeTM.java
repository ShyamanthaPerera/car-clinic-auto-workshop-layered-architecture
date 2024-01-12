package com.carclinic.car_clinic_auto_workshop.view.tdm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeTM {

    private String empId;
    private String empName;
    private String address;
    private String number;
    private String designation;
    private HBox btn;
}
