// Java Program to create a canvas with specified
// width and height(as arguments of constructor),
// add it to the stage and also add a circle and
// rectangle on it
package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private double user_x = 1305.f, user_y = 902.f;
    private double MAP_HEIGHT = 750.f, MAP_WIDTH = 1125.f;
    private double RATIO = 10.0f;
    private ArrayList<Location> locs = FileWorker.readFileToLocations();

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        MapDrawer md = new MapDrawer(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y, locs);
        VBox optionBox = new VBox();
        optionBox.setBackground(new Background(new BackgroundFill(Color.rgb(52, 183, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        Button showMapBtn = new Button("แสดงภาพแผนที่");
        FormField userXForm = new FormField("พิกัดแกนนอน", 10, true);
        userXForm.setFieldText("" + (int) user_x);
        FormField userYForm = new FormField("พิกัดแกนตั้ง", 10, true);
        userYForm.setFieldText("" + (int) user_y);
        showMapBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    md.getMapStage().show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        optionBox.setAlignment(Pos.CENTER);
        optionBox.getChildren().addAll(userXForm.getNode(), userYForm.getNode(), showMapBtn);

        stage.setTitle("โปรเจค");
        stage.setScene(new Scene(optionBox, 200, 400));
        stage.show();
    }

    // Main Method
    public static void main(String args[]) throws IOException {
        // launch the application
        launch(args);
    }
}
