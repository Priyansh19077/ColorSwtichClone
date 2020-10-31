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
    public void move1(ActionEvent e){
        if(p1.getLayoutX()>=400){
            p1.setLayoutX(-382.23);
        }
        p1.setLayoutX(p1.getLayoutX()+1);
    }
    public void move2(ActionEvent e){
        if(p2.getLayoutX()>=400){
            p2.setLayoutX(-382.23);
        }
        p2.setLayoutX(p2.getLayoutX()+1);
    }
    public void move3(ActionEvent e){
        if(p3.getLayoutX()>=400){
            p3.setLayoutX(-382.23);
        }
        p3.setLayoutX(p3.getLayoutX()+1);
    }
    public void move4(ActionEvent e){
        if(p4.getLayoutX()>=400){
            p4.setLayoutX(-382.23);
        }
        p4.setLayoutX(p4.getLayoutX()+1);
    }
    public void move5(ActionEvent e){
        if(p5.getLayoutX()>=400){
            p5.setLayoutX(-382.23);
        }
        p5.setLayoutX(p5.getLayoutX()+1);
    }
    public void move6(ActionEvent e){
        if(p6.getLayoutX()>=400){
            p6.setLayoutX(-382.23);
        }
        p6.setLayoutX(p6.getLayoutX()+1);
    }
    public void move7(ActionEvent e){
        if(p7.getLayoutX()>=400){
            p7.setLayoutX(-382.23);
        }
        p7.setLayoutX(p7.getLayoutX()+1);
    }
    public void move8(ActionEvent e){
        if(p8.getLayoutX()>=400){
            p8.setLayoutX(-382.23);
//            p8.setLayoutX(p8.getLayoutX()+1);
        }
        p8.setLayoutX(p8.getLayoutX()+1);
    }

    public void start(){
        t1=new Timeline(new KeyFrame(Duration.millis(20),this::drop));
        t2=new Timeline(new KeyFrame(Duration.millis(10),this::rise));
        tp1=new Timeline(new KeyFrame(Duration.millis(20),this::move1));
        tp2=new Timeline(new KeyFrame(Duration.millis(20),this::move2));
        tp3=new Timeline(new KeyFrame(Duration.millis(20),this::move3));
        tp4=new Timeline(new KeyFrame(Duration.millis(20),this::move4));
        tp5=new Timeline(new KeyFrame(Duration.millis(20),this::move5));
        tp6=new Timeline(new KeyFrame(Duration.millis(20),this::move6));
        tp7=new Timeline(new KeyFrame(Duration.millis(20),this::move7));
        tp8=new Timeline(new KeyFrame(Duration.millis(20),this::move8));
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
