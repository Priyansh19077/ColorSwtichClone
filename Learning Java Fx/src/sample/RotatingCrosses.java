package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.security.Key;
import java.util.ArrayList;

public class RotatingCrosses extends ObstacleClass implements Collidable{
    transient private Line line1, line2, line3, line4;
    transient private Line corner1, corner2, corner3, corner4;
    transient private Line corner5, corner6, corner7, corner8;
    transient private Line diag1, diag2;
    transient private Line diag3, diag4;
    transient private Line line5,line6,line7,line8;
    transient private Circle c2;
    transient private Circle c3;
    transient private ArrayList<Line> lines;
    transient private Timeline collision;
    private double x, y, length;
    private double degree=0;
    public RotatingCrosses(double x, double y, double length, PlayerClass player){
        super(player, player.getGame());
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*13), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        this.x=x;
        this.y=y;
        this.length=length;
        c2=new Circle();
        c2.setCenterY(y);
        c2.setCenterX(x-length+10);
        c2.setRadius(13);
        c2.setFill(Paint.valueOf("BLACK"));
        c3=new Circle();
        c3.setCenterY(y);
        c3.setCenterX(x+length-10);
        c3.setRadius(13);
        c3.setFill(Paint.valueOf("BLACK"));
        line1=new Line(x-length, y-length, x+length, y-length);
        diag1=new Line(x-length, y-length, x+length, y+length);
        diag2=new Line(x-length, y+length, x+length, y-length);
        diag3=new Line(x-length, y+length, x+length, y-length);
        diag4=new Line(x-length, y+length, x+length, y-length);
        line2=new Line(x+length, y-length, x+length, y+length);
        line3=new Line(x+length, y+length, x-length, y+length);
        line4=new Line(x-length, y+length, x-length, y-length);
        line5=new Line(x-length, y+length, x-length, y-length);
        line6=new Line(x-length, y+length, x-length, y-length);
        line7=new Line(x-length, y+length, x-length, y-length);
        line8=new Line(x-length, y+length, x-length, y-length);
        corner1=new Line(x-length,y-length,x-length,y-length);
        corner2=new Line(x-length,y+length,x-length,y+length);
        corner3=new Line(x+length,y-length,x+length,y-length);
        corner4=new Line(x+length,y+length,x+length,y+length);
        corner5=new Line(x+length,y+length,x+length,y+length);
        corner6=new Line(x+length,y+length,x+length,y+length);
        corner7=new Line(x+length,y+length,x+length,y+length);
        corner8=new Line(x+length,y+length,x+length,y+length);
        corner1.setStroke(Paint.valueOf("RED"));
        corner2.setStroke(Paint.valueOf("YELLOW"));
        corner3.setStroke(Paint.valueOf("BLUE"));
        corner4.setStroke(Paint.valueOf("GREEN"));
        corner1.setStrokeWidth(20);
        corner2.setStrokeWidth(20);
        corner3.setStrokeWidth(20);
        corner4.setStrokeWidth(20);
        corner5.setStroke(Paint.valueOf("GREEN"));
        corner6.setStroke(Paint.valueOf("YELLOW"));
        corner7.setStroke(Paint.valueOf("BLUE"));
        corner8.setStroke(Paint.valueOf("RED"));
        corner5.setStrokeWidth(20);
        corner6.setStrokeWidth(20);
        corner7.setStrokeWidth(20);
        corner8.setStrokeWidth(20);
        line1.setStroke(Paint.valueOf("RED"));
        line2.setStroke(Paint.valueOf("BLUE"));
        line3.setStroke(Paint.valueOf("GREEN"));
        line4.setStroke(Paint.valueOf("YELLOW"));
        line5.setStroke(Paint.valueOf("GREEN"));
        line6.setStroke(Paint.valueOf("BLUE"));
        line7.setStroke(Paint.valueOf("RED"));
        line8.setStroke(Paint.valueOf("YELLOW"));
        lines=new ArrayList<Line>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        lines.add(line5);
        lines.add(line6);
        lines.add(line7);
        lines.add(line8);
        for(int i=0;i<8;i++)
            lines.get(i).setStrokeWidth(20);
        move_obstacle(new ActionEvent());
    }

    @Override
    public void move_obstacle(ActionEvent event) {
        degree=(degree+1)%360;
        double pi=3.1415926535897932;
        double v1=Math.toRadians((degree));

        diag1.setStartX(x-length+10+length*Math.cos(v1));
        diag1.setStartY(y+length*Math.sin(v1));
        diag1.setEndX(x-length+10+length*Math.cos(v1+pi));
        diag1.setEndY(y+length*Math.sin(v1+pi));
        diag2.setStartX(x-length+10+length*Math.cos(v1-pi/2));
        diag2.setStartY(y+length*Math.sin(v1-pi/2));
        diag2.setEndX(x-length+10+length*Math.cos(v1+pi/2));
        diag2.setEndY(y+length*Math.sin(v1+pi/2));

        double degree1=(360-degree)%360;
        v1=Math.toRadians(degree1);
        diag3.setStartX(x+length-10+length*Math.cos(v1));
        diag3.setStartY(y+length*Math.sin(v1));
        diag3.setEndX(x+length-10+length*Math.cos(v1+pi));
        diag3.setEndY(y+length*Math.sin(v1+pi));
        diag4.setStartX(x+length-10+length*Math.cos(v1-pi/2));
        diag4.setStartY(y+length*Math.sin(v1-pi/2));
        diag4.setEndX(x+length-10+length*Math.cos(v1+pi/2));
        diag4.setEndY(y+length*Math.sin(v1+pi/2));




        change_coordintate(diag1.getStartX(), diag1.getStartY(), diag1.getStartX(), diag1.getStartY(), corner1);
        change_coordintate(diag1.getEndX(), diag1.getEndY(), diag1.getEndX(), diag1.getEndY(), corner4);
        change_coordintate(diag2.getStartX(), diag2.getStartY(), diag2.getStartX(), diag2.getStartY(), corner2);
        change_coordintate(diag2.getEndX(), diag2.getEndY(), diag2.getEndX(), diag2.getEndY(), corner3);


        change_coordintate(diag3.getStartX(), diag3.getStartY(), diag3.getStartX(), diag3.getStartY(), corner5);
        change_coordintate(diag3.getEndX(), diag3.getEndY(), diag3.getEndX(), diag3.getEndY(), corner8);
        change_coordintate(diag4.getStartX(), diag4.getStartY(), diag4.getStartX(), diag4.getStartY(), corner6);
        change_coordintate(diag4.getEndX(), diag4.getEndY(), diag4.getEndX(), diag4.getEndY(), corner7);


        change_coordintate(corner1.getEndX(), corner1.getEndY(), x-length+10,y, line1);
        change_coordintate(corner3.getEndX(), corner3.getEndY(), x-length+10,y, line2);
        change_coordintate(corner4.getEndX(), corner4.getEndY(), x-length+10,y, line3);
        change_coordintate(corner2.getEndX(), corner2.getEndY(), x-length+10,y, line4);


        change_coordintate(corner5.getEndX(), corner5.getEndY(), x+length-10,y, line5);
        change_coordintate(corner7.getEndX(), corner7.getEndY(), x+length-10,y, line6);
        change_coordintate(corner8.getEndX(), corner8.getEndY(), x+length-10,y, line7);
        change_coordintate(corner6.getEndX(), corner6.getEndY(), x+length-10,y, line8);

    }

    @Override
    public void detect_collision(ActionEvent event) {
        this.check_crossed();
        if(player.getBall().getFill()==Paint.valueOf("WHITE"))
            return;
        Circle ball=player.getBall();
        for(int i=0;i<8;i++){
            Shape intersection=Shape.intersect(ball, lines.get(i));
            if(intersection.getBoundsInLocal().getWidth()!=-1 && lines.get(i).getStroke()!=ball.getFill()){
                System.out.println("Game over!! Collision Detected");
                player.stopMoving();
                timeline.pause();
                collision.pause();
                game.endGame();
                break;
            }
        }
    }

    @Override
    public void remove_obstacle(Pane pane) {
        pane.getChildren().removeAll(line1, line2, line3, line4, line5, line6, line7, line8, c2, c3);
        timeline.stop();
        collision.stop();
    }

    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(line1, line2, line3, line4);
        pane.getChildren().addAll(line5, line6,line7,line8);
        pane.getChildren().addAll(c2, c3);
    }

    public void start_moving(){
        timeline.play();
        collision.play();
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
    public void stopMoving() {
        timeline.pause();
        collision.pause();
    }
    @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void check_crossed() {
        if(player.getBall().getCenterY()<y-length-10){
            if(!left_behind){
                System.out.println("Rotating Crosses crossed");
                left_behind=true;
                game.addObstaclesCrossed();
            }
        }
    }

    public void initialize(ObstacleClass o, PlayerClass player){
        this.player=player;
        this.game=player.getGame();
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*13), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        c2=new Circle();
        c2.setCenterY(y);
        c2.setCenterX(x-length+10);
        c2.setRadius(13);
        c2.setFill(Paint.valueOf("BLACK"));
        c3=new Circle();
        c3.setCenterY(y);
        c3.setCenterX(x+length-10);
        c3.setRadius(13);
        c3.setFill(Paint.valueOf("BLACK"));
        line1=new Line(x-length, y-length, x+length, y-length);
        diag1=new Line(x-length, y-length, x+length, y+length);
        diag2=new Line(x-length, y+length, x+length, y-length);
        diag3=new Line(x-length, y+length, x+length, y-length);
        diag4=new Line(x-length, y+length, x+length, y-length);
        line2=new Line(x+length, y-length, x+length, y+length);
        line3=new Line(x+length, y+length, x-length, y+length);
        line4=new Line(x-length, y+length, x-length, y-length);
        line5=new Line(x-length, y+length, x-length, y-length);
        line6=new Line(x-length, y+length, x-length, y-length);
        line7=new Line(x-length, y+length, x-length, y-length);
        line8=new Line(x-length, y+length, x-length, y-length);
        corner1=new Line(x-length,y-length,x-length,y-length);
        corner2=new Line(x-length,y+length,x-length,y+length);
        corner3=new Line(x+length,y-length,x+length,y-length);
        corner4=new Line(x+length,y+length,x+length,y+length);
        corner5=new Line(x+length,y+length,x+length,y+length);
        corner6=new Line(x+length,y+length,x+length,y+length);
        corner7=new Line(x+length,y+length,x+length,y+length);
        corner8=new Line(x+length,y+length,x+length,y+length);
        corner1.setStroke(Paint.valueOf("RED"));
        corner2.setStroke(Paint.valueOf("YELLOW"));
        corner3.setStroke(Paint.valueOf("BLUE"));
        corner4.setStroke(Paint.valueOf("GREEN"));
        corner1.setStrokeWidth(20);
        corner2.setStrokeWidth(20);
        corner3.setStrokeWidth(20);
        corner4.setStrokeWidth(20);
        corner5.setStroke(Paint.valueOf("GREEN"));
        corner6.setStroke(Paint.valueOf("YELLOW"));
        corner7.setStroke(Paint.valueOf("BLUE"));
        corner8.setStroke(Paint.valueOf("RED"));
        corner5.setStrokeWidth(20);
        corner6.setStrokeWidth(20);
        corner7.setStrokeWidth(20);
        corner8.setStrokeWidth(20);
        line1.setStroke(Paint.valueOf("RED"));
        line2.setStroke(Paint.valueOf("BLUE"));
        line3.setStroke(Paint.valueOf("GREEN"));
        line4.setStroke(Paint.valueOf("YELLOW"));
        line5.setStroke(Paint.valueOf("GREEN"));
        line6.setStroke(Paint.valueOf("BLUE"));
        line7.setStroke(Paint.valueOf("RED"));
        line8.setStroke(Paint.valueOf("YELLOW"));
        lines=new ArrayList<Line>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        lines.add(line5);
        lines.add(line6);
        lines.add(line7);
        lines.add(line8);
        for(int i=0;i<8;i++)
            lines.get(i).setStrokeWidth(20);
        move_obstacle(new ActionEvent());
    }
}
