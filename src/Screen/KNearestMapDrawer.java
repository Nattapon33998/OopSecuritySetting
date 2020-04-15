package Screen;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Location;

import java.io.IOException;
import java.util.ArrayList;

public class KNearestMapDrawer extends MapDrawer {
    private double radius = 0.0f;
    private ChoiceBox cb = new ChoiceBox();
    private Stage kSelector = new Stage();

    public KNearestMapDrawer(double MAP_HEIGHT, double MAP_WIDTH, double RATIO, double user_x, double user_y, ArrayList<Location> locs) throws IOException {
        super(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y, locs);
        ArrayList<String> choices = new ArrayList<>();
        for (int i = 0; i < this.getLocs().size(); i++) {
            choices.add("" + i);
        }
        cb.getItems().addAll(choices);
    }

    @Override
    public Parent getDrawScene() throws Exception {
        Button okBtn = new Button("ตกลง");
        VBox container = new VBox();
        container.getChildren().addAll(cb, okBtn);
        kSelector.setAlwaysOnTop(true);
        kSelector.setScene(new Scene(container));
        kSelector.show();
        okBtn.setOnAction(e->{
            if(Integer.parseInt((String) cb.getValue()) > 0) {
/// right here
            }
//            System.out.println(cb.getValue());
        });

        Pane mapPane = (Pane) super.getDrawScene();
        Circle radiusOfInterested = new Circle(radius / this.getRATIO());
        radiusOfInterested.setCenterX(this.getMAP_WIDTH() / 2);
        radiusOfInterested.setCenterY(this.getMAP_HEIGHT() / 2);
        radiusOfInterested.setOpacity(0.3);
        radiusOfInterested.setFill(Color.GREEN);
        mapPane.getChildren().add(radiusOfInterested);

        return mapPane;
    }
}
