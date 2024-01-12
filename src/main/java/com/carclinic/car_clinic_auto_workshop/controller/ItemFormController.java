package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.ItemDTO;
import com.carclinic.car_clinic_auto_workshop.view.tdm.ItemTM;
import com.carclinic.car_clinic_auto_workshop.model.ItemModel;
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

public class ItemFormController {

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblId;

    @FXML
    private Label lblModel;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQuantity;

    @FXML
    private JFXButton ItemUpdateBtn;

    @FXML
    private JFXButton itemAddBtn;

    @FXML
    private TextField textItemID;

    @FXML
    private TextField textItemModel;

    @FXML
    private TextField textItemDescription;

    @FXML
    private TextField textUnitPrice;

    @FXML
    private TextField textQuantity;

    @FXML
    private TableView<ItemTM> itemTbl;

    @FXML
    private TableColumn<?, ?> itemIDCol;

    @FXML
    private TableColumn<?, ?> itemModelCol;

    @FXML
    private TableColumn<?, ?> itemDescriptionCol;

    @FXML
    private TableColumn<?, ?> UnitPriceCol;

    @FXML
    private TableColumn<?, ?> quantityCol;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private JFXTextField txtdynamicSearch;

    ItemModel itemModell = new ItemModel();

    ObservableList<ItemTM> observableList = FXCollections.observableArrayList();

    public AppointmentFormController appointmentFormController;

    public Stage stage;

    boolean isUpdate;

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        itemAddBtn.setDisable(true);
        ItemUpdateBtn.setDisable(true);
        textItemID.requestFocus();
        generateNextItemId();
        isUpdate = false;
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

        String itemId = textItemID.getText();
        String itemModel = textItemModel.getText();
        String itemDescription = textItemDescription.getText();
        Double unitPrice = Double.parseDouble(textUnitPrice.getText());
        int quantity = Integer.parseInt(textQuantity.getText());

        ItemDTO dto = new ItemDTO(itemId, itemModel, itemDescription, unitPrice, quantity);

