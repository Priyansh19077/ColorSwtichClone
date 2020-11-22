package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class RotatingCircle extends ObstacleClass{
    private double x, y, length;
    private double degree=0;
    private Arc a1,a2, a3, a4;
    public RotatingCircle(double x, double y, double length, PlayerClass player){
        super(player);
        timeline = new Timeline(new KeyFrame(Duration.millis(12), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.x=x;
        this.y=y;
        this.length=length;
        a1 = new Arc(x, y, length/2, length/2, 0, 90);
        a2 = new Arc(x, y, length/2, length/2, 0, 90);
        a3 = new Arc(x, y, length/2, length/2, 0, 90);
        a4 = new Arc(x, y, length/2, length/2, 0, 90);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStroke(Paint.valueOf("RED"));
        a1.setStrokeWidth(20);
        a2.setType(ArcType.OPEN);
        a2.setFill(null);
        a2.setStroke(Paint.valueOf("BLUE"));
        a2.setStrokeWidth(20);
        a3.setType(ArcType.OPEN);
        a3.setFill(null);
        a3.setStroke(Paint.valueOf("GREEN"));
        a3.setStrokeWidth(20);
        a4.setType(ArcType.OPEN);
        a4.setFill(null);
        a4.setStroke(Paint.valueOf("YELLOW"));
        a4.setStrokeWidth(20);
    }

    @Override
    public void move_obstacle(ActionEvent event) {
        degree = (degree + 1) % 360;
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);
    }

    @Override
    public void detect_collision(PlayerClass player) {

    }
    @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void remove_obstacle(Pane pane) {
        pane.getChildren().removeAll(a1, a2, a3, a4);
    }


    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(a1,a2,a3,a4);
    }

    public void start_moving(){
        timeline.play();
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
