package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class ObstacleClass {
    protected Timeline timeline;
    protected PlayerClass player;
    public ObstacleClass(PlayerClass player){
        this.player=player;
        //just for calling super
    }
    public abstract void move_obstacle(ActionEvent event);  // to rotate or move the obstacle as necessary
    public abstract void detect_collision(PlayerClass player); //takes in the player as parameter - dependency
    public abstract void remove_obstacle(Pane pane); // to stop all the timelines and clear all the data of the obstacle for better latency
    public abstract void start_moving();
    public abstract void add_obstacle(Pane pane);
    public abstract void moveDown(double a);
    public abstract void stopMoving();
    public abstract double getY();
}
