package sample;

import java.awt.*;
import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;


public class StarClass implements Serializable, Collidable{
    private PlayerClass player;
    private GameClass game;
    private int points;
    private double x,y;
    transient private Timeline t1;
    transient Pane pane;
    public boolean hidden;
    private boolean colliding;
    int counter=0;
    transient private Image image;
    private double length;
    transient private ImageView image_view;

    public StarClass(double x,double y, int points, PlayerClass player, Pane pane, double length){
        this.x=x;
        this.y=y;
        this.hidden=false;
        this.length=length;
        this.game=player.getGame();
        this.points=points;
        this.pane=pane;
        this.player=player;
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
        double x1=this.x;
        t1=new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                detect_collision(new ActionEvent());
            }
        }));
        t1.setCycleCount(-1);
    }
    @Override
    public void detect_collision(ActionEvent event){
        if(Math.abs(player.getBall().getCenterY()-y)<=70 && Math.abs((x+40)-250)<=5){
            player.getGame().stars_remaining++;
            hidden=true;
            removeStar(pane);
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    File file=new File("Media/star_sound.wav");
                    AudioInputStream sound=AudioSystem.getAudioInputStream(file);
                    Clip clip=AudioSystem.getClip();
                    clip.open(sound);
                    clip.start();
                    return null;
                }
            };
            Thread thread = new Thread(task);
            thread.start();
            t1.stop();
        }
        image_view.setRotate((image_view.getRotate()-1)%360);
        if(game.getLevel()>=3) {
            if (counter == 0) {
                x += 1.5;
            } else {
                x -= 1.5;
            }
            image_view.setLayoutX(x);
            if (x >= 500)
                counter = 1;
            if (x <= -100)
                counter = 0;
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
                detect_collision(new ActionEvent());
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