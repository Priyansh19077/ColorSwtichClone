package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ObstacleClass implements Serializable {
    transient protected Timeline timeline;
    transient protected PlayerClass player;
    transient protected GameClass game;
    protected boolean left_behind=false;
    public ObstacleClass(PlayerClass player, GameClass game){
        this.player=player;
        this.game=game;
        //just for calling super
    }
    public abstract void move_obstacle(ActionEvent event);  // to rotate or move the obstacle as necessary
    public abstract void remove_obstacle(Pane pane); // to stop all the timelines and clear all the data of the obstacle for better latency
    public abstract void start_moving();
    public abstract void add_obstacle(Pane pane);
    public abstract void moveDown(double a);
    public abstract void stopMoving();
    public abstract double getY();
    public abstract void check_crossed();
    public abstract void initialize(ObstacleClass obs, PlayerClass player);
}
