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

import java.util.ArrayList;

public class RotatingCirclesExtra extends ObstacleClass{
    private double x, y, length;
    private double degree=120;
    transient private Arc a1,a2, a3, a4;
    transient private Arc a5,a6, a7, a8;
    transient private Arc a9,a10, a11, a12;
    public RotatingCirclesExtra(double x, double y, double length, PlayerClass player){
        super(player);
        timeline = new Timeline(new KeyFrame(Duration.millis(15), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.x=x;
        this.y=y;
        this.length=length;
        a1 = new Arc(x, y, length/2, length/2, 0, 90);
        a2 = new Arc(x, y, length/2, length/2, 0, 90);
        a3 = new Arc(x, y, length/2, length/2, 0, 90);
        a4 = new Arc(x, y, length/2, length/2, 0, 90);
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStroke(Paint.valueOf("RED"));
        a1.setStrokeWidth(10);
        a2.setType(ArcType.OPEN);
        a2.setFill(null);
        a2.setStroke(Paint.valueOf("BLUE"));
        a2.setStrokeWidth(10);
        a3.setType(ArcType.OPEN);
        a3.setFill(null);
        a3.setStroke(Paint.valueOf("GREEN"));
        a3.setStrokeWidth(10);
        a4.setType(ArcType.OPEN);
        a4.setFill(null);
        a4.setStroke(Paint.valueOf("YELLOW"));
        a4.setStrokeWidth(10);

        a5 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a6 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a7 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a8 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a5.setStartAngle(-(degree)%360);
        a6.setStartAngle(-(degree+90)%360);
        a7.setStartAngle(-(degree+180)%360);
        a8.setStartAngle(-(degree+270)%360);
        a5.setType(ArcType.OPEN);
        a5.setFill(null);
        a5.setStroke(Paint.valueOf("YELLOW"));
        a5.setStrokeWidth(10);
        a6.setType(ArcType.OPEN);
        a6.setFill(null);
        a6.setStroke(Paint.valueOf("GREEN"));
        a6.setStrokeWidth(10);
        a7.setType(ArcType.OPEN);
        a7.setFill(null);
        a7.setStroke(Paint.valueOf("BLUE"));
        a7.setStrokeWidth(10);
        a8.setType(ArcType.OPEN);
        a8.setFill(null);
        a8.setStroke(Paint.valueOf("RED"));
        a8.setStrokeWidth(10);

        a9 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);
        a10 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);
        a11 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);
        a12 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);

        a9.setStartAngle(-(degree)%360);
        a10.setStartAngle(-(degree+90)%360);
        a11.setStartAngle(-(degree+180)%360);
        a12.setStartAngle(-(degree+270)%360);
        a9.setType(ArcType.OPEN);
        a9.setFill(null);
        a9.setStroke(Paint.valueOf("YELLOW"));
        a9.setStrokeWidth(10);
        a10.setType(ArcType.OPEN);
        a10.setFill(null);
        a10.setStroke(Paint.valueOf("GREEN"));
        a10.setStrokeWidth(10);
        a11.setType(ArcType.OPEN);
        a11.setFill(null);
        a11.setStroke(Paint.valueOf("BLUE"));
        a11.setStrokeWidth(10);
        a12.setType(ArcType.OPEN);
        a12.setFill(null);
        a12.setStroke(Paint.valueOf("RED"));
        a12.setStrokeWidth(10);
        move_obstacle(new ActionEvent());
    }

    @Override
    public void move_obstacle(ActionEvent event) {
        degree = (degree + 1) % 360;
        if(a1.getStroke()!=player.getBall().getFill()) {
            ArrayList<Arc> one=new ArrayList<Arc>();
            one.add(a2); one.add(a3); one.add(a4);
            for(int i=0;i<3;i++){
                if(one.get(i).getStroke()==player.getBall().getFill()) {
                    one.get(i).setStroke(a1.getStroke());
                    a1.setStroke(player.getBall().getFill());
                }
            }
            one.clear();
            one.add(a5); one.add(a6); one.add(a7);
            for(int i=0;i<3;i++){
                if(one.get(i).getStroke()==player.getBall().getFill()) {
                    one.get(i).setStroke(a8.getStroke());
                    a8.setStroke(player.getBall().getFill());
                }
            }
            one.clear();
            one.add(a10); one.add(a11); one.add(a9);
            for(int i=0;i<3;i++){
                if(one.get(i).getStroke()==player.getBall().getFill()) {
                    one.get(i).setStroke(a12.getStroke());
                    a12.setStroke(player.getBall().getFill());
                }
            }
        }
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);

        a5.setStartAngle(-(degree)%360);
        a6.setStartAngle(-(degree+90)%360);
        a7.setStartAngle(-(degree+180)%360);
        a8.setStartAngle(-(degree+270)%360);


        a9.setStartAngle(-a1.getStartAngle());
        a10.setStartAngle(-a2.getStartAngle());
        a11.setStartAngle(-a3.getStartAngle());
        a12.setStartAngle(-a4.getStartAngle());
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
        pane.getChildren().removeAll(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
    }


    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(a1,a2,a3,a4, a5, a6, a7, a8, a9, a10, a11, a12);
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

    @Override
    public void initialize(ObstacleClass obs, PlayerClass player){
        this.player=player;
        timeline = new Timeline(new KeyFrame(Duration.millis(15), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        a1 = new Arc(x, y, length/2, length/2, 0, 90);
        a2 = new Arc(x, y, length/2, length/2, 0, 90);
        a3 = new Arc(x, y, length/2, length/2, 0, 90);
        a4 = new Arc(x, y, length/2, length/2, 0, 90);
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStroke(Paint.valueOf("RED"));
        a1.setStrokeWidth(10);
        a2.setType(ArcType.OPEN);
        a2.setFill(null);
        a2.setStroke(Paint.valueOf("BLUE"));
        a2.setStrokeWidth(10);
        a3.setType(ArcType.OPEN);
        a3.setFill(null);
        a3.setStroke(Paint.valueOf("GREEN"));
        a3.setStrokeWidth(10);
        a4.setType(ArcType.OPEN);
        a4.setFill(null);
        a4.setStroke(Paint.valueOf("YELLOW"));
        a4.setStrokeWidth(10);

        a5 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a6 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a7 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a8 = new Arc(x, y, (length-30)/2, (length-30)/2, 0, 90);
        a5.setStartAngle(-(degree)%360);
        a6.setStartAngle(-(degree+90)%360);
        a7.setStartAngle(-(degree+180)%360);
        a8.setStartAngle(-(degree+270)%360);
        a5.setType(ArcType.OPEN);
        a5.setFill(null);
        a5.setStroke(Paint.valueOf("YELLOW"));
        a5.setStrokeWidth(10);
        a6.setType(ArcType.OPEN);
        a6.setFill(null);
        a6.setStroke(Paint.valueOf("GREEN"));
        a6.setStrokeWidth(10);
        a7.setType(ArcType.OPEN);
        a7.setFill(null);
        a7.setStroke(Paint.valueOf("BLUE"));
        a7.setStrokeWidth(10);
        a8.setType(ArcType.OPEN);
        a8.setFill(null);
        a8.setStroke(Paint.valueOf("RED"));
        a8.setStrokeWidth(10);

        a9 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);
        a10 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);
        a11 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);
        a12 = new Arc(x, y, (length+30)/2, (length+30)/2, 0, 90);

        a9.setStartAngle(-(degree)%360);
        a10.setStartAngle(-(degree+90)%360);
        a11.setStartAngle(-(degree+180)%360);
        a12.setStartAngle(-(degree+270)%360);
        a9.setType(ArcType.OPEN);
        a9.setFill(null);
        a9.setStroke(Paint.valueOf("YELLOW"));
        a9.setStrokeWidth(10);
        a10.setType(ArcType.OPEN);
        a10.setFill(null);
        a10.setStroke(Paint.valueOf("GREEN"));
        a10.setStrokeWidth(10);
        a11.setType(ArcType.OPEN);
        a11.setFill(null);
        a11.setStroke(Paint.valueOf("BLUE"));
        a11.setStrokeWidth(10);
        a12.setType(ArcType.OPEN);
        a12.setFill(null);
        a12.setStroke(Paint.valueOf("RED"));
        a12.setStrokeWidth(10);
        move_obstacle(new ActionEvent());
    }
}
