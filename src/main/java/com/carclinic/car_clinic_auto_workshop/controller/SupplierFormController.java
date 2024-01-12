package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.SupplierDTO;
import com.carclinic.car_clinic_auto_workshop.view.tdm.SupplierTM;
import com.carclinic.car_clinic_auto_workshop.model.SupplierModel;
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

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private Label lblName;

    @FXML
    private Label lblId;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblTel;

    @FXML
    private JFXButton supplierUpdateBtn;

    @FXML
    private JFXButton supplierAddBtn;

    @FXML
    private TextField textSupplierID;

    @FXML
    private TextField textSupplierName;

    @FXML
    private TextField textSupplierAddress;

    @FXML
    private TextField textSupplierNumber;

    @FXML
    private TableView<SupplierTM> SupplierTbl;

    @FXML
    private TableColumn<?, ?> supplierIDCol;

    @FXML
    private TableColumn<?, ?> supplierNameCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private JFXTextField txtdynamicSearch;

    boolean isUpdate;

    SupplierModel supplierModel = new SupplierModel();

    ObservableList<SupplierTM> observableList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
        supplierAddBtn.setDisable(true);
        supplierUpdateBtn.setDisable(true);
        textSupplierID.requestFocus();
        generateNextSupplierId();
        isUpdate = false;
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

        String supId = textSupplierID.getText();
        String supName = textSupplierName.getText();
        String supAddress = textSupplierAddress.getText();
        String supTel = textSupplierNumber.getText();

        SupplierDTO dto = new SupplierDTO(supId, supName, supAddress, supTel);

        try {
            boolean isSaved = supplierModel.saveSupplier(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved").show();
                supplierclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void supplierclearFields() {

        textSupplierID.setText("");
        textSupplierName.setText("");
        textSupplierAddress.setText("");
        textSupplierNumber.setText("");

        supplierAddBtn.setDisable(true);
        supplierUpdateBtn.setDisable(true);
        isUpdate=false;

        generateNextSupplierId();
        loadAllSuppliers();
    }

    @FXML
    void btnSupplierClearOnAction(ActionEvent event) {
        supplierclearFields();
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {

        String supId = textSupplierID.getText();
        String supName = textSupplierName.getText();
        String supAddress = textSupplierAddress.getText();
        String supTel = textSupplierNumber.getText();

        SupplierDTO supplierDTO = new SupplierDTO(supId, supName, supAddress, supTel);

        try {
            boolean isUpdate = supplierModel.updateSupplier(supplierDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
                supplierclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        supplierIDCol.setCellValueFactory(new PropertyValueFactory<>("supId"));
        supplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("telNum"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
    @FXML
    void dynamicSearchAction(KeyEvent event) {
        if (!txtdynamicSearch.getText().trim().isEmpty()) {
            try {
                List<SupplierDTO> dtoList = supplierModel.getAllSupplierBySearch(txtdynamicSearch.getText());
                mapSupplierTableVal(dtoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadAllSuppliers();
        }
    }

    @FXML
    void validateSupForm(KeyEvent keyEvent) {

        final boolean name = StringUtils.isNullOrEmpty(textSupplierName.getText()) ? false
                : validationLogic(textSupplierName, "([A-Z a-z])+", keyEvent, textSupplierAddress);

        final boolean address = StringUtils.isNullOrEmpty(textSupplierAddress.getText()) ?
                false : validationLogic(textSupplierAddress, "[A-Za-z0-9 ,]+", keyEvent, textSupplierNumber);

        final boolean number = StringUtils.isNullOrEmpty(textSupplierNumber.getText()) ?
                false : validationLogic(textSupplierNumber, "[0]\\d{9}", keyEvent, null);


        if (name && address && number) {
            if (isUpdate) {
                supplierUpdateBtn.setDisable(false);
            }else {
                supplierAddBtn.setDisable(false);
            }
        } else {
            if (isUpdate) {
                supplierUpdateBtn.setDisable(true);
            }else {
                supplierAddBtn.setDisable(true);
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
                    supplierAddBtn.requestFocus();
                }
            }
            return true;
        } else {
            name.getParent().setStyle("-fx-border-color: red");
            return false;
        }
    }

    private void generateNextSupplierId() {
        try {
            String supplierId = supplierModel.generateNextSupplierId();
            textSupplierID.setText(supplierId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllSuppliers() {
        try {
            List<SupplierDTO> dtoList = supplierModel.getAllSupplier();
            mapSupplierTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapSupplierTableVal(List<SupplierDTO> dtoList) {
        observableList.clear();

        for (SupplierDTO supplierDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(createViewButton(supplierDTO), createUpdateButton(supplierDTO), createDeleteButton(supplierDTO));

            observableList.add(
                    new SupplierTM(
                            supplierDTO.getSupId(),
                            supplierDTO.getName(),
                            supplierDTO.getAddress(),
                            supplierDTO.getTelNum(),
                            hbox
                    )
            );
        }
        SupplierTbl.setItems(observableList);
    }

    private JFXButton createViewButton(SupplierDTO supplierDTO) {
        JFXButton btn = new JFXButton();
        setViewBtnAction(btn, supplierDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createUpdateButton(SupplierDTO supplierDTO) {
        JFXButton btn = new JFXButton();
        setUpdateBtnAction(btn, supplierDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createDeleteButton(SupplierDTO supplierDTO) {
        JFXButton btn = new JFXButton();
        setDeleteBtnAction(btn, supplierDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private void setViewBtnAction(Button btn, SupplierDTO supplierDTO) {
        btn.setOnAction((e) -> {

            lblName.setText(supplierDTO.getName());
            lblId.setText(supplierDTO.getSupId());
            lblAddress.setText(supplierDTO.getAddress());
            lblTel.setText(supplierDTO.getTelNum());
        });
    }

    private void setUpdateBtnAction(Button btn, SupplierDTO supplierDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;
            textSupplierID.setText(supplierDTO.getSupId());
            textSupplierName.setText(supplierDTO.getName());
            textSupplierAddress.setText(supplierDTO.getAddress());
            textSupplierNumber.setText(supplierDTO.getTelNum());
        });
    }

    private void setDeleteBtnAction(Button btn, SupplierDTO supplierDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                String id = supplierDTO.getSupId();

                try {
                    boolean isDelete = supplierModel.deleteSupplier(id);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = SupplierTbl.getSelectionModel().getFocusedIndex();
                observableList.remove(focusedIndex);
                SupplierTbl.refresh();
            }
        });
    }
}
