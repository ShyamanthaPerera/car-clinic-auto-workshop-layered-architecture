package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.dto.tm.CustomerTM;
import com.carclinic.car_clinic_auto_workshop.model.CustomerModel;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {

    public TableColumn emailCol;
    public Label lblName;

    public Label lblId;
    public Label lblAddress;
    public Label lblEmail;
    public Label lblTel;
    public JFXButton customerAddBtn;
    public JFXButton customerUpdateBtn;
    CustomerModel customerModel = new CustomerModel();
    ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
    boolean isUpdate;
    @FXML
    private JFXTextField txtdynamicSearch;
    @FXML
    private TextField textCustomerID;
    @FXML
    private TextField textCustomerName;
    @FXML
    private TextField textCustomerEmail34;
    @FXML
    private TextField textCustomerAddress;
    @FXML
    private TextField textCustomerNumber;
    @FXML
    private TableView<CustomerTM> CustomerTbl;
    @FXML
    private TableColumn<?, ?> customerIDCol;
    @FXML
    private TableColumn<?, ?> customerNameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> actionCol;
    public Stage stage;

    public VehicleFormController vehicleFormController;

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        customerAddBtn.setDisable(true);
        customerUpdateBtn.setDisable(true);
        textCustomerID.requestFocus();
        generateNextCustomerId();
        isUpdate = false;
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        String cusId = textCustomerID.getText();
        String cusName = textCustomerName.getText();
        String cusAddress = textCustomerAddress.getText();
        String cusEmail = textCustomerEmail34.getText();
        String cusTel = textCustomerNumber.getText();

        CustomerDTO dto = new CustomerDTO(cusId, cusName, cusAddress, cusEmail, cusTel);

        try {
            boolean isSaved = customerModel.saveCustomer(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved").show();
                customerclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {

        String cusId = textCustomerID.getText();
        String cusName = textCustomerName.getText();
        String cusAddress = textCustomerAddress.getText();
        String cusEmail = textCustomerEmail34.getText();
        String cusTel = textCustomerNumber.getText();

        CustomerDTO customerDTO = new CustomerDTO(cusId, cusName, cusAddress, cusEmail, cusTel);

        try {
            boolean isUpdate = customerModel.updateCustomer(customerDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
                customerclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("telNum"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setViewBtnAction(Button btn, CustomerDTO customerDTO) {
        btn.setOnAction((e) -> {

            lblName.setText(customerDTO.getCusName());
            lblId.setText(customerDTO.getCusId());
            lblAddress.setText(customerDTO.getAddress());
            lblEmail.setText(customerDTO.getEmail());
            lblTel.setText(customerDTO.getTelNum());
        });
    }

    private void setUpdateBtnAction(Button btn, CustomerDTO customerDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;
            textCustomerID.setText(customerDTO.getCusId());
            textCustomerName.setText(customerDTO.getCusName());
            textCustomerEmail34.setText(customerDTO.getEmail());
            textCustomerAddress.setText(customerDTO.getAddress());
            textCustomerNumber.setText(customerDTO.getTelNum());
        });
    }

    private void setDeleteBtnAction(Button btn, CustomerDTO customerDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                String id = customerDTO.getCusId();

                try {
                    boolean isDelete = customerModel.deleteCustomer(id);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = CustomerTbl.getSelectionModel().getFocusedIndex();

                observableList.remove(focusedIndex);
                CustomerTbl.refresh();
            }
        });
    }

    private void setSelectBtnAction(Button btn, CustomerDTO customerDTO) {
        btn.setOnAction((e) -> {
            if(vehicleFormController!=null){
            vehicleFormController.getData(customerDTO);
            stage.hide();
            }
        });
    }

    @FXML
    void btnCustomerClearOnAction(ActionEvent event) {
        customerclearFields();
    }

    private void customerclearFields() {
        textCustomerID.setText("");
        textCustomerName.setText("");
        textCustomerAddress.setText("");
        textCustomerEmail34.setText("");
        textCustomerNumber.setText("");

        customerAddBtn.setDisable(true);
        customerUpdateBtn.setDisable(true);
        isUpdate=false;

        generateNextCustomerId();
        loadAllCustomers();
    }

    @FXML
    private void dynamicSearchAction(KeyEvent event) {
        if (!txtdynamicSearch.getText().trim().isEmpty()) {
            try {
                List<CustomerDTO> dtoList = customerModel.getAllCustomerBySearch(txtdynamicSearch.getText());
                mapCustomerTableVal(dtoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadAllCustomers();
        }
    }

    public void loadAllCustomers() {
        try {
            List<CustomerDTO> dtoList = customerModel.getAllCustomer();
            mapCustomerTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapCustomerTableVal(List<CustomerDTO> dtoList) {

        observableList.clear();

        for (CustomerDTO customerDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            if(vehicleFormController==null) {
                hbox.getChildren().addAll(createViewButton(customerDTO), createUpdateButton(customerDTO), createDeleteButton(customerDTO));
            }else{
                hbox.getChildren().addAll(createViewButton(customerDTO), createUpdateButton(customerDTO), createDeleteButton(customerDTO), createSelectButton(customerDTO));
            }

            observableList.add(
                    new CustomerTM(
                            customerDTO.getCusId(),
                            customerDTO.getCusName(),
                            customerDTO.getAddress(),
                            customerDTO.getEmail(),
                            customerDTO.getTelNum(),
                            hbox
                    )
            );
        }
        CustomerTbl.setItems(observableList);
    }

    private JFXButton createViewButton(CustomerDTO customerDTO) {
        JFXButton btn = new JFXButton();
        setViewBtnAction(btn, customerDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createUpdateButton(CustomerDTO customerDTO) {
        JFXButton btn = new JFXButton();
        setUpdateBtnAction(btn, customerDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createDeleteButton(CustomerDTO customerDTO) {
        JFXButton btn = new JFXButton();
        setDeleteBtnAction(btn, customerDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createSelectButton(CustomerDTO customerDTO) {
        JFXButton btn = new JFXButton();
        setSelectBtnAction(btn, customerDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-select-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    @FXML
    public void validateCusForm(KeyEvent keyEvent) {

        final boolean name = StringUtils.isNullOrEmpty(textCustomerName.getText()) ? false
                : validationLogic(textCustomerName, "([A-Z a-z])+", keyEvent, textCustomerEmail34);

        final boolean email = StringUtils.isNullOrEmpty(textCustomerEmail34.getText()) ?
                false : validationLogic(textCustomerEmail34, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", keyEvent, textCustomerAddress);

        final boolean address = StringUtils.isNullOrEmpty(textCustomerAddress.getText()) ?
                false : validationLogic(textCustomerAddress, "[A-Za-z0-9 ,]+", keyEvent, textCustomerNumber);

        final boolean number = StringUtils.isNullOrEmpty(textCustomerNumber.getText()) ?
                false : validationLogic(textCustomerNumber, "[0]\\d{9}", keyEvent, null);


        if (name && email && address && number) {
            if (isUpdate) {
                customerUpdateBtn.setDisable(false);
            }else {
                customerAddBtn.setDisable(false);
            }
        } else {
            if (isUpdate) {
                customerUpdateBtn.setDisable(true);
            }else {
                customerAddBtn.setDisable(true);
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
                    customerAddBtn.requestFocus();
                }
            }
            return true;
        } else {
            name.getParent().setStyle("-fx-border-color: red");
            return false;
        }
    }

    private void generateNextCustomerId() {
        try {
            String customerId = customerModel.generateNextCustomerId();
            textCustomerID.setText(customerId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnPrintCustomerOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        InputStream inputStream = getClass().getResourceAsStream("../reports/CarClinicCustomers.jrxml");
        JasperDesign load = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void setScene(Stage stage, VehicleFormController vehicleFormController) {
        this.vehicleFormController = vehicleFormController;
        this.stage = stage;
        loadAllCustomers();
    }
}
