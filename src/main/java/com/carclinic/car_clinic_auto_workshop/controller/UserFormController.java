package com.carclinic.car_clinic_auto_workshop.controller;

import com.carclinic.car_clinic_auto_workshop.dto.UserDTO;
import com.carclinic.car_clinic_auto_workshop.dto.tm.UserTM;
import com.carclinic.car_clinic_auto_workshop.model.UserModel;
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

public class UserFormController {

    @FXML
    private Label lblName;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblPassword;

    @FXML
    private JFXButton userUpdateBtn;

    @FXML
    private JFXButton userAddBtn;

    @FXML
    private TextField textUsername;

    @FXML
    private TextField textPassword;

    @FXML
    private TextField textName;

    @FXML
    private TableView<UserTM> userTbl;

    @FXML
    private TableColumn<?, ?> usernameCol;

    @FXML
    private TableColumn<?, ?> passwordCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private JFXTextField txtdynamicSearch;

    UserModel userModel = new UserModel();

    ObservableList<UserTM> observableList = FXCollections.observableArrayList();

    boolean isUpdate;

    public void initialize() {
        setCellValueFactory();
        loadAllUsers();
        userAddBtn.setDisable(true);
        userUpdateBtn.setDisable(true);
        textUsername.requestFocus();
        isUpdate = false;
    }

    @FXML
    void btnAddUserOnAction(ActionEvent event) {

        String username = textUsername.getText();
        String password = textPassword.getText();
        String name = textName.getText();

        UserDTO dto = new UserDTO(username, password, name);

        try {
            boolean isSaved = userModel.saveUser(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "SignUp Successful").show();
                UserClearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {

        String username = textUsername.getText();
        String password = textPassword.getText();
        String name = textName.getText();

        UserDTO userDTO = new UserDTO(username, password, name);

        try {
            boolean isUpdate = userModel.updateUser(userDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();
                UserClearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setViewBtnAction(Button btn, UserDTO userDTO) {
        btn.setOnAction((e) -> {

            lblName.setText(userDTO.getName());
            lblUsername.setText(userDTO.getUserName());
            lblPassword.setText(userDTO.getPassword());
        });
    }

    private void setUpdateBtnAction(Button btn, UserDTO userDTO) {
        btn.setOnAction((e) -> {
            isUpdate = true;
            textUsername.setText(userDTO.getUserName());
            textPassword.setText(userDTO.getPassword());
            textName.setText(userDTO.getName());
        });
    }

    private void setDeleteBtnAction(Button btn, UserDTO userDTO) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                String name = userDTO.getUserName();

                try {
                    boolean isDelete = userModel.deleteUser(name);

                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "User Deleted").show();
                    }
                } catch (SQLException exception) {
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
                int focusedIndex = userTbl.getSelectionModel().getFocusedIndex();

                observableList.remove(focusedIndex);
                userTbl.refresh();
            }
        });
    }

    private void loadAllUsers() {

        try {
            List<UserDTO> dtoList = userModel.getAllUser();
            mapUserTableVal(dtoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapUserTableVal(List<UserDTO> dtoList) {

        observableList.clear();

        for (UserDTO userDTO : dtoList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(createViewButton(userDTO), createUpdateButton(userDTO), createDeleteButton(userDTO));

            observableList.add(
                    new UserTM(
                            userDTO.getUserName(),
                            userDTO.getPassword(),
                            userDTO.getName(),
                            hbox
                    )
            );
        }
        userTbl.setItems(observableList);
    }

    private JFXButton createViewButton(UserDTO userDTO) {
        JFXButton btn = new JFXButton();
        setViewBtnAction(btn, userDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #a29bfe;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-eye-96.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createUpdateButton(UserDTO userDTO) {
        JFXButton btn = new JFXButton();
        setUpdateBtnAction(btn, userDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #817703;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-update-64.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private JFXButton createDeleteButton(UserDTO userDTO) {
        JFXButton btn = new JFXButton();
        setDeleteBtnAction(btn, userDTO);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: #ff4d4d;");

        Image image = new Image(getClass().getResourceAsStream("/com/carclinic/car_clinic_auto_workshop/view/images/icons8-delete-90.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        btn.setGraphic(imageView);
        return btn;
    }

    private void UserClearFields() {
        textUsername.setText("");
        textPassword.setText("");
        textName.setText("");

        userAddBtn.setDisable(true);
        userUpdateBtn.setDisable(true);
        isUpdate=false;

        loadAllUsers();
    }

    @FXML
    void btnUserClearOnAction(ActionEvent event) {
        UserClearFields();

    }

    @FXML
    void dynamicSearchAction(KeyEvent event) {

        if (!txtdynamicSearch.getText().trim().isEmpty()) {
            try {
                List<UserDTO> dtoList = userModel.getAllCustomerBySearch(txtdynamicSearch.getText());
                mapUserTableVal(dtoList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loadAllUsers();
        }
    }

    @FXML
    void validateUserForm(KeyEvent keyEvent) {


        final boolean username = StringUtils.isNullOrEmpty(textUsername.getText()) ? false
                : validationLogic(textUsername, "([a-z])+", keyEvent, textPassword);

        final boolean password = StringUtils.isNullOrEmpty(textPassword.getText()) ?
                false : validationLogic(textPassword, "^[A-Z][a-z]+[0-9]{1,4}$", keyEvent, textName);

        final boolean name = StringUtils.isNullOrEmpty(textName.getText()) ?
                false : validationLogic(textName, "([A-Z a-z])+", keyEvent, null);


        if (username && password && name) {
            if (isUpdate) {
                userUpdateBtn.setDisable(false);
            }else {
                userAddBtn.setDisable(false);
            }
        } else {
            if (isUpdate) {
                userUpdateBtn.setDisable(true);
            }else {
                userAddBtn.setDisable(true);
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
                    userAddBtn.requestFocus();
                }
            }
            return true;
        } else {
            name.getParent().setStyle("-fx-border-color: red");
            return false;
        }
    }
}
