package com.carclinic.car_clinic_auto_workshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {

        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("Car Clinic Auto Workshop");
        stage.show();
        stage.getIcons().add(new javafx.scene.image.Image("com/carclinic/car_clinic_auto_workshop/view/images/icons8-car-repair-64.png"));
    }
}
