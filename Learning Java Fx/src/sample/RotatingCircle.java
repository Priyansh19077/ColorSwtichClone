package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;

public class RotatingCircle extends ObstacleClass implements Collidable{
    private double x, y, length;
    private double degree=0;
    transient private Arc a1;
    transient private Timeline collision;
    transient private ArrayList<Arc> arcs;
    transient private ArrayList<Color> colors;
    public RotatingCircle(double x, double y, double length, PlayerClass player){
        super(player, player.getGame());
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*12), this::move_obstacle));
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision.setCycleCount(-1);
        arcs=new ArrayList<Arc>();
        colors=new ArrayList<Color>();
        colors.add((Color)Paint.valueOf("RED"));
        colors.add((Color)Paint.valueOf("GREEN"));
        colors.add((Color)Paint.valueOf("YELLOW"));
        colors.add((Color)Paint.valueOf("BLUE"));
        this.x=x;
        this.y=y;
        this.length=length;
        for(int i=0;i<4;i++){
            a1 = new Arc(x, y, length/2, length/2, 0, 79);
            a1.setStartAngle((degree+(i*90))%360);
            a1.setType(ArcType.OPEN);
            a1.setFill(null);
            a1.setStroke(colors.get((i%4)));
            a1.setStrokeWidth(18);
            arcs.add(a1);
        }
        move_obstacle(new ActionEvent());
    }

    @Override
    public void move_obstacle(ActionEvent event) {
        degree = (degree + 1) % 360;
        for(int i=0;i<4;i++){
            arcs.get(i).setStartAngle((degree+(i*90))%360);
        }
    }

    @Override
    public void detect_collision(ActionEvent event) {
        this.check_crossed();
        if(player.getBall().getFill()==Paint.valueOf("WHITE"))
            return;
        Circle ball=player.getBall();
        for(int i=0;i<4;i++){
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
                left_behind=true;
                System.out.println("Rotating Circle crossed");
                game.addObstaclesCrossed();
            }
        }
    }

    @Override
    public void remove_obstacle(Pane pane) {
        for(int i=0;i<4;i++)
            pane.getChildren().remove(arcs.get(i));
        timeline.stop();
        collision.stop();
    }


    @Override
    public void add_obstacle(Pane pane) {
        for(int i=0;i<4;i++)
            pane.getChildren().add(arcs.get(i));
    }

    public void start_moving(){
        timeline.playFromStart();
        collision.playFromStart();
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
        this.game=player.getGame();
        timeline = new Timeline(new KeyFrame(Duration.millis(game.getTime()*12), this::move_obstacle));
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision.setCycleCount(-1);
        arcs=new ArrayList<Arc>();
        colors=new ArrayList<Color>();
        colors.add((Color)Paint.valueOf("RED"));
        colors.add((Color)Paint.valueOf("GREEN"));
        colors.add((Color)Paint.valueOf("YELLOW"));
        colors.add((Color)Paint.valueOf("BLUE"));
        for(int i=0;i<4;i++){
            a1 = new Arc(x, y, length/2, length/2, 0, 79);
            a1.setStartAngle((degree+(i*90))%360);
            a1.setType(ArcType.OPEN);
            a1.setFill(null);
            a1.setStroke(colors.get((i%4)));
            a1.setStrokeWidth(18);
            arcs.add(a1);
        }
        move_obstacle(new ActionEvent());
    }
}
