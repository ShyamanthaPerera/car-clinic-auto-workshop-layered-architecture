package com.carclinic.car_clinic_auto_workshop.dto;

import com.carclinic.car_clinic_auto_workshop.view.tdm.ItemTM;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AppointmentDTO {
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

    private OrderDTO orderDTO;
    private List<EmployeeDTO> employeeDTOList;
    private List<ItemTM> itemDTOList;

//    public AppointmentDTO(String appointmentId, String customerId, String vehicleId, String slotId, String date, String time, String issue) {
//        this.appointmentId = appointmentId;
//        this.customerId = customerId;
//        this.vehicleId = vehicleId;
//        this.slotId = slotId;
//        this.date = date;
//        this.time = time;
//        this.issue = issue;
//    }

    public AppointmentDTO(String appointmentId, String slotId, String vehicleId, String vehicleModel, String customerId, String customerName, String date, String time, String issue, String status) {
        this.appointmentId = appointmentId;
        this.slotId = slotId;
        this.vehicleId = vehicleId;
        this.vehicleModel = vehicleModel;
        this.customerId = customerId;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.issue = issue;
        this.status = status;
    }



//    public AppointmentDTO(String appointmentId, String slotId, String vehicleId, String vehicleModel, String customerId, String customerName, String date, String time, String issue) {
//        this.appointmentId = appointmentId;
//        this.slotId = slotId;
//        this.vehicleId = vehicleId;
//        this.vehicleModel = vehicleModel;
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.date = date;
//        this.time = time;
//        this.issue = issue;
//    }
}
