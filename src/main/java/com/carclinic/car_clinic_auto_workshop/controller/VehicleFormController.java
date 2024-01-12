package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.dto.VehicleDTO;
import com.carclinic.car_clinic_auto_workshop.view.tdm.VehicleTM;
import com.carclinic.car_clinic_auto_workshop.model.VehicleModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class VehicleFormController {

    @FXML
    private Label lblModel;

    @FXML
    private Label lblId;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblManufacturer;

    @FXML
    private Label lblCategory;

    @FXML
    private JFXButton vehicleUpdateBtn;

    @FXML
    private JFXButton vehicleAddBtn;

    @FXML
    private TextField textVehicleID;

    @FXML
    private TextField textCustomerID;

    @FXML
    private TextField textCustomerName;

    @FXML
    private TextField textCategory;

    @FXML
    private TextField textManufacturer;

    @FXML
    private TextField textModel;

    @FXML
    private TableView<VehicleTM> vehicleTbl;

    @FXML
    private TableColumn<?, ?> vehicleIDCol;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TableColumn<?, ?> categoryCol;

    @FXML
    private TableColumn<?, ?> manufacturerCol;

    @FXML
    private TableColumn<?, ?> modelCol;

    @FXML
    private TableColumn<?, ?> actionCol1;

    @FXML
    private JFXTextField txtdynamicSearch;

    VehicleModel vehicleModel = new VehicleModel();

    ObservableList<VehicleTM> observableList = FXCollections.observableArrayList();

    public AppointmentFormController appointmentFormController;

    public Stage stage;

    boolean isUpdate;

    public void initialize() {
        generateNextVehicleId();
        setCellValueFactory();
        loadAllVehicles();

        vehicleAddBtn.setDisable(true);
        vehicleUpdateBtn.setDisable(true);

        isUpdate = false;
    }

    private void setCellValueFactory() {

        vehicleIDCol.setCellValueFactory(new PropertyValueFactory<>("vcl_id"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("cus_name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("vcl_ctgry"));
        manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        actionCol1.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    void btnAddCustomerOnActionInVehicle (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/CustomerForm.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            CustomerFormController controller = loader.getController();
            controller.setScene(stage, this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScene(Stage stage, AppointmentFormController appointmentFormController) {
        this.appointmentFormController = appointmentFormController;
        this.stage = stage;
        loadAllVehicles();
    }

    public void getData(CustomerDTO customer) {
        textCustomerID.setText(customer.getCusId());
        textCustomerName.setText(customer.getCusName());
    }

    @FXML
    void btnAddVehicleOnAction(ActionEvent event) {

        String vclId = textVehicleID.getText();
        String cusID = textCustomerID.getText();
        String vclCategory = textCategory.getText();
        String manufacturer = textManufacturer.getText();
        String model = textModel.getText();

        VehicleDTO dto = new VehicleDTO(vclId, cusID, vclCategory, manufacturer, model);

        try {
            boolean isSaved = vehicleModel.saveVehicle(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Saved").show();
                VehicleclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void VehicleclearFields() {

        textVehicleID.setText("");
        textCustomerID.setText("");
        textCustomerName.setText("");
        textCategory.setText("");
        textManufacturer.setText("");
        textModel.setText("");

        vehicleAddBtn.setDisable(true);
        vehicleUpdateBtn.setDisable(true);
        isUpdate=false;

        generateNextVehicleId();
        loadAllVehicles();
    }

    @FXML
    void btnUpdateVehicleOnAction(ActionEvent event) {

        String vclId = textVehicleID.getText();
        String cusID = textCustomerID.getText();
        String vclCategory = textCategory.getText();
        String manufacturer = textManufacturer.getText();
        String model = textModel.getText();

        VehicleDTO vehicleDTO = new VehicleDTO(vclId, cusID, vclCategory, manufacturer, model);

        try {
            boolean isUpdate = vehicleModel.updateVehicle(vehicleDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Updated").show();
                VehicleclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnVehicleClearOnAction(ActionEvent event) {
        VehicleclearFields();
    }

    public void loadAllVehicles() {
        try {
            List<VehicleDTO> dtoList = vehicleModel.getAllVehicle();
            mapCustomerTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapCustomerTableVal(List<VehicleDTO> dtoList) {

        observableList.clear();
        for (VehicleDTO vehicleDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            if(appointmentFormController==null) {
                hbox.getChildren().addAll(createViewButton(vehicleDTO), createUpdateButton(vehicleDTO), createDeleteButton(vehicleDTO));
            }else{
                hbox.getChildren().addAll(createViewButton(vehicleDTO), createUpdateButton(vehicleDTO), createDeleteButton(vehicleDTO), createSelectButton(vehicleDTO));
            }
                observableList.add(
                        new VehicleTM(
                                vehicleDTO.getVclId(),
                                vehicleDTO.getCusId(),
                                vehicleDTO.getCusName(),
                                vehicleDTO.getVclCategory(),
                                vehicleDTO.getManufacturer(),
                                vehicleDTO.getModel(),
                                hbox
                        )
                );
        }
        vehicleTbl.setItems(observableList);
    }

    private JFXButton createViewButton(VehicleDTO vehicleDTO) {
        JFXButton btn = new JFXButton();
        setViewBtnAction(btn, vehicleDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createUpdateButton(VehicleDTO vehicleDTO) {
        JFXButton btn = new JFXButton();
        setUpdateBtnAction(btn, vehicleDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createDeleteButton(VehicleDTO vehicleDTO) {
        JFXButton btn = new JFXButton();
        setDeleteBtnAction(btn, vehicleDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createSelectButton(VehicleDTO vehicleDTO) {
        JFXButton btn = new JFXButton();
        setSelectBtnAction(btn, vehicleDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-select-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private void setViewBtnAction(Button btn, VehicleDTO vehicleDTO) {
        btn.setOnAction((e) -> {

            lblModel.setText(vehicleDTO.getModel());
            lblId.setText(vehicleDTO.getVclId());
            lblCusName.setText(vehicleDTO.getCusName());
            lblManufacturer.setText(vehicleDTO.getManufacturer());
            lblCategory.setText(vehicleDTO.getVclCategory());
        });
    }

    private void setUpdateBtnAction(Button btn, VehicleDTO vehicleDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;

            textVehicleID.setText(vehicleDTO.getVclId());
            textCustomerID.setText(vehicleDTO.getCusId());
            textCustomerName.setText(vehicleDTO.getCusName());
            textCategory.setText(vehicleDTO.getVclCategory());
            textManufacturer.setText(vehicleDTO.getManufacturer());
            textModel.setText(vehicleDTO.getModel());
        });
    }

    private void setDeleteBtnAction(Button btn, VehicleDTO vehicleDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                String id = vehicleDTO.getVclId();

                try {
                    boolean isDelete = vehicleModel.deleteVehicle(id);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = vehicleTbl.getSelectionModel().getFocusedIndex();

                observableList.remove(focusedIndex);
                vehicleTbl.refresh();
            }
        });
    }

    private void setSelectBtnAction(Button btn, VehicleDTO vehicleDTO) {
        btn.setOnAction((e) -> {
            if(appointmentFormController!=null){
                appointmentFormController.getData(vehicleDTO);
                stage.hide();
            }
        });
    }

    @FXML
    void dynamicSearchAction(KeyEvent event) {
        if (!txtdynamicSearch.getText().trim().isEmpty()) {
            try {
                List<VehicleDTO> dtoList = vehicleModel.getAllVehicleBySearch(txtdynamicSearch.getText());
                mapCustomerTableVal(dtoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadAllVehicles();
        }
    }

    @FXML
    void validateVehicleForm(KeyEvent keyEvent) {

        final boolean category = StringUtils.isNullOrEmpty(textCategory.getText()) ? false
                : validationLogic(textCategory, "([A-Z a-z])+", keyEvent, textManufacturer);

        final boolean manufacturer = StringUtils.isNullOrEmpty(textManufacturer.getText()) ?
                false : validationLogic(textManufacturer, "([A-Z a-z])+", keyEvent, textModel);

        final boolean model = StringUtils.isNullOrEmpty(textModel.getText()) ?
                false : validationLogic(textModel, "([A-Z a-z])+", keyEvent, null);


        if (category && manufacturer && model) {
            if (isUpdate) {
                vehicleUpdateBtn.setDisable(false);
            }else {
                vehicleAddBtn.setDisable(false);
            }
        } else {
            if (isUpdate) {
                vehicleUpdateBtn.setDisable(true);
            }else {
                vehicleAddBtn.setDisable(true);
            }
        }
    }

    public boolean validationLogic(TextField name, String regex, KeyEvent keyEvent, TextField nextName) {
        if (Pattern.matches(regex, name.getText())) {
            name.getParent().setStyle("-fx-border-color: green");
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if(nextName!=null){
                    nextName.requestFocus();
                }else {
                    vehicleAddBtn.requestFocus();
                }
            }
            return true;
        } else {
            name.getParent().setStyle("-fx-border-color: red");
            return false;
        }
    }

    private void generateNextVehicleId() {
        try {
            String vehicleId = vehicleModel.generateNextVehicleId();
            textVehicleID.setText(vehicleId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
