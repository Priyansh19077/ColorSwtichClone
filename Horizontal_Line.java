package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Horizontal_Line {
    double y;
    Line v1,v2,v3,v4,v5,v6,v7,v8;
    Timeline t;

    public Horizontal_Line(double y){
        this.y=y;
        t=new Timeline(new KeyFrame(Duration.millis(8),this::move));
        t.setCycleCount(Timeline.INDEFINITE);
        v1=new Line(8,y,123,y);
        v2=new Line(131,y,246,y);
        v3=new Line(254,y,369,y);
        v4=new Line(377,y,492,y);
        v5=new Line(-492,y,-377,y);
        v6=new Line(-369,y,-254,y);
        v7=new Line(-246,y,-131,y);
        v8=new Line(-123,y,-8,y);
        v1.setStrokeWidth(19);
        v2.setStrokeWidth(19);
        v3.setStrokeWidth(19);
        v4.setStrokeWidth(19);
        v5.setStrokeWidth(19);
        v6.setStrokeWidth(19);
        v7.setStrokeWidth(19);
        v8.setStrokeWidth(19);
        v1.setStroke(Paint.valueOf("ea0303"));
        v2.setStroke(Paint.valueOf("#07e832"));
        v3.setStroke(Paint.valueOf("#3d05e8"));
        v4.setStroke(Paint.valueOf("#bae807"));
        v5.setStroke(Paint.valueOf("#ea0303"));
        v6.setStroke(Paint.valueOf("#07e832"));
        v7.setStroke(Paint.valueOf("#3d05e8"));
        v8.setStroke(Paint.valueOf("#bae807"));
    }

    private void move(ActionEvent event) {
        if(v1.getStartX()>=500){
            v1.setStartX(-492);
            v1.setEndX(-377);
        }
        if(v2.getStartX()>=500){
            v2.setStartX(-492);
            v2.setEndX(-377);
        }
        if(v3.getStartX()>=500){
            v3.setStartX(-492);
            v3.setEndX(-377);
        }
        if(v4.getStartX()>=500){
            v4.setStartX(-492);
            v4.setEndX(-377);
        }
        if(v5.getStartX()>=500){
            v5.setStartX(-492);
            v5.setEndX(-377);
        }
        if(v6.getStartX()>=500){
            v6.setStartX(-492);
            v6.setEndX(-377);
        }
        if(v7.getStartX()>=500){
            v7.setStartX(-492);
            v7.setEndX(-377);
        }
        if(v8.getStartX()>=500){
            v8.setStartX(-492);
            v8.setEndX(-377);
        }
        v1.setStartX(v1.getStartX()+1);
        v1.setEndX(v1.getEndX()+1);
        v2.setStartX(v2.getStartX()+1);
        v2.setEndX(v2.getEndX()+1);
        v3.setStartX(v3.getStartX()+1);
        v3.setEndX(v3.getEndX()+1);
        v4.setStartX(v4.getStartX()+1);
        v4.setEndX(v4.getEndX()+1);
        v5.setStartX(v5.getStartX()+1);
        v5.setEndX(v5.getEndX()+1);
        v6.setStartX(v6.getStartX()+1);
        v6.setEndX(v6.getEndX()+1);
        v7.setStartX(v7.getStartX()+1);
        v7.setEndX(v7.getEndX()+1);
        v8.setStartX(v8.getStartX()+1);
        v8.setEndX(v8.getEndX()+1);
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
        t.play();
    }

    //    @Override
    public void moveDown(double a) {
        y=y+a;
    }

    //    @Override
    public void stopMoving(){
        t.pause();
    }

//    @Override
//    public void initialize(ObstacleClass obs, PlayerClass player){
//
//    }
}
