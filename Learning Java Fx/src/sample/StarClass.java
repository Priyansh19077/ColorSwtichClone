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
import java.io.Serializable;

public class StarClass implements Serializable{
    private PlayerClass player;
    private int points;
    private double x,y;
    transient private Timeline t1;
    transient Pane pane;
    public boolean hidden;
    private boolean colliding;
    transient private Image image;
    private double length;
    transient private ImageView image_view;

    public StarClass(double x,double y, int points, PlayerClass player, Pane pane, double length){
        this.x=x;
        this.y=y;
        this.hidden=false;
        this.length=length;
        this.points=points;
        this.pane=pane;
        try {
            image = new Image(new FileInputStream("Media/Star.png"));
            image_view = new ImageView(image);
            image_view.setLayoutX(x);
            image_view.setLayoutY(y);
            image_view.setFitHeight(length);
            image_view.setPreserveRatio(true);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        t1=new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Math.abs(player.getBall().getCenterY()-y)<=70){
                    player.addScore(points);
                    player.getGame().stars_remaining++;
                    hidden=true;
                    removeStar(pane);
                    t1.stop();
                }
//                image_view.setLayoutX((image_view.getLayoutX()+1)%620);
                image_view.setRotate((image_view.getRotate()-1)%360);
            }
        }));
        t1.setCycleCount(-1);
//        t1.play();
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
        if(!this.hidden) {
//            System.out.println(1);
            pane.getChildren().add(this.image_view);
        }
    }
    public void initialize(StarClass s1, Pane pane, PlayerClass player){
        this.player=player;
        this.pane=pane;
        try {
            image = new Image(new FileInputStream("Media/Star.png"));
            image_view = new ImageView(image);
            image_view.setLayoutX(x);
            image_view.setLayoutY(y);
            image_view.setFitHeight(length);
            image_view.setPreserveRatio(true);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        t1=new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Math.abs(player.getBall().getCenterY()-y)<=70){
                    player.addScore(points);
                    player.getGame().stars_remaining++;
                    removeStar(pane);
                    hidden=true;
                    t1.stop();
                }
//                image_view.setLayoutX((image_view.getLayoutX()+1)%620);
                image_view.setRotate((image_view.getRotate()-1)%360);
            }
        }));
        t1.setCycleCount(-1);
    }
    public void startMoving(){
        if(!this.hidden)
        {
            t1.setCycleCount(-1);
            t1.playFromStart();
        }
    }
    public void stopMoving(){
        t1.stop();
    }
}