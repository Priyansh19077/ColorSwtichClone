package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class GameClass {
    private PlayerClass player;
    private Button b1;
    private Button pause;
    private int number_of_obstacles=0;
    private ArrayList<ObstacleClass> obstacles;
    private ArrayList<StarClass> stars;
    private ArrayList<ColorChangerClass> colorChangers;
    private ArrayList<ColorChangerClass> changers;
    private ArrayList<ObstacleClass> available_obs;
    private ColorChangerClass nextColorChanger = null;
    private StarClass nextStar = null;
    private ArrayList<Color> colors;
    private Pane pane;
    private Scene scene;
    private StarClass constant_star;
    public Label constant_score;
    public Label constant_stars;
    public Button constant_score1;
    public Button constant_stars1;
    transient private Controller controller;
    private boolean gameEnded;
    public int stars_remaining;
    public int required_stars=3;
    private int score;
    Timeline timeline;
    public GameClass(Pane pane, Controller controller, Scene scene){
        this.controller=controller;
        this.pane=pane;
        System.out.println(pane.getLayoutY());
        this.scene=scene;
        this.timeline=new Timeline(new KeyFrame(Duration.millis(10), this::update_UI));
        timeline.setCycleCount(-1);
        stars_remaining=0;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.P) {
                    pauseGame();
                }
                if(event.getCode()==KeyCode.G){
                    player.stopMoving();
                    for(ObstacleClass i:obstacles){
                        i.stopMoving();
                    }
                    timeline.stop();
                    timeline.setCycleCount(0);
                    controller.display_end_game_menu();
                }
            }
        });
        this.obstacles=new ArrayList<ObstacleClass>();
        this.stars=new ArrayList<StarClass>();
        this.colors=new ArrayList<Color>();
        this.colorChangers=new ArrayList<ColorChangerClass>();
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
        available_obs=new ArrayList<ObstacleClass>();
        available_obs.add(new RotatingCircle(250, 150, 80, player));
        available_obs.add(new RotatingRectangle(250, 150, 80, player));
        Random rand=new Random();
        for(int i=0;i<3;i++){
            int p=rand.nextInt(available_obs.size());
            if(available_obs.get(p).getClass()==new RotatingCircle(0, 0, 0, player).getClass()) {
                obstacles.add(new RotatingCircle(250, 150-number_of_obstacles*400, 200, player));
            }else if(available_obs.get(p).getClass()==new RotatingRectangle(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingRectangle(250, 150-number_of_obstacles*400, 100, player));
            }
            stars.add(new StarClass(210, 150 - number_of_obstacles * 400 - 40, 10, player, pane, 60));
            ColorChangerClass c1=new ColorChangerClass(250, 150-number_of_obstacles*400-200, player, pane);
            colorChangers.add(c1);
            number_of_obstacles++;
        }
//        constant_stars=new Label("STARS: "+stars_remaining);
//        constant_score=new Label("SCORE: "+player.getScore());

//        constant_score.setLayoutY(40);
//        constant_score.setLayoutX(10);
//        constant_score.setTextFill(Paint.valueOf("CYAN"));
//        constant_score.setStyle("-fx-font-size:15;");
//        constant_stars.setLayoutY(40);
//        constant_stars.setLayoutX(420);
//        constant_stars.setTextFill(Paint.valueOf("CYAN"));
//        constant_stars.setStyle("-fx-font-size:15;");
        constant_stars1=new Button("STARS  "+stars_remaining);
        constant_score1=new Button("SCORE  "+player.getScore());
        constant_score1.setLayoutY(40);
        constant_score1.setLayoutX(5);
        constant_score1.setTextFill(Paint.valueOf("BLACK"));
        constant_stars1.setLayoutY(40);
        constant_stars1.setLayoutX(390);
        constant_stars1.setTextFill(Paint.valueOf("BLACK"));
        constant_score1.setStyle("-fx-background-color: #FF1A85; -fx-background-radius: 5px; -fx-font-size:14");
        constant_stars1.setStyle("-fx-background-color: #FFFB8A; -fx-background-radius: 5px; -fx-font-size:14");
        constant_score1.setMinWidth(100);
        constant_score1.setMinHeight(35);
        constant_stars1.setMinWidth(100);
        constant_stars1.setMinHeight(35);
//        constant_star=new StarClass(400, 30, 0, player, pane, 40);
    }
    public void startGame(){
        player.startMoving();
        for(ObstacleClass i:obstacles){
            i.start_moving();
        }
        timeline.setCycleCount(-1);
        timeline.play();
    }
    public void pauseGame(){ // serialization
        player.stopMoving();
        for(ObstacleClass i:obstacles){
            i.stopMoving();
        }
        timeline.stop();
        timeline.setCycleCount(0);
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
        for(StarClass i:stars){
            i.addStar(pane);
        }
        for(ColorChangerClass i:colorChangers){
            i.addColorChanger(pane);
        }
        pane.getChildren().addAll(constant_score1, constant_stars1);
//        constant_star.addStar(pane);
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
    public PlayerClass getPlayer(){
        return this.player;
    }
    public void update_UI(ActionEvent event){
        ObstacleClass obs1=obstacles.get(0);
        if(player.getBall().getCenterY()-obs1.getY()<=-500){
            obstacles.remove(obs1);
            obs1.remove_obstacle(pane);
            StarClass s1=stars.get(0);
            ColorChangerClass c1=colorChangers.get(0);
            c1.removeColorChanger(pane);
            stars.remove(0);
            colorChangers.remove(0);
            s1.removeStar(pane);
            Random rand=new Random();
            int p=rand.nextInt(available_obs.size());
            if(available_obs.get(p).getClass()==new RotatingCircle(0, 0, 0, player).getClass()) {
                obstacles.add(new RotatingCircle(250, 150-number_of_obstacles*400, 200, player));
            }else if(available_obs.get(p).getClass()==new RotatingRectangle(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingRectangle(250, 150-number_of_obstacles*400, 100, player));
            }
            obstacles.get(obstacles.size()-1).add_obstacle(pane);
            stars.add(new StarClass(210, 150 - number_of_obstacles * 400 - 40, 10, player, pane, 60));
            colorChangers.add(new ColorChangerClass(250, 150-number_of_obstacles*400-200, player, pane));
            number_of_obstacles++;
            stars.get(stars.size()-1).addStar(pane);
            colorChangers.get(colorChangers.size()-1).addColorChanger(pane);
            obstacles.get(obstacles.size()-1).start_moving();
        }
        constant_score1.setText("SCORE  "+player.getScore());
        constant_stars1.setText("STARS  "+stars_remaining);
    }
}