        try {
            boolean isSaved = itemModell.saveItem(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Saved").show();
                ItemclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {

        String itemId = textItemID.getText();
        String itemModel = textItemModel.getText();
        String itemDescription = textItemDescription.getText();
        Double unitPrice = Double.parseDouble(textUnitPrice.getText());
        int quantity = Integer.parseInt(textQuantity.getText());

        ItemDTO itemDto = new ItemDTO(itemId, itemModel, itemDescription, unitPrice, quantity);

        try {
            boolean isUpdate = itemModell.updateItem(itemDto);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
                ItemclearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {

        itemIDCol.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        itemModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        itemDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        UnitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("qty_on_hand"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setViewBtnAction(Button btn, ItemDTO itemDTO) {
        btn.setOnAction((e) -> {

            lblId.setText(itemDTO.getItemId());
            lblDescription.setText(itemDTO.getDescription());
            lblModel.setText(itemDTO.getModel());
            lblUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            lblQuantity.setText(String.valueOf(itemDTO.getQtyOnHand()));
        });
    }

    private void setUpdateBtnAction(Button btn, ItemDTO itemDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;

            textItemID.setText(itemDTO.getItemId());
            textItemDescription.setText(itemDTO.getDescription());
            textItemModel.setText(itemDTO.getModel());
            textUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            textQuantity.setText(String.valueOf(itemDTO.getQtyOnHand()));
        });
    }

    private void setDeleteBtnAction(Button btn, ItemDTO itemDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                String id = itemDTO.getItemId();

                try {
                    boolean isDelete = itemModell.deleteItem(id);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = itemTbl.getSelectionModel().getFocusedIndex();

                observableList.remove(focusedIndex);
                itemTbl.refresh();
            }
        });
    }

    private void setSelectBtnAction(Button btn, ItemDTO itemDTO) {
        btn.setOnAction((e) -> {
            if(appointmentFormController!=null){
                appointmentFormController.getItemData(itemDTO);
                stage.hide();
            }
        });
    }

    private void loadAllItems() {
        try {
            List<ItemDTO> dtoList = itemModell.getAllItem();
            mapItemsTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemClearOnAction(ActionEvent event) {
        ItemclearFields();
    }

    private void ItemclearFields() {

        textItemID.setText("");
        textItemModel.setText("");
        textItemDescription.setText("");
        textUnitPrice.setText("");
        textQuantity.setText("");

        itemAddBtn.setDisable(true);
        ItemUpdateBtn.setDisable(true);
        isUpdate=false;

        generateNextItemId();
        loadAllItems();
    }

    @FXML
    void dynamicSearchAction(KeyEvent event) {

        if (!txtdynamicSearch.getText().trim().isEmpty()) {
            try {
                List<ItemDTO> dtoList = itemModell.getAllItemsBySearch(txtdynamicSearch.getText());
                mapItemsTableVal(dtoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadAllItems();
        }
    }

    private void mapItemsTableVal(List<ItemDTO> dtoList) {

        observableList.clear();

        for (ItemDTO itemDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            if(appointmentFormController == null){
                hbox.getChildren().addAll(createViewButton(itemDTO), createUpdateButton(itemDTO), createDeleteButton(itemDTO));
            }else {
                hbox.getChildren().addAll(createViewButton(itemDTO), createUpdateButton(itemDTO), createDeleteButton(itemDTO),createSelectButton(itemDTO));
            }

            observableList.add(
                    new ItemTM(
                            itemDTO.getItemId(),
                            itemDTO.getModel(),
                            itemDTO.getDescription(),
                            itemDTO.getUnitPrice(),
                            itemDTO.getQtyOnHand(),
                            hbox
                    )
            );
        }
        itemTbl.setItems(observableList);
    }

    private JFXButton createViewButton(ItemDTO itemDTO) {
        JFXButton btn = new JFXButton();
        setViewBtnAction(btn, itemDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createUpdateButton(ItemDTO itemDTO) {
        JFXButton btn = new JFXButton();
        setUpdateBtnAction(btn, itemDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createDeleteButton(ItemDTO itemDTO) {
        JFXButton btn = new JFXButton();
        setDeleteBtnAction(btn, itemDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createSelectButton(ItemDTO itemDTO) {
        JFXButton btn = new JFXButton();
        setSelectBtnAction(btn, itemDTO);
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
    void validateItemForm(KeyEvent keyEvent) {

        final boolean model = StringUtils.isNullOrEmpty(textItemModel.getText()) ? false
                : validationLogic(textItemModel, "[A-Za-z0-9.-_]+", keyEvent, textItemDescription);

        final boolean description = StringUtils.isNullOrEmpty(textItemDescription.getText()) ?
                false : validationLogic(textItemDescription, "[A-Z a-z]+", keyEvent, textUnitPrice);

        final boolean unitPrice = StringUtils.isNullOrEmpty(textUnitPrice.getText()) ?
                false : validationLogic(textUnitPrice, "^\\d+\\.\\d{2}$", keyEvent, textQuantity);

        final boolean quantity = StringUtils.isNullOrEmpty(textQuantity.getText()) ?
                false : validationLogic(textQuantity, "\\d+", keyEvent, null);


        if (model && description && unitPrice && quantity) {
            if (isUpdate) {
                ItemUpdateBtn.setDisable(false);
            }else {
                itemAddBtn.setDisable(false);
            }
        } else {
            if (isUpdate) {
                ItemUpdateBtn.setDisable(true);
            }else {
                itemAddBtn.setDisable(true);
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
                    itemAddBtn.requestFocus();
                }
            }
            return true;
        } else {
            name.getParent().setStyle("-fx-border-color: red");
            return false;
        }
    }

    private void generateNextItemId() {
        try {
            String itemId = itemModell.generateNextItemsId();
            textItemID.setText(itemId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void setScene(Stage stage, AppointmentFormController appointmentFormController) {
        this.appointmentFormController = appointmentFormController;
        this.stage = stage;
        loadAllItems();
    }
}
