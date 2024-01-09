package com.carclinic.car_clinic_auto_workshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane root;

    @FXML
    private Label lblLogginUser;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    void btnAppointmentsOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/AppointmentForm.fxml")));

    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/CustomerForm.fxml")));

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/SlotForm.fxml")));

    }

    @FXML
    void btnEmployeesOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/EmployeeForm.fxml")));
    }

    @FXML
    void btnItemsOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/ItemForm.fxml")));

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {

//        root.getChildren().clear();
//        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/.fxml")));

    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/SupplierForm.fxml")));

    }

    @FXML
    void btnUsersOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/UserForm.fxml")));

    }

    @FXML
    void btnVehiclesOnAction(ActionEvent event) throws IOException {

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/VehicleForm.fxml")));

    }
}
