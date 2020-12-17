package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.SocketHandler;

public class RotatingCirclesExtra extends ObstacleClass implements Collidable{
    private double x, y, length;
    private int n;
    private double degree=120;
    transient private ArrayList<Arc> arcs;
    transient private Arc a1;
    transient private ArrayList<Color> colors;
    transient private Timeline collision;
    public RotatingCirclesExtra(double x, double y, double length, int n, PlayerClass player){
        super(player, player.getGame());
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*15), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50),this::detect_collision));
        collision.setCycleCount(-1);
        arcs=new ArrayList<Arc>();
        colors=new ArrayList<Color>();
        colors.add((Color)Paint.valueOf("RED"));
        colors.add((Color)Paint.valueOf("GREEN"));
        colors.add((Color)Paint.valueOf("YELLOW"));
        colors.add((Color)Paint.valueOf("BLUE"));
        this.x=x;
        this.y=y;
        this.n=n;
        this.length=length;
        for(int i=0;i<4*n;i++){
            double radius=(length-30*(i/4))/2;
            double curr_degree=(degree+((i*90)%360))%360;
            a1 = new Arc(x, y, radius, radius, 0, 84-(2*(i/4)));
            a1.setStartAngle(curr_degree);
            a1.setType(ArcType.OPEN);
            a1.setFill(null);
            if(i%8<=3) {
                a1.setStroke(colors.get(i % 4));
            }else{
                if(i%2==0) {
                    a1.setStroke(colors.get((i + 1) % 4));
                }else{
                    a1.setStroke(colors.get((i - 1) % 4));
                }
            }
            a1.setStrokeWidth(10);
            arcs.add(a1);
        }
        move_obstacle(new ActionEvent());
    }

    @Override
    public void move_obstacle(ActionEvent event) {
        degree = (degree + 1) % 360;
        if(player.getBall().getFill()==colors.get(0) || player.getBall().getFill()==colors.get(2)){
            Color c1=colors.get(0);
            for(int i=0;i<3;i++){
                colors.set(i, colors.get((i+1)%4));
            }
            colors.set(3, c1);
            for(int i=0;i<4*n;i++){
                double radius=(length-30*(i/4))/2;
                double curr_degree=(degree+((i*90)%360))%360;
                a1 = arcs.get(i);
                a1.setStartAngle(curr_degree);
                a1.setType(ArcType.OPEN);
                a1.setFill(null);
                if(i%8<=3) {
                    a1.setStroke(colors.get(i % 4));
                }else{
                    if(i%2==0) {
                        a1.setStroke(colors.get((i + 1) % 4));
                    }else{
                        a1.setStroke(colors.get((i - 1) % 4));
                    }
                }
                a1.setStrokeWidth(10);
                arcs.set(i, a1);
            }
        }
        for(int i=0;i<4*n;i++){
            double curr_degree1=(degree+((i*90)%360))%360;
            double curr_degree2=(-curr_degree1)%360;
            if(i%8<=3){
                arcs.get(i).setStartAngle(curr_degree1);
            }else{
                arcs.get(i).setStartAngle(curr_degree2);
            }
        }
    }

    @Override
    public void detect_collision(ActionEvent event) {
        this.check_crossed();
        if(player.getBall().getFill()==Paint.valueOf("WHITE"))
            return;
        Circle ball=player.getBall();
        for(int i=0;i<4*n;i++){
            Shape shape= Shape.intersect(ball, arcs.get(i));
            if(shape.getBoundsInLocal().getWidth()!=-1 && arcs.get(i).getStroke()!=ball.getFill()){
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
    public double getY(){
        return this.y;
    }

    @Override
    public void check_crossed() {
        if(player.getBall().getCenterY()<y-length/2){
            if(!left_behind){
                System.out.println("Rotating Circles Extra crossed");
                left_behind=true;
                game.addObstaclesCrossed();
            }
        }
    }

    @Override
    public void remove_obstacle(Pane pane) {
        for(int i=0;i<4*n;i++)
            pane.getChildren().remove(arcs.get(i));
        timeline.stop();
        collision.stop();
    }


    @Override
    public void add_obstacle(Pane pane) {
        for(int i=0;i<4*n;i++)
            pane.getChildren().add(arcs.get(i));
    }

    public void start_moving(){
        timeline.play();
        collision.play();
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
    public void initialize(ObstacleClass obs, PlayerClass player){
        this.player=player;
        this.game=player.getGame();
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*15), this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50),this::detect_collision));
        collision.setCycleCount(-1);
        arcs=new ArrayList<Arc>();
        colors=new ArrayList<Color>();
        colors.add((Color)Paint.valueOf("RED"));
        colors.add((Color)Paint.valueOf("GREEN"));
        colors.add((Color)Paint.valueOf("YELLOW"));
        colors.add((Color)Paint.valueOf("BLUE"));
        for(int i=0;i<4*n;i++){
            double radius=(length-30*(i/4))/2;
            double curr_degree=(degree+((i*90)%360))%360;
            a1 = new Arc(x, y, radius, radius, 0, 84-(2*(i/4)));
            a1.setStartAngle(curr_degree);
            a1.setType(ArcType.OPEN);
            a1.setFill(null);
            if(i%8<=3) {
                a1.setStroke(colors.get(i % 4));
            }else{
                if(i%2==0) {
                    a1.setStroke(colors.get((i + 1) % 4));
                }else{
                    a1.setStroke(colors.get((i - 1) % 4));
                }
            }
            a1.setStrokeWidth(10);
            arcs.add(a1);
        }
        move_obstacle(new ActionEvent());
    }
}
