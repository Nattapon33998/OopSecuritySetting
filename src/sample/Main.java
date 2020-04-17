// Java Program to create a canvas with specified
// width and height(as arguments of constructor),
// add it to the stage and also add a circle and
// rectangle on it
package sample;

import Screen.*;
import Utilities.FileWorker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private double user_x = 0.5f, user_y = 0.f;
    private double MAP_HEIGHT = 750.f, MAP_WIDTH = 1125.f;
    private double RATIO = 50.0f;
    private ArrayList<Location> locs = FileWorker.readFileToLocations();

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        SettingScreen settingScreen = new SettingScreen();
        Label failLoginLabel = new Label("Faild login");
        failLoginLabel.setOpacity(0.0);
        VBox container = new VBox();
        PasswordField passwordField = new PasswordField();
        Button loginBtn = new Button("login");

        container.getChildren().addAll(passwordField, loginBtn);
        container.getChildren().add(failLoginLabel);
        loginBtn.setOnAction(e->{
//            System.out.println(passwordField.getText());
//            FileWorker.writeSettings(new Setting(passwordField.getText(), true));
            try {
                Setting setting = FileWorker.readSettings();
                if(passwordField.getText().compareTo(setting.getPassword()) == 0) {
                    stage.close();
                    settingScreen.getStage().show();
                } else {
                    failLoginLabel.setOpacity(1.0);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Scene sc = new Scene(container);
        stage.setScene(sc);
        stage.show();
    }


//    @Override
//    public void start(Stage stage) throws Exception {
//        LocationManagement locManScreen = new LocationManagement();
////        SelectableMapDrawer md = new SelectableMapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y, locs);
//
//
//        VBox mainPane = new VBox();
//        mainPane.setPadding(new Insets(10));
//        mainPane.setAlignment(Pos.TOP_CENTER);
//        mainPane.setSpacing(10);
//
//        ImageView logo = new ImageView(new Image(new FileInputStream("res/img/kmitl_logo.png")));
//        logo.setFitHeight(150);
//        logo.setFitWidth(300);
//
//        // Position Group
//        HBox positionGroup = new HBox();
//        positionGroup.setAlignment(Pos.CENTER);
//        Label currentXY = new Label("พิกัดปัจจุบัน (" + (int) user_x + ", " + (int) user_y + ")");
//        Label editPosition = new Label("แก้ไข");
//        editPosition.setTextFill(Color.BLUE);
//        editPosition.setPadding(new Insets(3));
//        // Changing position screen
//        // TODO: Add handling Error
//        editPosition.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                try {
//                    SelectableMapDrawer smd = new SelectableMapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                Stage editPositionStage = new Stage();
////                VBox editPositionBox = new VBox();
////                editPositionBox.setPadding(new Insets(5));
////                FormField xAxis = new FormField("พิกัดแนวขวาง", 10, true);
////                FormField yAxis = new FormField("พิกัดแนวตั้ง", 10, true);
////                HBox btnContainer = new HBox();
////                Button okBtn = new Button("ตกลง");
////                okBtn.setOnAction(new EventHandler<ActionEvent>() {
////                    @Override
////                    public void handle(ActionEvent actionEvent) {
////                        user_x =  Double.parseDouble(xAxis.getEnteredText());
////                        user_y =  Double.parseDouble(yAxis.getEnteredText());
////                        currentXY.setText("พิกัดปัจจุบัน (" + (int) user_x + ", " + (int) user_y + ")");
////                        editPositionStage.close();
////                    }
////                });
////                Button cancelBtn = new Button("ยกเลิก");
////                cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
////                    @Override
////                    public void handle(ActionEvent actionEvent) {
////                        editPositionStage.close();
////                    }
////                });
////                btnContainer.getChildren().addAll(okBtn, cancelBtn);
////                btnContainer.setAlignment(Pos.CENTER);
////                editPositionBox.getChildren().addAll(xAxis.getNode(), yAxis.getNode(), btnContainer);
////                editPositionStage.setScene(new Scene(editPositionBox));
////                editPositionStage.show();
//            }
//        });
//        editPosition.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                editPosition.setUnderline(true);
//            }
//        });
//        editPosition.setOnMouseExited(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                editPosition.setUnderline(false);
//            }
//        });
//        positionGroup.getChildren().addAll(currentXY, editPosition);
//
//
//        GridPane btnGroup = new GridPane();
//        Button mode1 = new Button("Mode 1");
//        mode1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    AggregationMapDrawer md = new AggregationMapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y);
////                    md.setLocs(FileWorker.readFileToLocations());
//                    md.getMapStage().show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Button mode2 = new Button("Mode 2");
//        mode2.setOnAction(e->{
//            try {
//                KNearestMapDrawer knmd = new KNearestMapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y);
//                knmd.getMapStage().show();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//        Button mode3 = new Button("Mode 3");
//        mode3.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    ApproximateMapDrawer amd = new ApproximateMapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y);
//                    amd.getMapStage().show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Button mode4 = new Button("Mode 4");
//        mode4.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    MinMaxMapDrawer mmmd = new MinMaxMapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y);
//                    mmmd.getMapStage().show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Button manageBtn = new Button("จัดการร้านค้า");
//        manageBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                locManScreen.getStage().show();
//            }
//        });
//        Button exitBtn = new Button("ออก");
//        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Platform.exit();
//                System.exit(0);
//            }
//        });
//        btnGroup.setHgap(10);
//        btnGroup.setVgap(10);
//        btnGroup.setAlignment(Pos.CENTER);
//
//        btnGroup.add(mode1, 0, 0);
//        btnGroup.add(mode2, 1, 0);
//        btnGroup.add(mode3, 0,1);
//        btnGroup.add(mode4, 1, 1);
//        btnGroup.add(manageBtn, 0,2);
//        btnGroup.add(exitBtn, 1, 2);
//
//        mainPane.getChildren().addAll(logo, positionGroup, btnGroup);
//
//        Scene sc = new Scene(mainPane);
//        stage.setScene(sc);
//        stage.show();
//    }

    // Main Method
    public static void main(String args[]) throws IOException {
        // launch the application
        launch(args);
    }
}
