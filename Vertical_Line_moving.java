package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.Random;

import static sample.Main.*;

public class Controller {
    static int f=0,k=0;
    @FXML
    Circle c;
    @FXML
    Button b;
    @FXML
    Button b2;
    @FXML
    Line p1;
    @FXML
    Line p2;
    @FXML
    Line p3;
    @FXML
    Line p4;
    @FXML
    Line p5;
    @FXML
    Line p6;
    @FXML
    Line p7;
    @FXML
    Line p8;

    static Timeline t1,t2,tp1,tp2,tp3,tp4,tp5,tp6,tp7,tp8;

    public void drop(ActionEvent e){
        c.setCenterY(c.getCenterY()+1);
    }
    public void rise(ActionEvent e){
        c.setCenterY(c.getCenterY()-1);
//        t3.play();
    }
    public void movel1(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==0){
            p1.setLayoutX(p1.getLayoutX()-1);
        }else{
            p1.setLayoutX(p1.getLayoutX()+1);
        }
    }
    public void movel2(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==0){
            p2.setLayoutX(p2.getLayoutX()-1);
        }else{
            p2.setLayoutX(p2.getLayoutX()+1);
        }
    }
    public void movel3(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==0){
            p3.setLayoutX(p3.getLayoutX()-1);
        }else{
            p3.setLayoutX(p3.getLayoutX()+1);
        }
    }
    public void movel4(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==0){
            p4.setLayoutX(p4.getLayoutX()-1);
        }else{
            p4.setLayoutX(p4.getLayoutX()+1);
        }
    }
    public void mover1(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==1){
            p5.setLayoutX(p5.getLayoutX()-1);
        }else{
            p5.setLayoutX(p5.getLayoutX()+1);
        }
    }
    public void mover2(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==1){
            p6.setLayoutX(p6.getLayoutX()-1);
        }else{
            p6.setLayoutX(p6.getLayoutX()+1);
        }
    }
    public void mover3(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==1){
            p7.setLayoutX(p7.getLayoutX()-1);
        }else{
            p7.setLayoutX(p7.getLayoutX()+1);
        }
    }
    public void mover4(ActionEvent e){
        if(p3.getLayoutX()==9){
            f=1;
        }else if(p3.getLayoutX()==169){
            f=0;
        }
        if(f==1){
            p8.setLayoutX(p8.getLayoutX()-1);
        }else{
            p8.setLayoutX(p8.getLayoutX()+1);
        }
    }

    public void start(){
        t1=new Timeline(new KeyFrame(Duration.millis(20),this::drop));
        t2=new Timeline(new KeyFrame(Duration.millis(10),this::rise));
        tp1=new Timeline(new KeyFrame(Duration.millis(20),this::mover1));
        tp2=new Timeline(new KeyFrame(Duration.millis(20),this::mover2));
        tp3=new Timeline(new KeyFrame(Duration.millis(20),this::mover3));
        tp4=new Timeline(new KeyFrame(Duration.millis(20),this::mover4));
        tp5=new Timeline(new KeyFrame(Duration.millis(20),this::movel1));
        tp6=new Timeline(new KeyFrame(Duration.millis(20),this::movel2));
        tp7=new Timeline(new KeyFrame(Duration.millis(20),this::movel3));
        tp8=new Timeline(new KeyFrame(Duration.millis(20),this::movel4));
        t1.setCycleCount(Timeline.INDEFINITE);
        tp1.setCycleCount(Timeline.INDEFINITE);
        tp2.setCycleCount(Timeline.INDEFINITE);
        tp3.setCycleCount(Timeline.INDEFINITE);
        tp4.setCycleCount(Timeline.INDEFINITE);
        tp5.setCycleCount(Timeline.INDEFINITE);
        tp6.setCycleCount(Timeline.INDEFINITE);
        tp7.setCycleCount(Timeline.INDEFINITE);
        tp8.setCycleCount(Timeline.INDEFINITE);
        t2.setCycleCount(25);
        t1.play();
        tp1.play();
        tp2.play();
        tp3.play();
        tp4.play();
        tp5.play();
        tp6.play();
        tp7.play();
        tp8.play();
        t2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                t1.play();
                t2.playFromStart();
                t2.pause();
            }
        });
    }

    public void gen(){
        t1.pause();
        t2.playFromStart();
    }
}
