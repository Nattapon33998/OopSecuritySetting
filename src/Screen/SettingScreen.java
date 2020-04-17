package Screen;

import Utilities.FileWorker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Setting;

import java.io.File;
import java.io.IOException;

public class SettingScreen {
    private Setting setting = FileWorker.readSettings();
    private Stage stage;
    private Scene scene;
    private GridPane container;

    public SettingScreen() throws IOException {
        stage = new Stage();
        container = new GridPane();
        scene = new Scene(container);
        stage.setScene(scene);
    }

    public Stage getStage() {
        Label isLock = new Label("ป้องกันด้วยรหัสผ่าน");
        CheckBox isLockCheckBox = new CheckBox();
        isLockCheckBox.setSelected(this.setting.isLock());
        GridPane.setConstraints(isLock, 0, 0);
        GridPane.setConstraints(isLockCheckBox, 1, 0);
        Label passwordLabel = new Label("รหัสผ่าน");
        TextField passwordVisibleField = new TextField();
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordLabel, 0,1);
        GridPane.setConstraints(passwordField, 1,1);
        GridPane.setConstraints(passwordVisibleField, 1, 1);
        //passwordVisibleField.setVisible(false);
        passwordField.setDisable(!this.setting.isLock());
        isLockCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    passwordField.setDisable(false);
                } else {
                    passwordField.setDisable(true);
                }
            }
        });
        Label showPassword = new Label("แสดงรหัสผ่าน");
        CheckBox showPasswordCheckBox = new CheckBox();
        passwordVisibleField.textProperty().bindBidirectional(passwordField.textProperty());
        passwordVisibleField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordVisibleField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());

        passwordField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        GridPane.setConstraints(showPassword, 0, 2);
        GridPane.setConstraints(showPasswordCheckBox, 1, 2);
        Button okBtn = new Button("บันทึก");
        okBtn.setOnAction(e->{
            FileWorker.writeSettings(new Setting(!isLockCheckBox.isSelected() ? "" : passwordField.getText(), isLockCheckBox.isSelected()));
            stage.close();
        });
        Button cancelBtn = new Button("ยกเลิก");
        cancelBtn.setOnAction(e->{
            this.stage.close();
        });
        GridPane.setConstraints(okBtn, 0, 3);
        GridPane.setConstraints(cancelBtn, 1, 3);
        container.getChildren().addAll(isLock, isLockCheckBox, passwordLabel, passwordField, passwordVisibleField,
                showPassword, showPasswordCheckBox, okBtn, cancelBtn);
        return this.stage;
    }
}
