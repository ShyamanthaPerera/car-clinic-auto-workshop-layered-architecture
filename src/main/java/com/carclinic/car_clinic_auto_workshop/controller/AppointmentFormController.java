package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.*;
import com.carclinic.car_clinic_auto_workshop.dto.tm.AppointmentTM;
import com.carclinic.car_clinic_auto_workshop.dto.tm.EmployeeTM;
import com.carclinic.car_clinic_auto_workshop.dto.tm.ItemTM;
import com.carclinic.car_clinic_auto_workshop.model.AppointmentModel;
import com.carclinic.car_clinic_auto_workshop.model.OrderModel;
import com.jfoenix.controls.*;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentFormController {

    public AppointmentDTO appointmentDTO = null;
    public JFXTextArea textNoteCol;
    public TextField textEmployeeID;
    public TextField textEmployeeName;
    public TableView employeeTable;
    public TableColumn employeeIdCol;
    public TableColumn EmployeeNameCol;
    public TextField textSlotNo;
    public TextField textCustomerName;
    public TextField textCustomerID;
    @FXML
    public Label lblItemCode;
    @FXML
    public Label lblModel;
    public Label lblStatus;
    public TableColumn slotIdCol;
    public TableColumn cusIdCol;
    public TableColumn cusNameCol;
    public TableColumn noteCol;
    public TableColumn statusCol;
    public TableColumn actionColll;
    public TableView appointmentTable;

    public Label lblOrderId;
    ObservableList<EmployeeTM> observableEmployeeList = FXCollections.observableArrayList();
    ItemTM seletedItemTemp;
    ObservableList<AppointmentTM> observableList = FXCollections.observableArrayList();
    boolean isUpdate;
    AppointmentModel appointmentModel = new AppointmentModel();
    private ObservableList<ItemTM> obList = FXCollections.observableArrayList();
    @FXML
    private JFXButton appointmentUpdateBtn;
    @FXML
    private JFXButton appointmentAddBtn;
    @FXML
    private TextField textAppointmentID;
    @FXML
    private TextField textVehicleID;
    @FXML
    private TextField textVehicleModel;
    @FXML
    private JFXDatePicker textDate;
    @FXML
    private JFXTimePicker textTime;
    @FXML
    private TextArea textIssue;
    @FXML
    private JFXButton slot01;
    @FXML
    private JFXButton slot02;
    @FXML
    private JFXButton slot03;
    @FXML
    private JFXButton slot04;
    @FXML
    private JFXButton slot05;
    @FXML
    private JFXButton slot06;
    @FXML
    private JFXButton slot07;
    @FXML
    private JFXButton slot08;
    @FXML
    private JFXButton slot09;
    @FXML
    private JFXButton slot10;
    //------------------------------------------
    @FXML
    private TextField textQty;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private TableView<ItemTM> cartTable;
    @FXML
    private TableColumn<?, ?> itemIdCol;
    @FXML
    private TableColumn<?, ?> modelCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<?, ?> qtyCol;
    @FXML
    private TableColumn<?, ?> unitPriceCol;
    @FXML
    private TableColumn<?, ?> totalPriceCol;
    @FXML
    private TableColumn<?, ?> actionColl;
    @FXML
    private Label lblTotalPrice;
    //----------------------------------------------------
    @FXML
    private TableColumn<?, ?> appIdCol;
    @FXML
    private TableColumn<?, ?> vclIdCol;
    @FXML
    private TableColumn<?, ?> vclModelCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> issueCol;
    @FXML
    private TableColumn<?, ?> actionCol;
    @FXML
    private JFXTextField txtdynamicSearch;
    @FXML
    private Tab apimentTab;
    @FXML
    private Tab employeeTab;
    @FXML
    private Tab itemTab;

    public void initialize() {
        setCellValueFactory();
        appointmentDTO = new AppointmentDTO();
        setCellValueFactoryForItem();
        generateNextAppointmentId();
        generateNextOrderId();
        loadAllAppointments();
        setAppointmentCellValueFactory();
    }

    public void getData(VehicleDTO vehicleDTO) {
        textVehicleID.setText(vehicleDTO.getVclId());
        textVehicleModel.setText(vehicleDTO.getModel());
        textCustomerID.setText(vehicleDTO.getCusId());
        textCustomerName.setText(vehicleDTO.getCusName());
    }

    public void getSlotData(SlotDTO slotDTO) {
        textSlotNo.setText(slotDTO.getSlotId());
    }

    public void getEmployeeData(EmployeeDTO employeeDTO) {
        if (appointmentDTO.getEmployeeDTOList() == null || appointmentDTO.getEmployeeDTOList().isEmpty()) {
            appointmentDTO.setEmployeeDTOList(new ArrayList<>());
        }
        appointmentDTO.getEmployeeDTOList().add(employeeDTO);
        mapEmployeeTableVal(appointmentDTO.getEmployeeDTOList());
    }

    public void getItemData(ItemDTO itemDTO) {
        lblItemCode.setText(itemDTO.getItemId());
        lblModel.setText(itemDTO.getModel());
        lblDescription.setText(itemDTO.getDescription());
        lblUnitPrice.setText(Double.toString(itemDTO.getUnitPrice()));
        lblQtyOnHand.setText(Integer.toString(itemDTO.getQtyOnHand()));
        seletedItemTemp = new ItemTM(itemDTO.getItemId(), itemDTO.getModel(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(), null);
    }

    private void mapEmployeeTableVal(List<EmployeeDTO> dtoList) {
        observableEmployeeList.clear();

        for (EmployeeDTO employeeDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            hbox.getChildren().add(createDeleteButton(employeeDTO));

            observableEmployeeList.add(
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
        employeeTable.setItems(observableEmployeeList);
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

    private void setDeleteBtnAction(Button btn, EmployeeDTO employeeDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                appointmentDTO.getEmployeeDTOList().remove(employeeDTO);
                mapEmployeeTableVal(appointmentDTO.getEmployeeDTOList());

            }
        });
    }

    @FXML
    void btnAddVehicleOnActionInAppointment(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/VehicleForm.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            VehicleFormController controller = loader.getController();
            controller.setScene(stage, this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAppointmentClearOnAction(ActionEvent event) {

    }

    private void setCellValueFactory() {
        employeeIdCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        EmployeeNameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void btnAddEmployeeOnActionInAppointment(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/EmployeeForm.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            EmployeeFormController controller = loader.getController();
            controller.setScene(stage, this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddSlotOnActionInAppointment(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/SlotForm.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            SlotFormController controller = loader.getController();
            controller.setScene(stage, this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddItemOnActionInAppointment(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/carclinic/car_clinic_auto_workshop/view/ItemForm.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            ItemFormController controller = loader.getController();
            controller.setScene(stage, this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void mapItemsTableVal() {

        if (appointmentDTO.getItemDTOList() != null) {

            obList.clear();

            double totalPrice = 0;

            for (ItemTM itemDTO : appointmentDTO.getItemDTOList()) {
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.getChildren().add(createDeleteItemitemDTO(itemDTO));

                totalPrice = totalPrice + itemDTO.getUnit_price() * itemDTO.getQty_on_hand();
                obList.add(
                        new ItemTM(
                                itemDTO.getItem_id(),
                                itemDTO.getModel(),
                                itemDTO.getDescription(),
                                itemDTO.getUnit_price(),
                                itemDTO.getQty_on_hand(),
                                itemDTO.getUnit_price() * itemDTO.getQty_on_hand(),
                                hbox
                        )
                );
            }
            lblTotalPrice.setText(String.valueOf(totalPrice));
            cartTable.setItems(obList);
        }

    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        if (textQty.getText() == null) {
            new Alert(Alert.AlertType.WARNING, "Please input QTY").show();
        }

        if (Integer.parseInt(textQty.getText()) <= Integer.parseInt(lblQtyOnHand.getText())) {

            seletedItemTemp.setQty_on_hand(Integer.parseInt(textQty.getText()));

            if (appointmentDTO.getItemDTOList() == null || appointmentDTO.getItemDTOList().isEmpty()) {
                appointmentDTO.setItemDTOList(new ArrayList<>());
            }

            final Optional<ItemTM> first = appointmentDTO.getItemDTOList().stream().filter(itemTM -> itemTM.getItem_id().equals(seletedItemTemp.getItem_id())).findFirst();

            if (first.isPresent()) {
                appointmentDTO.getItemDTOList().forEach(itemTM -> {
                    if (itemTM.getItem_id().equals(seletedItemTemp.getItem_id())) {
                        itemTM.setQty_on_hand(itemTM.getQty_on_hand() + seletedItemTemp.getQty_on_hand());
                        itemTM.setTotalPrice(itemTM.getUnit_price() * itemTM.getQty_on_hand());
                    }
                });
            } else {
                appointmentDTO.getItemDTOList().add(seletedItemTemp);
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Qty Invalid").show();
        }
        mapItemsTableVal();

        cleanMethod();
    }


    private JFXButton createDeleteItemitemDTO(ItemTM itemDTO) {
        JFXButton btn = new JFXButton();
        setDeleteItemBtnAction(btn, itemDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private void setDeleteItemBtnAction(Button btn, ItemTM itemDTO) {
        btn.setOnAction((e) -> {
            appointmentDTO.getItemDTOList().remove(itemDTO);
            mapItemsTableVal();
        });
    }

    public void cleanMethod() {
        lblItemCode.setText("XXXX");
        lblModel.setText("XXXX");
        lblDescription.setText("XXXX");
        lblUnitPrice.setText("XXXX");
        lblTotalPrice.setText("XXXX");
        lblQtyOnHand.setText("XXXX");
        textQty.setText("");
    }


    private void calculateTotal() {
//        double total = 0;
//        for (int i = 0; i < cartTable.getItems().size(); i++) {
//            total += (double) totalPriceCol.getCellData(i);
//        }
//        lblTotalPrice.setText(String.valueOf(total));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    private void setCellValueFactoryForItem() {

        itemIdCol.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty_on_hand"));
        actionColl.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextAppointmentId() {
        try {
            String appointmentId = appointmentModel.generateNextAppointmentId();
            textAppointmentID.setText(appointmentId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    //----------------------------------------------------------------------------------------------
    @FXML
    void btnAddAppointmentOnAction(ActionEvent event) {

        if (textAppointmentID.getText().trim() .isEmpty() ||
                textSlotNo.getText().trim().isEmpty() ||
                textVehicleID.getText().trim().isEmpty() ||
                textVehicleModel.getText().trim().isEmpty() ||
                textCustomerID.getText().trim().isEmpty() ||
                textCustomerName.getText().trim().isEmpty() ||
                textSlotNo.getText().trim().isEmpty()||
                textDate.getValue() == null ||
                textTime.getValue() == null
        ) {
            new Alert(Alert.AlertType.WARNING, "Please fill all values").show();
        } else {
            appointmentDTO.setAppointmentId(textAppointmentID.getText());
            appointmentDTO.setSlotId(textSlotNo.getText());
            appointmentDTO.setVehicleId(textVehicleID.getText());
            appointmentDTO.setVehicleModel(textVehicleModel.getText());
            appointmentDTO.setCustomerId(textCustomerID.getText());
            appointmentDTO.setCustomerName(textCustomerName.getText());
            appointmentDTO.setDate(String.valueOf(textDate.getValue()));
            appointmentDTO.setTime(String.valueOf(textTime.getValue()));
            appointmentDTO.setIssue(textIssue.getText());
            appointmentDTO.setStatus(lblStatus.getText());

            if (appointmentDTO.getItemDTOList() != null && !appointmentDTO.getItemDTOList().isEmpty()) {
                appointmentDTO.setOrderDTO(new OrderDTO(lblOrderId.getText(), textAppointmentID.getText(), lblTotalPrice.getText(), textDate.getValue().toString(), textTime.getValue().toString()));
            }

            boolean isSaved = false;
            try {
                isSaved = appointmentModel.save(appointmentDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (isSaved) {
                loadAllAppointments();
                new Alert(Alert.AlertType.INFORMATION, "Appointment Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Appointment NOT Saved").show();
            }
        }
    }

    @FXML
    void btnUpdateAppointmentOnAction(ActionEvent event) {

//        String appId = textAppointmentID.getText();
//        String slotId = textSlotNo.getText();
//        String vclId = textVehicleID.getText();
//        String vclModel = textVehicleModel.getText();
//        String cusId = textCustomerID.getText();
//        String cusName = textCustomerName.getText();
//        String date = String.valueOf(textDate.getValue());
//        String time = String.valueOf(textTime.getValue());
//        String note = textIssue.getText();
//
//        AppointmentDTO dto = new AppointmentDTO(appId, slotId, vclId, vclModel, cusId, cusName, date, time, note);
//
//        try {
//            boolean isUpdate = appointmentModel.updateAppointment(dto);
//
//            if (isUpdate) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Appointment Updated").show();
////                customerclearFields();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
    }

    private void loadAllAppointments() {
        try {
            List<AppointmentDTO> dtoList = appointmentModel.getAllAppointment();
            mapAppointmentTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapAppointmentTableVal(List<AppointmentDTO> dtoList) {
        observableList.clear();

        for (AppointmentDTO appointmentDTO1 : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(createAppointmentViewButton(appointmentDTO1), createAppointmentUpdateButton(appointmentDTO1), createAppointmentDeleteButton(appointmentDTO1));

            observableList.add(
                    new AppointmentTM(
                            appointmentDTO1.getAppointmentId(),
                            appointmentDTO1.getSlotId(),
                            appointmentDTO1.getVehicleId(),
                            appointmentDTO1.getVehicleModel(),
                            appointmentDTO1.getCustomerId(),
                            appointmentDTO1.getCustomerName(),
                            appointmentDTO1.getDate(),
                            appointmentDTO1.getTime(),
                            appointmentDTO1.getIssue(),
                            appointmentDTO1.getStatus(),
                            hbox
                    )
            );
        }
        appointmentTable.setItems(observableList);
    }

    private JFXButton createAppointmentViewButton(AppointmentDTO appointmentDTO) {
        JFXButton btn = new JFXButton();
        setAppointmentViewBtnAction(btn, appointmentDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createAppointmentUpdateButton(AppointmentDTO appointmentDTO) {
        JFXButton btn = new JFXButton();
        setAppointmentUpdateBtnAction(btn, appointmentDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createAppointmentDeleteButton(AppointmentDTO appointmentDTO) {
        JFXButton btn = new JFXButton();
        setAppointmentDeleteBtnAction(btn, appointmentDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private void setAppointmentViewBtnAction(Button btn, AppointmentDTO appointmentDTO) {
        btn.setOnAction((e) -> {

        });
    }

    private void setAppointmentUpdateBtnAction(Button btn, AppointmentDTO appointmentDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;

            textAppointmentID.setText(appointmentDTO.getAppointmentId());
            textSlotNo.setText(appointmentDTO.getSlotId());
            textVehicleID.setText(appointmentDTO.getVehicleId());
            textVehicleModel.setText(appointmentDTO.getVehicleModel());
            textCustomerID.setText(appointmentDTO.getCustomerId());
            textCustomerName.setText(appointmentDTO.getCustomerName());
            textDate.setValue(LocalDate.parse(appointmentDTO.getDate()));
            textTime.setValue(LocalTime.parse(appointmentDTO.getTime()));
            textIssue.setText(appointmentDTO.getIssue());
        });
    }

    private void setAppointmentDeleteBtnAction(Button btn, AppointmentDTO appointmentDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                String id = appointmentDTO.getAppointmentId();

                try {
                    boolean isDelete = appointmentModel.deleteAppointment(id);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Appointment Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = appointmentTable.getSelectionModel().getFocusedIndex();
                observableList.remove(focusedIndex);
                appointmentTable.refresh();
            }
        });
    }

    private void setAppointmentCellValueFactory() {
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        slotIdCol.setCellValueFactory(new PropertyValueFactory<>("slotId"));
        vclIdCol.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        vclModelCol.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        cusIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cusNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        issueCol.setCellValueFactory(new PropertyValueFactory<>("issue"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionColll.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {

        OrderModel orderModel = new OrderModel();

        try {
            String orderId = orderModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
}
