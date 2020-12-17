package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.security.Key;
import java.util.ArrayList;


public class RotatingRectangle extends ObstacleClass implements Collidable{

    transient private Line line1, line2, line3, line4;
    transient private Line corner1, corner2, corner3, corner4;
    transient private Line diag1, diag2;
    transient private ArrayList<Line> lines;
    transient private ArrayList<Line> corners;
    transient private Timeline collision;
    private double x, y, length;
    private double pi=3.1415926535897932;
    private double degree=0;
    private double p;
    public RotatingRectangle(double x, double y, double length, PlayerClass player){
        super(player, player.getGame());
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*12), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        this.x=x;
        this.y=y;
        this.length=length;
        this.p=length+10;
        lines=new ArrayList<Line>();
        corners=new ArrayList<Line>();
        line1=new Line(x-length, y-length, x+length, y-length);
        line2=new Line(x+length, y-length, x+length, y+length);
        line3=new Line(x+length, y+length, x-length, y-length);
        line4=new Line(x-length, y+length, x-length, y-length);
        line1.setStroke(Paint.valueOf("RED"));
        line2.setStroke(Paint.valueOf("BLUE"));
        line3.setStroke(Paint.valueOf("GREEN"));
        line4.setStroke(Paint.valueOf("YELLOW"));
        diag1=new Line(x-length, y-length, x+length, y+length);
        diag2=new Line(x-length, y+length, x+length, y-length);
        corner1=new Line(x-length,y-length,x-length, y-length);
        corner2=new Line(x-length,y+length,x-length, y+length);
        corner3=new Line(x+length,y-length,x+length, y-length);
        corner4=new Line(x+length,y+length,x+length, x+length);
        corner1.setStroke(Paint.valueOf("RED"));
        corner2.setStroke(Paint.valueOf("YELLOW"));
        corner3.setStroke(Paint.valueOf("BLUE"));
        corner4.setStroke(Paint.valueOf("GREEN"));
        corners.add(corner1);
        corners.add(corner2);
        corners.add(corner3);
        corners.add(corner4);
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        for(int i=0;i<4;i++) {
            corners.get(i).setStrokeWidth(20);
            lines.get(i).setStrokeWidth(20);
            corners.get(i).setRotate(degree+45);
        }
        double v1=Math.toRadians((degree));
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
    public void move_obstacle(ActionEvent event) {
        degree = (degree + 1) % 360;
        double pi=3.1415926535897932;
        double v1=Math.toRadians((degree));
        for(int i=0;i<4;i++)
            corners.get(i).setRotate(degree+45);
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
    public void detect_collision(ActionEvent event) {
        this.check_crossed();
        if(player.getBall().getFill()==Paint.valueOf("WHITE"))
            return;
        Circle ball=player.getBall();
        int number=0;
        for(int i=0;i<4;i++){
            Shape intersection=Shape.intersect(ball, corners.get(i));
            if(intersection.getBoundsInLocal().getWidth()!=-1){
                number++;
            }
            if(corners.get(i).getStroke()!=ball.getFill() && intersection.getBoundsInLocal().getWidth()!=-1){
                System.out.println("Game over !! Collision");
                timeline.pause();
                collision.pause();
                player.stopMoving();
                game.endGame();
                break;
            }
        }
        if(number>0)
            return;
        for(int i=0;i<4;i++){
            Shape intersection=Shape.intersect(ball, lines.get(i));
            if(lines.get(i).getStroke()!=ball.getFill() && intersection.getBoundsInLocal().getWidth()!=-1){
                System.out.println("Game over !! Collision");
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
        pane.getChildren().removeAll(line1, line2, line3, line4, corner1, corner2, corner3, corner4);
        timeline.stop();
        collision.stop();
    }


    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(line1, line2, line3, line4, corner1, corner2, corner3, corner4);
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
    public void stopMoving(){
        timeline.pause();
        collision.pause();
    }
    @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void check_crossed() {
        if(player.getBall().getCenterY()<y-length){
            if(!left_behind){
                System.out.println("Rotating Rectangle crossed");
                left_behind=true;
                game.addObstaclesCrossed();
            }
        }
    }

    @Override
    public void initialize(ObstacleClass obs, PlayerClass player){
        this.player=player;
        this.game=player.getGame();
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*12), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        lines=new ArrayList<Line>();
        corners=new ArrayList<Line>();
        line1=new Line(x-length, y-length, x+length, y-length);
        line2=new Line(x+length, y-length, x+length, y+length);
        line3=new Line(x+length, y+length, x-length, y-length);
        line4=new Line(x-length, y+length, x-length, y-length);
        line1.setStroke(Paint.valueOf("RED"));
        line2.setStroke(Paint.valueOf("BLUE"));
        line3.setStroke(Paint.valueOf("GREEN"));
        line4.setStroke(Paint.valueOf("YELLOW"));
        diag1=new Line(x-length, y-length, x+length, y+length);
        diag2=new Line(x-length, y+length, x+length, y-length);
        corner1=new Line(x-length,y-length,x-length, y-length);
        corner2=new Line(x-length,y+length,x-length, y+length);
        corner3=new Line(x+length,y-length,x+length, y-length);
        corner4=new Line(x+length,y+length,x+length, x+length);
        corner1.setStroke(Paint.valueOf("RED"));
        corner2.setStroke(Paint.valueOf("YELLOW"));
        corner3.setStroke(Paint.valueOf("BLUE"));
        corner4.setStroke(Paint.valueOf("GREEN"));
        corners.add(corner1);
        corners.add(corner2);
        corners.add(corner3);
        corners.add(corner4);
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        for(int i=0;i<4;i++) {
            corners.get(i).setStrokeWidth(20);
            lines.get(i).setStrokeWidth(20);
            corners.get(i).setRotate(degree+45);
        }
        double v1=Math.toRadians((degree));
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
}
