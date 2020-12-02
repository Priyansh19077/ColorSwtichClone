package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;

public class RotatingCircle extends ObstacleClass{
    private double x, y, length;
    private double degree=0;
    transient private Arc a1,a2, a3, a4;
    transient private Timeline collision;
    public RotatingCircle(double x, double y, double length, PlayerClass player){
        super(player, player.getGame());
        timeline = new Timeline(new KeyFrame(Duration.millis(12), this::move_obstacle));
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision.setCycleCount(-1);
        this.x=x;
        this.y=y;
        this.length=length;
        a1 = new Arc(x, y, length/2, length/2, 0, 79);
        a2 = new Arc(x, y, length/2, length/2, 0, 79);
        a3 = new Arc(x, y, length/2, length/2, 0, 79);
        a4 = new Arc(x, y, length/2, length/2, 0, 79);
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStroke(Paint.valueOf("RED"));
        a1.setStrokeWidth(18);
        a2.setType(ArcType.OPEN);
        a2.setFill(null);
        a2.setStroke(Paint.valueOf("BLUE"));
        a2.setStrokeWidth(18);
        a3.setType(ArcType.OPEN);
        a3.setFill(null);
        a3.setStroke(Paint.valueOf("GREEN"));
        a3.setStrokeWidth(18);
        a4.setType(ArcType.OPEN);
        a4.setFill(null);
        a4.setStroke(Paint.valueOf("YELLOW"));
        a4.setStrokeWidth(18);

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
    public void detect_collision(ActionEvent event) {
        Circle ball=player.getBall();
        if(ball.getCenterY()>y+200 || ball.getCenterY()<y-200)
            return;
        ArrayList<Shape> intersections=new ArrayList<Shape>();
        if(a1.getStroke()!=ball.getFill())
            intersections.add(Shape.intersect(a1, ball));
        if(a2.getStroke()!=ball.getFill())
            intersections.add(Shape.intersect(a2, ball));
        if(a3.getStroke()!=ball.getFill())
            intersections.add(Shape.intersect(a3, ball));
        if(a4.getStroke()!=ball.getFill())
            intersections.add(Shape.intersect(a4, ball));
        for(int i=0;i<intersections.size();i++){
            Shape shape=intersections.get(i);
//            System.out.println(intersections.size());
            if(shape.getLayoutBounds().getHeight()>=1 && shape.getLayoutBounds().getWidth()>=1){
//                System.out.println("Collision detected!! Game over!");
                timeline.stop();
                collision.stop();
                player.stopMoving();
                game.endGame();
            }

        }
    }
    @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void remove_obstacle(Pane pane) {
        pane.getChildren().removeAll(a1, a2, a3, a4);
        timeline.stop();
        collision.stop();
    }


    @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(a1,a2,a3,a4);
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
        this.timeline = new Timeline(new KeyFrame(Duration.millis(12), this::move_obstacle));
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        a1 = new Arc(x, y, length/2, length/2, 0, 79);
        a2 = new Arc(x, y, length/2, length/2, 0, 79);
        a3 = new Arc(x, y, length/2, length/2, 0, 79);
        a4 = new Arc(x, y, length/2, length/2, 0, 79);
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStroke(Paint.valueOf("RED"));
        a1.setStrokeWidth(18);
        a2.setType(ArcType.OPEN);
        a2.setFill(null);
        a2.setStroke(Paint.valueOf("BLUE"));
        a2.setStrokeWidth(18);
        a3.setType(ArcType.OPEN);
        a3.setFill(null);
        a3.setStroke(Paint.valueOf("GREEN"));
        a3.setStrokeWidth(18);
        a4.setType(ArcType.OPEN);
        a4.setFill(null);
        a4.setStroke(Paint.valueOf("YELLOW"));
        a4.setStrokeWidth(18);
    }
}
