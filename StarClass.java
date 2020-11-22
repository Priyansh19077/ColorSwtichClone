package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StarClass {
    private PlayerClass player;
    private int points;
    private double x,y;
    private boolean colliding;
    private Timeline t;
    private Image image;
    private ImageView image_view;

    public StarClass(double x,double y, int points, PlayerClass player){
        this.x=x;
        this.y=y;
        this.points=points;
        try {
            image = new Image(new FileInputStream("Media/Star.png"));
            image_view = new ImageView(image);
            image_view.setLayoutX(x);
            image_view.setLayoutY(y);
            image_view.setFitHeight(70);
            image_view.setPreserveRatio(true);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addScore(){

    }

    public void removeStar(Pane pane){
        pane.getChildren().remove(this.image_view);
    }

    public boolean isColliding() {
        return colliding;
    }
    public void addStar(Pane pane){
        pane.getChildren().add(this.image_view);
    }
}