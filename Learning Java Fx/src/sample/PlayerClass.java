package sample;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayerClass implements Serializable {
    private double x, y;
    private double radius;
    private int index;
    transient private Circle ball;
    transient private ArrayList<Timeline> timelines;
    transient private Pane pane;
    private boolean moving_up=false;
    private GameClass game;
    private double previous_pushing_point;
    private int score;
    public PlayerClass(int index, ArrayList<Color> colors, Pane pane, GameClass game){
        previous_pushing_point=Screen.getPrimary().getBounds().getHeight()/2;
        this.game=game;
        this.x=250;
        this.index=index;
        this.y=400;
        this.radius=10;
        this.score=0;
        this.pane=pane;
        this.ball=new Circle(x, y, radius, colors.get(index));
        this.timelines=new ArrayList<Timeline>();
        Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(0.7), this::move_down));
        timeline1.setCycleCount(-1);
        Timeline timeline2=new Timeline(new KeyFrame(Duration.millis(0.8), this::move_up));
        timeline2.setCycleCount(220);
        this.timelines.add(timeline1);
        this.timelines.add(timeline2);
        this.timelines.get(1).setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timelines.get(0).playFromStart();
                moving_up=false;
            }
        });
    }
    public void move_up(ActionEvent event){
//        System.out.println(y);
        moving_up=true;
        y=y-0.3;
        ball.setCenterY(y);
        if(y<330){
            if(330-y>pane.getLayoutY()) {
                pane.setLayoutY(330 - y);
                game.constant_score1.setLayoutY(y-330+40);
                game.constant_stars1.setLayoutY(y-330+40);
                game.y_value=(y-330+460);
            }
        }
//        System.out.println(y);
    }
    private void move_down(ActionEvent event){
        moving_up=false;
        y=y+0.1;
        ball.setCenterY(y);
    }

    public void startMoving(){
        timelines.get(0).play();
    }
    public void moveDown() {
        timelines.get(0).pause();
        timelines.get(1).playFromStart();
    }
    public Circle getBall(){
        return ball;
    }
    public void stopMoving(){
        for(Timeline i:timelines){
            i.pause();
        }
    }
    public void addScore(int a){
        this.score+=a;
    }
    public int getScore(){
        return this.score;
    }
    public GameClass getGame(){
        return game;
    }
    public void initialize(PlayerClass p1, ArrayList<Color> colors, Pane p){
        this.pane=p;
        this.ball=new Circle(x, y, radius, colors.get(index));
        this.timelines=new ArrayList<Timeline>();
        Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(0.7), this::move_down));
        timeline1.setCycleCount(-1);
        Timeline timeline2=new Timeline(new KeyFrame(Duration.millis(0.8), this::move_up));
        timeline2.setCycleCount(220);
        this.timelines.add(timeline1);
        this.timelines.add(timeline2);
        this.timelines.get(1).setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timelines.get(0).playFromStart();
                moving_up=false;
            }
        });
    }
    public void setIndex(int a){
        this.index=a;
    }
}
