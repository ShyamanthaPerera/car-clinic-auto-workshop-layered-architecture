package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.SlotDTO;
import com.carclinic.car_clinic_auto_workshop.model.SlotModel;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class SlotFormController {

    public AppointmentFormController appointmentFormController;
    public Stage stage;
    SlotModel slotModel = new SlotModel();
    SlotDTO slotDTO = new SlotDTO();
    @FXML
    private Label lblSlotNo;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblSpace;
    @FXML
    private Label lblChargingOutlet;
    @FXML
    private JFXButton btnSlot01;
    @FXML
    private JFXButton selectSlotBtn;
    @FXML
    private JFXButton btnSlot06;
    @FXML
    private JFXButton btnSlot02;
    @FXML
    private JFXButton btnSlot07;
    @FXML
    private JFXButton btnSlot03;
    @FXML
    private JFXButton btnSlot08;
    @FXML
    private JFXButton btnSlot04;
    @FXML
    private JFXButton btnSlot09;
    @FXML
    private JFXButton btnSlot05;
    @FXML
    private JFXButton btnSlot10;

    public void initialize() {
        ifSlotFree();
        selectSlotBtn.setDisable(true);
    }

    public void loadAllSlots() {
        try {
            List<SlotDTO> dtoList = slotModel.getAllSlot();
            for (int i = 0; i < dtoList.size(); i++) {
                SlotDTO slotDTO = dtoList.get(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSlot01OnAction(ActionEvent event) {


    }

    @FXML
    void btnSlot02OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot03OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot04OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot05OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot06OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot07OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot08OnAction(ActionEvent event) {

    }

    @FXML
    void btnSlot09OnAction(ActionEvent event) {

    }


    @FXML
    void btnSlot10OnAction(ActionEvent event) {

    }

    public void ifSlotFree() {
        try {
            List<SlotDTO> dtoList = slotModel.getAllStatus();
            dtoList.forEach(slotDTO -> {
                switch (slotDTO.getSlotId()) {
                    case "S01":
                        make(slotDTO, btnSlot01);
                        break;
                    case "S02":
                        make(slotDTO, btnSlot02);
                        break;
                    case "S03":
                        make(slotDTO, btnSlot03);
                        break;
                    case "S04":
                        make(slotDTO, btnSlot04);
                        break;
                    case "S05":
                        make(slotDTO, btnSlot05);
                        break;
                    case "S06":
                        make(slotDTO, btnSlot06);
                        break;
                    case "S07":
                        make(slotDTO, btnSlot07);
                        break;
                    case "S08":
                        make(slotDTO, btnSlot08);
                        break;
                    case "S09":
                        make(slotDTO, btnSlot09);
                        break;
                    case "S10":
                        make(slotDTO, btnSlot10);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void make(SlotDTO slotDTO, JFXButton button) {
        Image image;

        this.slotDTO = slotDTO;

        if (slotDTO.getStatus().equals("FREE")) {
            image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-car-repair-642.png"));
            button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        } else {
            image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-car-repair-64 (1).png"));
            button.setStyle("-fx-background-color: #19567c; -fx-text-fill: white;");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.BOTTOM);

        button.setOnAction((e) -> {
            lblSlotNo.setText(slotDTO.getSlotId());
            lblStatus.setText(slotDTO.getStatus());
            lblSpace.setText(slotDTO.getSpace());
            lblChargingOutlet.setText(slotDTO.getChargingOutlet());

            if (slotDTO.getStatus().equals("FREE")) {
                selectSlotBtn.setDisable(false);
            } else {
                selectSlotBtn.setDisable(true);
            }
        });
    }

    @FXML
    void selectSlotAction(ActionEvent event) {
        if (appointmentFormController != null) {
            appointmentFormController.getSlotData(slotDTO);
            stage.hide();
        }
    }

    public void setScene(Stage stage, AppointmentFormController appointmentFormController) {
        this.appointmentFormController = appointmentFormController;
        this.stage = stage;
    }

}
