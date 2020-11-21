package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class RotatingRectangle extends ObstacleClass{

    private Line line1, line2, line3, line4;
    private Line corner1, corner2, corner3, corner4;
    private Line diag1, diag2;
    private double x, y, length;
    private double degree=0;
    public RotatingRectangle(double x, double y, double length){
        super();
        timeline = new Timeline(new KeyFrame(Duration.millis(18), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.x=x;
        this.y=y;
        this.length=length;
        line1=new Line(x-length, y-length, x+length, y-length);
        diag2=new Line(x-length, y+length, x+length, y-length);
        line2=new Line(x+length, y-length, x+length, y+length);
        diag1=new Line(x-length, y-length, x+length, y+length);
        line3=new Line(x+length, y+length, x-length, y-length);
        line4=new Line(x-length, y+length, x-length, y-length);
        corner1=new Line(x-length,y-length,x-length, y-length);
        corner2=new Line(x-length,y+length,x-length, y+length);
        corner3=new Line(x+length,y-length,x+length, y-length);
        corner4=new Line(x+length,y+length,x+length, x+length);
        corner1.setStrokeWidth(20);
        corner2.setStrokeWidth(20);
        corner3.setStrokeWidth(20);
        corner4.setStrokeWidth(20);
        corner1.setStroke(Paint.valueOf("RED"));
        corner2.setStroke(Paint.valueOf("YELLOW"));
        corner3.setStroke(Paint.valueOf("BLUE"));
        corner4.setStroke(Paint.valueOf("GREEN"));
        line1.setStroke(Paint.valueOf("RED"));
        line2.setStroke(Paint.valueOf("BLUE"));
        line3.setStroke(Paint.valueOf("GREEN"));
        line4.setStroke(Paint.valueOf("YELLOW"));
        line1.setStrokeWidth(20);
        line2.setStrokeWidth(20);
        line3.setStrokeWidth(20);
        line4.setStrokeWidth(20);
    }

    @Override
    public void move_obstacle(ActionEvent event) {
        degree = (degree + 1) % 360;
        double pi=3.1415926535897932;
        double v1=Math.toRadians((degree));
        corner1.setRotate(degree+45);
        corner2.setRotate(degree+45);
        corner3.setRotate(degree+45);
        corner4.setRotate(degree+45);
        double p=length+10;
        diag1.setStartX(x+p*Math.cos(v1));
        diag1.setStartY(y+p*Math.sin(v1));
        diag1.setEndX(x+p*Math.cos(v1+pi));
        diag1.setEndY(y+p*Math.sin(v1+pi));
        diag2.setStartX(x+p*Math.cos(v1-pi/2));
        diag2.setStartY(y+p*Math.sin(v1-pi/2));
        diag2.setEndX(x+p*Math.cos(v1+pi/2));
        diag2.setEndY(y+p*Math.sin(v1+pi/2));
        change_coordintate(diag1.getStartX(), diag1.getStartY(), diag1.getStartX(), diag1.getStartY(), corner1);
        change_coordintate(diag1.getEndX(), diag1.getEndY(), diag1.getEndX(), diag1.getEndY(), corner4);
        change_coordintate(diag2.getStartX(), diag2.getStartY(), diag2.getStartX(), diag2.getStartY(), corner2);
        change_coordintate(diag2.getEndX(), diag2.getEndY(), diag2.getEndX(), diag2.getEndY(), corner3);
        change_coordintate(corner1.getEndX(), corner1.getEndY(), corner3.getEndX(), corner3.getEndY(), line1);
        change_coordintate(corner3.getEndX(), corner3.getEndY(), corner4.getEndX(), corner4.getEndY(), line2);
        change_coordintate(corner4.getEndX(), corner4.getEndY(), corner2.getEndX(), corner2.getEndY(), line3);
        change_coordintate(corner2.getEndX(), corner2.getEndY(), corner1.getEndX(), corner1.getEndY(), line4);
    }

    @Override
    public void detect_collision(PlayerClass player) {

    }

    @Override
    public void remove_obstacle() {

    }

    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(line1, line2, line3, line4, corner1, corner2, corner3, corner4);
    }

    public void start_moving(){
        timeline.play();
    }
    private void change_coordintate(double a, double b, double c, double d, Line n1){
        n1.setStartX(a);
        n1.setStartY(b);
        n1.setEndX(c);
        n1.setEndY(d);
    }

    @Override
    public void moveDown(double a) {
        y=y+a;
    }
    @Override
    public void stopMoving(){
        timeline.pause();
    }
}
