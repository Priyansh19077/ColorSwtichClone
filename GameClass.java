package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class GameClass {
    private PlayerClass player;
    private Button b1;
    private Button pause;
    private ArrayList<ObstacleClass> obstacles;
    private ColorChangerClass nextColorChanger = null;
    private StarClass nextStar = null;
    private ArrayList<Color> colors;
    private Pane pane;
    private Scene scene;
    transient private Controller controller;
    private boolean gameEnded;
    public GameClass(Pane pane, Controller controller, Scene scene){
        this.controller=controller;
        this.pane=pane;
        this.scene=scene;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.P) {
                    pauseGame();
                }
            }
        });
        this.obstacles=new ArrayList<ObstacleClass>();
        this.colors=new ArrayList<Color>();
        this.b1=new Button();
        this.pause=new Button("Pause");
        b1.setLayoutY(1000);
        b1.setLayoutY(1000);
        pause.setLayoutX(0);
        pause.setLayoutY(0);
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
        player.stopMoving();
        for(ObstacleClass i:obstacles){
            i.stopMoving();
        }
        controller.display_pause_menu();
    }
    public void resumeGame(Controller controller){  // deserialization
        this.controller=controller;
//        this.pane=p1;
        this.startGame();
    }
    public void addNodes(){
        pane.getChildren().add(b1);
        pane.getChildren().add(player.getBall());
        for(ObstacleClass i:obstacles){
            i.add_obstacle(pane);
        }
//        pane.getChildren().add(pause);
    }
    public Pane getPane(){
        return pane;
    }
    public ArrayList<ObstacleClass> getObstacles(){
        return obstacles;
    }
    public Scene getScene(){
        return this.scene;
    }
}
