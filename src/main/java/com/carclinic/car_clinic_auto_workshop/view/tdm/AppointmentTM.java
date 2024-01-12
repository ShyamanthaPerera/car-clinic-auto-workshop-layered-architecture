package com.carclinic.car_clinic_auto_workshop.view.tdm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class AppointmentTM {

    private String appointmentId;
    private String slotId;
    private String vehicleId;
    private String vehicleModel;
    private String customerId;
    private String customerName;
    private String date;
    private String time;
    private String issue;
    private String status;
    public HBox btn;
}
