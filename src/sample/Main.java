// Java Program to create a canvas with specified
// width and height(as arguments of constructor),
// add it to the stage and also add a circle and
// rectangle on it
package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private double user_x = 1305.f, user_y = 902.f;
    private double SCREEN_HEIGHT = 500.f, SCREEN_WIDTH = 750.f;
    private double RATIO = 1.5f;

    private ArrayList<Location> locs = FileWorker.readFileToLocations();

    public Main() throws IOException {
    }

    // launch the application
    public void start(Stage stage) {

        // set title for the stage
        stage.setTitle("creating canvas");

        // create a canvas
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

        // graphics context
        GraphicsContext graphics_context =
                canvas.getGraphicsContext2D();

        double relX, relY;
        double maxRelX = 0, maxRelY = 0;

//        for (Location l: this.locs) {
//            user_x += l.getX();
//            user_y += l.getY();
//        }
//
//        user_x /= this.locs.size();
//        user_y /= this.locs.size();

        for (Location l: this.locs) {
            relX = this.relUser(l.getX(), 'x');
            relY = this.relUser(l.getY(), 'y');

            if(Math.abs(relX) > maxRelX) maxRelX = Math.abs(relX);
            if(Math.abs(relY) > maxRelY) maxRelY = Math.abs(relY);
        }


        this.RATIO = maxRelY / SCREEN_HEIGHT > maxRelX / SCREEN_WIDTH ? (2 * maxRelY) / SCREEN_HEIGHT  : (2 * maxRelX) / SCREEN_WIDTH;


        for (Location l: this.locs) {
            relX = this.relUser(l.getX(), 'x');
            relY = this.relUser(l.getY(), 'y');
            graphics_context.setFill(Color.GREEN);
            System.out.println((this.SCREEN_WIDTH / 2) + " + " + (relX / this.RATIO) + ", " + (this.SCREEN_HEIGHT / 2) + "+"  + (relY / this.RATIO));
            graphics_context.fillOval((this.SCREEN_WIDTH / 2) - (relX / this.RATIO), (this.SCREEN_HEIGHT / 2) - (relY / this.RATIO), 10, 10);
            graphics_context.fillText(l.getName() + " (" + l.getX() + ", " + l.getY() + ")", (this.SCREEN_WIDTH / 2) - (relX / this.RATIO), (this.SCREEN_HEIGHT / 2) - (relY / this.RATIO));
        }

        // Draw user
        graphics_context.setFill(Color.BLACK);
        graphics_context.fillOval(this.SCREEN_WIDTH / 2, this.SCREEN_HEIGHT / 2, 10, 10);
        graphics_context.fillText("User (" + user_x + ", " + user_y + ")", this.SCREEN_WIDTH / 2, this.SCREEN_HEIGHT / 2);


        // create a Group
        Group group = new Group(canvas);

        // create a scene
        Scene scene = new Scene(group, SCREEN_WIDTH, SCREEN_HEIGHT);

        // set the scene
        stage.setScene(scene);

        stage.show();
    }

    // Main Method
    public static void _main(String args[]) throws IOException {
        // launch the application
        launch(args);
    }

    public double relUser(double pos, char axis) {
        if(axis == 'x') {
            return user_x - pos;
        } else if(axis == 'y') {
            return user_y - pos;
        }
        return 0.0f;
    }
}
