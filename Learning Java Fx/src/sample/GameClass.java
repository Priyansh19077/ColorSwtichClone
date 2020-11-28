package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

public class GameClass implements Serializable {
    private PlayerClass player;
    transient private Button b1;
    transient private Button pause;
    private double shiftedX;
    private double shiftedY;
    private int number_of_obstacles=0;
    private ArrayList<ObstacleClass> obstacles;
    private ArrayList<StarClass> stars;
    private ArrayList<ColorChangerClass> colorChangers;
    private ArrayList<ObstacleClass> available_obs;
    private double absY;
    transient private Button timerLabel;
    transient private ArrayList<Color> colors;
    transient private Pane pane;
    transient private Scene scene;
    transient public Button constant_score1;
    transient public Button constant_stars1;
    transient private Controller controller;
    private boolean gameEnded;
    public double y_value=460;
    public int stars_remaining;
    public int required_stars=3;
    transient public Timeline timeline;
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
        available_obs.add(new RotatingCrosses(250, 250, 80, player));
        available_obs.add(new RotatingCirclesExtra(250, 250, 80, player));
        Random rand=new Random();
        for(int i=0;i<3;i++){
            int p=rand.nextInt(available_obs.size());
            if(available_obs.get(p).getClass()==new RotatingCircle(0, 0, 0, player).getClass()) {
                obstacles.add(new RotatingCircle(250, 150-number_of_obstacles*400, 200, player));
            }else if(available_obs.get(p).getClass()==new RotatingRectangle(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingRectangle(250, 150-number_of_obstacles*400, 100, player));
            }else if(available_obs.get(p).getClass()==new RotatingCrosses(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingCrosses(250, 150-number_of_obstacles*400, 90, player));
            }else if(available_obs.get(p).getClass()==new RotatingCirclesExtra(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingCirclesExtra(250, 150-number_of_obstacles*400, 200, player));
            }
            stars.add(new StarClass(210, 150 - number_of_obstacles * 400 - 40, 10, player, pane, 60));
            ColorChangerClass c1=new ColorChangerClass(250, 150-number_of_obstacles*400-200, player, pane);
            colorChangers.add(c1);
            number_of_obstacles++;
        }
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
    public void startGame()
    {
        // update timerLabel
        timerLabel=new Button("3");
        timerLabel.setVisible(false);
        timerLabel.setLayoutY(y_value);
        timerLabel.setLayoutX(200);
        timerLabel.setTextFill(Paint.valueOf("FFFB8A"));
        timerLabel.setStyle("-fx-background-color: transparent; -fx-font-size:60;");
        pane.getChildren().addAll(timerLabel);
        Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int p=Integer.parseInt(timerLabel.getText().toString());
                p--;
                timerLabel.setText(String.valueOf(p));
            }
        }));
        timeline1.setCycleCount(1);
        timeline1.playFromStart();
        timeline1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.startMoving();
                for(ObstacleClass i:obstacles){
                    i.start_moving();
                }
                for(StarClass i:stars)
                    i.startMoving();
                for(ColorChangerClass i:colorChangers)
                    i.startMoving();
                timeline.setCycleCount(-1);
                timeline.play();
            }
        });
    }
    public void pauseGame(){ // serialization
        player.stopMoving();
        for(ObstacleClass i:obstacles){
            i.stopMoving();
        }
        for(StarClass i:stars)
            i.stopMoving();
        for(ColorChangerClass i:colorChangers)
            i.stopMoving();
        timeline.stop();
        timeline.setCycleCount(0);
        controller.display_pause_menu();
        shiftedX=pane.getLayoutX();
        shiftedY=pane.getLayoutY();
        y_value=timerLabel.getLayoutY();
        absY=constant_score1.getLayoutY();
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
            }else if(available_obs.get(p).getClass()==new RotatingCrosses(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingCrosses(250, 150-number_of_obstacles*400, 90, player));
            }else if(available_obs.get(p).getClass()==new RotatingCirclesExtra(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingCirclesExtra(250, 150-number_of_obstacles*400, 200, player));
                System.out.println(1000);
            }
            obstacles.get(obstacles.size()-1).add_obstacle(pane);
            stars.add(new StarClass(210, 150 - number_of_obstacles * 400 - 40, 10, player, pane, 60));
            colorChangers.add(new ColorChangerClass(250, 150-number_of_obstacles*400-200, player, pane));
            number_of_obstacles++;
            stars.get(stars.size()-1).addStar(pane);
            stars.get(stars.size()-1).startMoving();
            colorChangers.get(colorChangers.size()-1).addColorChanger(pane);
            colorChangers.get(colorChangers.size()-1).startMoving();
            obstacles.get(obstacles.size()-1).start_moving();
        }
        constant_score1.setText("SCORE  "+player.getScore());
        constant_stars1.setText("STARS  "+stars_remaining);
    }
    public void serialize(DataClass dataClass){
        dataClass.saveGame(this);
        this.controller.display_main_menu();
    }
    public ArrayList<StarClass> getStars(){
        return this.stars;
    }
    public ArrayList<ColorChangerClass> getColorChangers(){
        return this.colorChangers;
    }
    public void initialize(Controller c1){
        this.pane=new Pane();
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        Scene s1=new Scene(pane, 500, 620, Color.BLACK);
        this.scene=s1;
        this.controller=c1;
        this.timeline=new Timeline(new KeyFrame(Duration.millis(10), this::update_UI));
        timeline.setCycleCount(-1);
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
        this.player.initialize(this.player, colors, pane);
        available_obs=new ArrayList<ObstacleClass>();
        available_obs.add(new RotatingCircle(250, 150, 80, player));
        available_obs.add(new RotatingRectangle(250, 150, 80, player));
        available_obs.add(new RotatingCrosses(250, 150, 80, player));
        available_obs.add(new RotatingCirclesExtra(250, 150, 80, player));
        constant_stars1=new Button("STARS  "+stars_remaining);
        constant_score1=new Button("SCORE  "+player.getScore());
        constant_score1.setLayoutX(5);
        constant_score1.setTextFill(Paint.valueOf("BLACK"));
        constant_stars1.setLayoutX(390);
        constant_stars1.setTextFill(Paint.valueOf("BLACK"));
        constant_score1.setStyle("-fx-background-color: #FF1A85; -fx-background-radius: 5px; -fx-font-size:14");
        constant_stars1.setStyle("-fx-background-color: #FFFB8A; -fx-background-radius: 5px; -fx-font-size:14");
        constant_score1.setMinWidth(100);
        constant_score1.setMinHeight(35);
        constant_stars1.setMinWidth(100);
        constant_stars1.setMinHeight(35);
        for(int i=0;i<obstacles.size();i++){
            ObstacleClass o=obstacles.get(i);
            o.initialize(o, player);
        }
        ArrayList<StarClass> new_star=new ArrayList<StarClass>();
        for(int i=0;i<stars.size();i++){
            StarClass o=stars.get(i);
            o.initialize(o, pane, player);
            new_star.add(o);
        }
//        stars=new_star;
        ArrayList<ColorChangerClass> new_color_changer=new ArrayList<ColorChangerClass>();
        for(int i=0;i<colorChangers.size();i++){
            ColorChangerClass o=colorChangers.get(i);
            o.initialize(o, pane, player);
            new_color_changer.add(o);
        }
//        colorChangers=new_color_changer;
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
        addNodes();
        pane.setLayoutX(shiftedX);
        pane.setLayoutY(shiftedY);
        constant_stars1.setLayoutY(absY);
        constant_score1.setLayoutY(absY);
        ObstacleClass obs1=obstacles.get(0);
        if(player.getBall().getCenterY()-obs1.getY()<=-500){
            obstacles.remove(obs1);
            obs1.remove_obstacle(pane);
            StarClass s12=stars.get(0);
            ColorChangerClass c12=colorChangers.get(0);
            c12.removeColorChanger(pane);
            stars.remove(0);
            colorChangers.remove(0);
            s12.removeStar(pane);
            Random rand=new Random();
            int p=rand.nextInt(available_obs.size());
            if(available_obs.get(p).getClass()==new RotatingCircle(0, 0, 0, player).getClass()) {
                obstacles.add(new RotatingCircle(250, 150-number_of_obstacles*400, 200, player));
            }else if(available_obs.get(p).getClass()==new RotatingRectangle(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingRectangle(250, 150-number_of_obstacles*400, 100, player));
            }else if(available_obs.get(p).getClass()==new RotatingCrosses(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingCrosses(250, 150-number_of_obstacles*400, 90, player));
            }else if(available_obs.get(p).getClass()==new RotatingCirclesExtra(0, 0, 0, player).getClass()){
                obstacles.add(new RotatingCirclesExtra(250, 150-number_of_obstacles*400, 200, player));
            }
            obstacles.get(obstacles.size()-1).add_obstacle(pane);
            stars.add(new StarClass(210, 150 - number_of_obstacles * 400 - 40, 10, player, pane, 60));
            colorChangers.add(new ColorChangerClass(250, 150-number_of_obstacles*400-200, player, pane));
            number_of_obstacles++;
            stars.get(stars.size()-1).addStar(pane);
            colorChangers.get(colorChangers.size()-1).addColorChanger(pane);
        }
        constant_score1.setText("SCORE  "+player.getScore());
        constant_stars1.setText("STARS  "+stars_remaining);
        controller.continue_game(this);
    }
}
