package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.EmployeeDTO;
import com.carclinic.car_clinic_auto_workshop.dto.tm.AppointmentEmployeeTM;
import com.carclinic.car_clinic_auto_workshop.dto.tm.EmployeeTM;
import com.carclinic.car_clinic_auto_workshop.model.EmployeeModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeeFormController {

    public AppointmentFormController appointmentFormController;
    public Stage stage;
    boolean isUpdate;
    ObservableList<EmployeeTM> observableList = FXCollections.observableArrayList();
    ObservableList<AppointmentEmployeeTM> observableListt = FXCollections.observableArrayList();
    EmployeeModel employeeModel = new EmployeeModel();
    @FXML
    private Label lblName;
    @FXML
    private Label lblId;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblNumber;
    @FXML
    private Label lblDesignation;
    @FXML
    private JFXButton employeeUpdateBtn;
    @FXML
    private JFXButton employeeAddBtn;
    @FXML
    private TextField textEmployeeID;
    @FXML
    private TextField textEmployeeName;
    @FXML
    private TextField textEmployeeAddress;
    @FXML
    private TextField textEmployeeNumber;
    @FXML
    private TextField textEmployeeDesignation;
    @FXML
    private TableView<EmployeeTM> EmployeeTbl;
    @FXML
    private TableColumn<?, ?> EmployeeIDCol;
    @FXML
    private TableColumn<?, ?> EmployeeNameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> numberCol;
    @FXML
    private TableColumn<?, ?> designationCol;
    @FXML
    private TableColumn<?, ?> actionCol;
    @FXML
    private JFXTextField txtdynamicSearch;

    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
        employeeAddBtn.setDisable(true);
        employeeUpdateBtn.setDisable(true);
        textEmployeeID.requestFocus();
        generateNextEmployeeId();
        isUpdate = false;
    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {

        String empId = textEmployeeID.getText();
        String empName = textEmployeeName.getText();
        String empAddress = textEmployeeAddress.getText();
        String empNumber = textEmployeeNumber.getText();
        String empDesignation = textEmployeeDesignation.getText();

        EmployeeDTO dto = new EmployeeDTO(empId, empName, empAddress, empNumber, empDesignation);

        try {
            boolean isSaved = employeeModel.saveEmployee(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved").show();
                employeeclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateEmployeeOnAction(ActionEvent event) {

        String empId = textEmployeeID.getText();
        String empName = textEmployeeName.getText();
        String empAddress = textEmployeeAddress.getText();
        String empNumber = textEmployeeNumber.getText();
        String empDesignation = textEmployeeDesignation.getText();

        EmployeeDTO dto = new EmployeeDTO(empId, empName, empAddress, empNumber, empDesignation);

        try {
            boolean isUpdate = employeeModel.updateEmployee(dto);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
                employeeclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        EmployeeIDCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        EmployeeNameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setViewBtnAction(Button btn, EmployeeDTO employeeDTO) {
        btn.setOnAction((e) -> {

            lblName.setText(employeeDTO.getName());
            lblId.setText(employeeDTO.getEmpId());
            lblAddress.setText(employeeDTO.getAddress());
            lblNumber.setText(employeeDTO.getTelNum());
            lblDesignation.setText(employeeDTO.getDesignation());
        });
    }

    private void setUpdateBtnAction(Button btn, EmployeeDTO employeeDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;
            textEmployeeID.setText(employeeDTO.getEmpId());
            textEmployeeName.setText(employeeDTO.getName());
            textEmployeeAddress.setText(employeeDTO.getAddress());
            textEmployeeNumber.setText(employeeDTO.getTelNum());
            textEmployeeDesignation.setText(employeeDTO.getDesignation());
        });
    }

    private void setDeleteBtnAction(Button btn, EmployeeDTO employeeDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                String id = employeeDTO.getEmpId();

                try {
                    boolean isDelete = employeeModel.deleteEmployee(id);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = EmployeeTbl.getSelectionModel().getFocusedIndex();

                observableList.remove(focusedIndex);
                EmployeeTbl.refresh();
            }
        });
    }

    private void setSelectBtnAction(Button btn, EmployeeDTO employeeDTO) {
        btn.setOnAction((e) -> {

            if(appointmentFormController!=null){
                appointmentFormController.getEmployeeData(employeeDTO);
                stage.hide();
            }


//            List<EmployeeDTO> dtoList = null;
//            try {
//                dtoList = employeeModel.getAllEmployeeIDAndName();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//            mapAppointmentEmployeeTableVal(dtoList);
        });
    }



    private void loadAllEmployees() {
        try {
            List<EmployeeDTO> dtoList = employeeModel.getAllEmployee();
            mapEmployeeTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapEmployeeTableVal(List<EmployeeDTO> dtoList) {
        observableList.clear();

        for (EmployeeDTO employeeDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            if (appointmentFormController == null) {
                hbox.getChildren().addAll(createViewButton(employeeDTO), createUpdateButton(employeeDTO), createDeleteButton(employeeDTO));
            } else {
                hbox.getChildren().addAll(createViewButton(employeeDTO), createUpdateButton(employeeDTO), createDeleteButton(employeeDTO), createSelectButton(employeeDTO));
            }

            observableList.add(
                    new EmployeeTM(
                            employeeDTO.getEmpId(),
                            employeeDTO.getName(),
                            employeeDTO.getAddress(),
                            employeeDTO.getTelNum(),
                            employeeDTO.getDesignation(),
                            hbox
                    )
            );
        }
        EmployeeTbl.setItems(observableList);
    }

    private JFXButton createViewButton(EmployeeDTO employeeDTO) {
        JFXButton btn = new JFXButton();
        setViewBtnAction(btn, employeeDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createUpdateButton(EmployeeDTO employeeDTO) {
        JFXButton btn = new JFXButton();
        setUpdateBtnAction(btn, employeeDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createDeleteButton(EmployeeDTO employeeDTO) {
        JFXButton btn = new JFXButton();
        setDeleteBtnAction(btn, employeeDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createSelectButton(EmployeeDTO employeeDTO) {
        JFXButton btn = new JFXButton();
        setSelectBtnAction(btn, employeeDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-select-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private void employeeclearFields() {
        textEmployeeID.setText("");
        textEmployeeName.setText("");
        textEmployeeAddress.setText("");
        textEmployeeNumber.setText("");
        textEmployeeDesignation.setText("");

        employeeAddBtn.setDisable(true);
        employeeUpdateBtn.setDisable(true);
        isUpdate = false;

        generateNextEmployeeId();
        loadAllEmployees();
    }

    @FXML
    void btnEmployeeClearOnAction(ActionEvent event) {
        employeeclearFields();
    }

    @FXML
    void dynamicSearchAction(KeyEvent event) {
        if (!txtdynamicSearch.getText().trim().isEmpty()) {
            try {
                List<EmployeeDTO> dtoList = employeeModel.getAllEmployeeBySearch(txtdynamicSearch.getText());
                mapEmployeeTableVal(dtoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadAllEmployees();
        }
    }

    @FXML
    void validateEmpForm(KeyEvent keyEvent) {

        final boolean name = StringUtils.isNullOrEmpty(textEmployeeName.getText()) ? false
                : validationLogic(textEmployeeName, "([A-Z a-z])+", keyEvent, textEmployeeAddress);

        final boolean address = StringUtils.isNullOrEmpty(textEmployeeAddress.getText()) ?
                false : validationLogic(textEmployeeAddress, "[A-Za-z0-9 ,]+", keyEvent, textEmployeeNumber);

        final boolean number = StringUtils.isNullOrEmpty(textEmployeeNumber.getText()) ?
                false : validationLogic(textEmployeeNumber, "[0]\\d{9}", keyEvent, textEmployeeDesignation);

        final boolean designation = StringUtils.isNullOrEmpty(textEmployeeDesignation.getText()) ?
                false : validationLogic(textEmployeeDesignation, "[A-Za-z]+", keyEvent, null);


        if (name && address && number && designation) {
            if (isUpdate) {
                employeeUpdateBtn.setDisable(false);
            } else {
                employeeAddBtn.setDisable(false);
            }
        } else {
            if (isUpdate) {
                employeeUpdateBtn.setDisable(true);
            } else {
                employeeAddBtn.setDisable(true);
            }
        }

    }

    public boolean validationLogic(TextField name, String regex, KeyEvent keyEvent, TextField nextName) {
        if (Pattern.matches(regex, name.getText())) {
            name.getParent().setStyle("-fx-border-color: green");
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (nextName != null) {
                    nextName.requestFocus();
                } else {
                    employeeAddBtn.requestFocus();
                }
            }
            return true;
        } else {
            name.getParent().setStyle("-fx-border-color: red");
            return false;
        }
    }

    private void generateNextEmployeeId() {
        try {
            String employeeId = employeeModel.generateNextCustomerId();
            textEmployeeID.setText(employeeId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void setScene(Stage stage, AppointmentFormController appointmentFormController) {
        this.appointmentFormController = appointmentFormController;
        this.stage = stage;
        loadAllEmployees();
    }
}
