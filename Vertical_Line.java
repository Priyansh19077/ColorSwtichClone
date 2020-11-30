package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

import java.util.Collection;

public class Vertical_Line {
    double y;
    static int f=0;
    transient private static Line v1,v2,v3,v4,v5,v6,v7,v8;
    Timeline tp1,tp2;

    public Vertical_Line(double y){
        this.y=y;
        tp1=new Timeline(new KeyFrame(Duration.millis(20),this::mover));
        tp2=new Timeline(new KeyFrame(Duration.millis(20),this::movel));
        //Assume y=100
        v1=new Line(85,y,85,y+100);
        v2=new Line(110,y+25,110,y+75);
        v3=new Line(185,y+15,185,y+85);
        v4=new Line(210,y+30,210,y+70);
        v5=new Line(285,y,285,y+100);
        v6=new Line(310,y+25,310,y+75);
        v7=new Line(385,y+15,385,y+85);
        v8=new Line(410,y+30,410,y+70);
        v1.setStrokeWidth(18);
        v2.setStrokeWidth(10);
        v3.setStrokeWidth(13);
        v4.setStrokeWidth(7);
        v5.setStrokeWidth(18);
        v6.setStrokeWidth(10);
        v7.setStrokeWidth(13);
        v8.setStrokeWidth(7);
        v1.setStroke(Paint.valueOf("#f50909"));
        v2.setStroke(Paint.valueOf("#f50909"));
        v3.setStroke(Paint.valueOf("#0af51e"));
        v4.setStroke(Paint.valueOf("#0af51e"));
        v5.setStroke(Paint.valueOf("#0a0cf5"));
        v6.setStroke(Paint.valueOf("#0a0cf5"));
        v7.setStroke(Paint.valueOf("#e5ff00"));
        v8.setStroke(Paint.valueOf("#e5ff00"));
    }

    private void movel(ActionEvent event) {
        if(v7.getStartX()==215){
            f=1;
        }else if(v7.getStartX()==385){
            f=0;
        }
        if(f==0){
            v1.setStartX(v1.getStartX()-1);
            v1.setEndX(v1.getEndX()-1);
            v3.setStartX(v3.getStartX()-1);
            v3.setEndX(v3.getEndX()-1);
            v5.setStartX(v5.getStartX()-1);
            v5.setEndX(v5.getEndX()-1);
            v7.setStartX(v7.getStartX()-1);
            v7.setEndX(v7.getEndX()-1);
        }else{
            v1.setStartX(v1.getStartX()+1);
            v1.setEndX(v1.getEndX()+1);
            v3.setStartX(v3.getStartX()+1);
            v3.setEndX(v3.getEndX()+1);
            v5.setStartX(v5.getStartX()+1);
            v5.setEndX(v5.getEndX()+1);
            v7.setStartX(v7.getStartX()+1);
            v7.setEndX(v7.getEndX()+1);
        }
    }

    private void mover(ActionEvent event) {
        if(f==0){
            v2.setStartX(v2.getStartX()+1);
            v2.setEndX(v2.getEndX()+1);
            v4.setStartX(v4.getStartX()+1);
            v4.setEndX(v4.getEndX()+1);
            v6.setStartX(v6.getStartX()+1);
            v6.setEndX(v6.getEndX()+1);
            v8.setStartX(v8.getStartX()+1);
            v8.setEndX(v8.getEndX()+1);
        }else{
            v2.setStartX(v2.getStartX()-1);
            v2.setEndX(v2.getEndX()-1);
            v4.setStartX(v4.getStartX()-1);
            v4.setEndX(v4.getEndX()-1);
            v6.setStartX(v6.getStartX()-1);
            v6.setEndX(v6.getEndX()-1);
            v8.setStartX(v8.getStartX()-1);
            v8.setEndX(v8.getEndX()-1);
        }
    }

    public void move_Obstacle(ActionEvent e){

    }

//    @Override
//    public void detect_collision(PlayerClass player) {
//
//    }

//    @Override
    public double getY(){
        return this.y;
    }

//    @Override
    public void remove_obstacle(Pane pane) {
        pane.getChildren().removeAll(v1,v2,v3,v4,v5,v6,v7,v8);
    }


//    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(v1,v2,v3,v4,v5,v6,v7,v8);
    }

    public void start_moving(){
        tp1.play();
        tp2.play();
    }

//    @Override
    public void moveDown(double a) {
        y=y+a;
    }

//    @Override
    public void stopMoving(){
        tp1.pause();
        tp2.pause();
    }

//    @Override
//    public void initialize(ObstacleClass obs, PlayerClass player){
//
//    }
}