package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class GameClass {
    private PlayerClass player;
    private Button b1;
    private ArrayList<ObstacleClass> obstacles;
    private ColorChangerClass nextColorChanger = null;
    private StarClass nextStar = null;
    private ArrayList<Color> colors;
    private Pane pane;
    public GameClass(Pane pane){
        this.pane=pane;
        this.obstacles=new ArrayList<ObstacleClass>();
        this.colors=new ArrayList<Color>();
        this.b1=new Button();
        b1.setLayoutY(1000);
        b1.setLayoutY(1000);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.moveDown();
            }
        });
        colors.add((Color) Paint.valueOf("BLUE"));
        colors.add((Color) Paint.valueOf("GREEN"));
        colors.add((Color) Paint.valueOf("RED"));
        colors.add((Color) Paint.valueOf("YELLOW"));
        this.player=new PlayerClass((new Random()).nextInt(4), colors, this.pane, this);
        for(int i=0;i<9;i+=2) {
            this.obstacles.add(new RotatingRectangle(250, 150-i*400, 80));
            this.obstacles.add(new RotatingCircle(250,150-i*400-400, 160));
//            this.obstacles.add(new RotatingCrosses(250, 150-i*400-800, 80));
        }
    }
    public void startGame(){
        player.startMoving();
        for(ObstacleClass i:obstacles){
            i.start_moving();
        }
    }
    public void pauseGame(){ // serialization

    }
    public void resumeGame(Pane p1){  // deserialization

    }
    public void addNodes(){
        pane.getChildren().add(b1);
        pane.getChildren().add(player.getBall());
        for(ObstacleClass i:obstacles){
            i.add_obstacle(pane);
        }
    }
    public ArrayList<ObstacleClass> getObstacles(){
        return obstacles;
    }

}
