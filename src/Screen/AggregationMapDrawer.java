package Screen;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import sample.Location;
import sample.MapDrawer;
import sample.Aggregation;

import java.util.ArrayList;

public class AggregationMapDrawer extends MapDrawer {
    ArrayList<Location> tspSolved;
    public AggregationMapDrawer(double MAP_HEIGHT, double MAP_WIDTH, double RATIO, double user_x, double user_y, ArrayList<Location> locs) throws Exception {
        super(MAP_HEIGHT, MAP_WIDTH, RATIO, user_x, user_y, locs);
        tspSolved = Aggregation.findShortestTravelingRoute(new Location(getUser_x(), getUser_y(), "user"), this.getLocs());
    }

    @Override
    public Parent getDrawScene() throws Exception {
        Pane mapPane = (Pane) super.getDrawScene();
        for (int i = 0; i < tspSolved.size() - 1; i++) {
            double relXi = this.relUser(tspSolved.get(i).getX(), 'x');
            double relYi = this.relUser(tspSolved.get(i).getY(), 'y');
            double relX2 = this.relUser(tspSolved.get(i + 1).getX(), 'x');
            double relY2 = this.relUser(tspSolved.get(i + 1).getY(), 'y');

            Line line = new Line((getMAP_WIDTH() / 2) - (relXi / getRATIO()), (getMAP_HEIGHT() / 2) - (relYi / getRATIO()),
                    (getMAP_WIDTH() / 2) - (relX2 / getRATIO()), (getMAP_HEIGHT() / 2) - (relY2 / getRATIO()));
            mapPane.getChildren().add(line);
        }
        return mapPane;
    }
}
