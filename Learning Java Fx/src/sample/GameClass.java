package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameClass implements Serializable {
    private final PlayerClass player;
    transient private Button b1;
    transient private Button pause;
    private double shiftedX;
    private double shiftedY;
    private int number_of_obstacles=0;
    private final ArrayList<ObstacleClass> obstacles;
    private final ArrayList<StarClass> stars;
    private final ArrayList<ColorChangerClass> colorChangers;
    private ArrayList<ObstacleClass> available_obs;
    private double absY;
    private int number_of_color_changers_crossed;
    transient private Button timerLabel;
    transient private ArrayList<Color> colors;
    transient private Pane pane;
    transient private Scene scene;
    transient public Button constant_score1;
    transient public Button constant_stars1;
    transient private Controller controller;
    private double time;
    private int level;
    private int number_of_obstacles_crossed=0;
    private final ArrayList<Double> times;
    public double y_value=460;
    public int stars_remaining;
    public int required_stars=3;
    transient public Timeline timeline;
    public GameClass(Pane pane, Controller controller, Scene scene){
        this.controller=controller;
        this.pane=pane;
        this.time=1;
        this.times=new ArrayList<>();
        for(int i=0;i<13;i++){
            times.add(1-i*(0.04));
        }
        this.level=1;
//        System.out.println(pane.getLayoutY());
        this.scene=scene;
        this.timeline=new Timeline(new KeyFrame(Duration.millis(10), this::update_UI));
        timeline.setCycleCount(-1);
        stars_remaining=0;
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.P) {
                pauseGame();
            }
            if(event.getCode()==KeyCode.G) {
                endGame();
            }
        });
        this.obstacles=new ArrayList<>();
        this.stars=new ArrayList<>();
        this.colors=new ArrayList<>();
        this.number_of_color_changers_crossed=0;
        this.colorChangers=new ArrayList<>();
        this.b1=new Button();
        this.pause=new Button("Pause");
        b1.setLayoutY(1000);
        b1.setLayoutY(1000);
        pause.setLayoutX(0);
        pause.setLayoutY(0);
        b1.setOnAction(new EventHandler<>() {
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
        available_obs=new ArrayList<>();
        available_obs.add(new RotatingCircle(250, 150, 80, player));
        available_obs.add(new RotatingRectangle(250, 150, 80, player));
        available_obs.add(new VerticalLines(250, player));
        available_obs.add(new HorizontalLine(0, player));
        available_obs.add(new RotatingCirclesExtra(250, 250, 80, 3, player));
        available_obs.add(new RotatingCrosses(250, 250, 80, player));
        Random rand=new Random();
        for(int i=0;i<3;i++){
            int p=rand.nextInt(6);
            if(available_obs.get(p).getClass()== RotatingCircle.class) {
                obstacles.add(new RotatingCircle(250, 150-number_of_obstacles*400, 200, player));
            }else if(available_obs.get(p).getClass()== RotatingRectangle.class){
                obstacles.add(new RotatingRectangle(250, 150-number_of_obstacles*400, 100, player));
            }else if(available_obs.get(p).getClass()== RotatingCrosses.class){
                obstacles.add(new RotatingCrosses(250, 150-number_of_obstacles*400, 90, player));
            }else if(available_obs.get(p).getClass()== RotatingCirclesExtra.class){
                obstacles.add(new RotatingCirclesExtra(250, 150-number_of_obstacles*400, 200, 3, player));
            }else if(available_obs.get(p).getClass()== VerticalLines.class)
                obstacles.add(new VerticalLines(150-number_of_obstacles*400-40, player));
            else if(available_obs.get(p).getClass()== HorizontalLine.class)
                obstacles.add(new HorizontalLine(150-number_of_obstacles*400+40, player));
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
        constant_score1.setDisable(true);
        constant_stars1.setDisable(true);
    }
    public void startGame()
    {
        b1.setDisable(true);
        timeline.setCycleCount(-1);
        timeline.play();
        // update timerLabel
        timerLabel=new Button("3");
        timerLabel.setVisible(true);
        timerLabel.setLayoutY(y_value);
        timerLabel.setLayoutX(195);
        timerLabel.setTextFill(Paint.valueOf("FFFB8A"));
        timerLabel.setStyle("-fx-background-color: transparent; -fx-font-size:60;");
        pane.getChildren().addAll(timerLabel);
        Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            int p=Integer.parseInt(timerLabel.getText());
            p--;
            timerLabel.setText(String.valueOf(p));
        }));
        timeline1.setCycleCount(3);
        timeline1.playFromStart();
        timeline1.setOnFinished(event -> {
            player.startMoving();
            timerLabel.setVisible(false);
            for(ObstacleClass i:obstacles){
                i.start_moving();
            }
            for(StarClass i:stars)
                i.startMoving();
            for(ColorChangerClass i:colorChangers)
                i.startMoving();
            b1.setDisable(false);
        });
    }
    public void pauseGame(){ // serialization
        player.stopMoving();
        b1.setDisable(true);
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
        b1.setDisable(false);
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
    }
    public Pane getPane(){
        return pane;
    }

    public Scene getScene(){
        return this.scene;
    }
    public PlayerClass getPlayer(){
        return this.player;
    }
    public void update_UI(ActionEvent event){
        timerLabel.setLayoutY(y_value);
        int new_level=(number_of_color_changers_crossed/3)+1;
        if(new_level>level){
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    File file=new File("Media/level_up.wav");
                    AudioInputStream sound= AudioSystem.getAudioInputStream(file);
                    Clip clip=AudioSystem.getClip();
                    clip.open(sound);
                    clip.start();
                    return null;
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        }
        level=new_level;
        player.setScore(number_of_obstacles_crossed*10);
        this.time=times.get(Math.min(number_of_obstacles/3, 12));
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
            int p;
            p=rand.nextInt(available_obs.size());
            if (available_obs.get(p).getClass() == RotatingCircle.class) {
                obstacles.add(new RotatingCircle(250, 150 - number_of_obstacles * 400, 200, player));
            } else if (available_obs.get(p).getClass() == RotatingRectangle.class) {
                obstacles.add(new RotatingRectangle(250, 150 - number_of_obstacles * 400, 100, player));
            } else if (available_obs.get(p).getClass() == RotatingCrosses.class) {
                obstacles.add(new RotatingCrosses(250, 150 - number_of_obstacles * 400, 90, player));
            } else if (available_obs.get(p).getClass() == RotatingCirclesExtra.class) {
                obstacles.add(new RotatingCirclesExtra(250, 150 - number_of_obstacles * 400, 200, 3, player));
                System.out.println(1000);
            } else if (available_obs.get(p).getClass() == VerticalLines.class)
                obstacles.add(new VerticalLines(150 - number_of_obstacles * 400 - 40, player));
            else if (available_obs.get(p).getClass() == HorizontalLine.class)
                obstacles.add(new HorizontalLine(150 - number_of_obstacles * 400 + 40, player));
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
        constant_score1.setText("SCORE  "+player.getScore()+"\nLEVEL  "+this.level);
        constant_stars1.setText("STARS  "+stars_remaining);
    }
    public void serialize(DataClass dataClass){
        dataClass.saveGame(this);
        this.controller.display_main_menu();
    }
    public ArrayList<ObstacleClass> getObstacles(){
        return this.obstacles;
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
        this.colors=new ArrayList<>();
        this.b1=new Button();
        this.pause=new Button("Pause");
        b1.setLayoutY(1000);
        b1.setLayoutY(1000);
        pause.setLayoutX(0);
        pause.setLayoutY(0);
        b1.setOnAction(event -> player.moveDown());
        colors.add((Color) Paint.valueOf("BLUE"));
        colors.add((Color) Paint.valueOf("GREEN"));
        colors.add((Color) Paint.valueOf("RED"));
        colors.add((Color) Paint.valueOf("YELLOW"));
        this.player.initialize(this.player, colors, pane);
        available_obs=new ArrayList<ObstacleClass>();
        available_obs.add(new RotatingCircle(250, 150, 80, player));
        available_obs.add(new RotatingRectangle(250, 150, 80, player));
        available_obs.add(new VerticalLines(250, player));
        available_obs.add(new HorizontalLine(0, player));
        available_obs.add(new RotatingCirclesExtra(250, 250, 80, 3, player));
        available_obs.add(new RotatingCrosses(250, 250, 80, player));
        constant_stars1=new Button("STARS  "+stars_remaining);
        constant_score1=new Button("SCORE  "+player.getScore()+"\nLEVEL  "+this.level);
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
        constant_score1.setDisable(true);
        constant_stars1.setDisable(true);
        for (ObstacleClass o : obstacles) {
            o.initialize(o, player);
        }
        ArrayList<StarClass> new_star= new ArrayList<>();
        for (StarClass o : stars) {
            o.initialize(o, pane, player);
            new_star.add(o);
        }
        ArrayList<ColorChangerClass> new_color_changer=new ArrayList<ColorChangerClass>();
        for (ColorChangerClass o : colorChangers) {
            o.initialize(o, pane, player);
            new_color_changer.add(o);
        }
        scene.setOnKeyPressed(event -> {
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
            int p;
            p=rand.nextInt(available_obs.size());
            if(available_obs.get(p).getClass()== RotatingCircle.class) {
                obstacles.add(new RotatingCircle(250, 150-number_of_obstacles*400, 200, player));
            }else if(available_obs.get(p).getClass()== RotatingRectangle.class){
                obstacles.add(new RotatingRectangle(250, 150-number_of_obstacles*400, 100, player));
            }else if(available_obs.get(p).getClass()== RotatingCrosses.class){
                obstacles.add(new RotatingCrosses(250, 150-number_of_obstacles*400, 90, player));
            }else if(available_obs.get(p).getClass()== RotatingCirclesExtra.class){
                obstacles.add(new RotatingCirclesExtra(250, 150-number_of_obstacles*400, 200, 3, player));
            }else if(available_obs.get(p).getClass()== VerticalLines.class)
                obstacles.add(new VerticalLines(150-number_of_obstacles*400-40, player));
            else if(available_obs.get(p).getClass()== HorizontalLine.class)
                obstacles.add(new HorizontalLine(150-number_of_obstacles*400+40, player));
            obstacles.get(obstacles.size()-1).add_obstacle(pane);
            stars.add(new StarClass(210, 150 - number_of_obstacles * 400 - 40, 10, player, pane, 60));
            colorChangers.add(new ColorChangerClass(250, 150-number_of_obstacles*400-200, player, pane));
            number_of_obstacles++;
            stars.get(stars.size()-1).addStar(pane);
            colorChangers.get(colorChangers.size()-1).addColorChanger(pane);
        }
        controller.continue_game(this);
    }
    public void endGame(){
        if(controller.getGameCount()==1){
            controller.display_end_game_menu();
            return;
        }
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                File file=new File("Media/game_over.wav");
                AudioInputStream sound= AudioSystem.getAudioInputStream(file);
                Clip clip=AudioSystem.getClip();
                clip.open(sound);
                clip.start();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        player.stopMoving();
        for(ObstacleClass i:obstacles)
            i.stopMoving();
        for(StarClass i:stars)
            i.stopMoving();
        for(ColorChangerClass i:colorChangers)
            i.stopMoving();
        timeline.stop();
        timeline.setCycleCount(0);
        Timeline endGameTimeline=new Timeline(new KeyFrame(Duration.millis(500), event -> {
        }));
        endGameTimeline.setCycleCount(1);
        b1.setDisable(true);
        endGameTimeline.play();
        endGameTimeline.setOnFinished(event -> {
            player.stopMoving();
            controller.display_end_game_menu();
        });
    }
    public double getTime(){
        return this.time;
    }
    public void addObstaclesCrossed(){
        this.number_of_obstacles_crossed++;
        System.out.println(number_of_obstacles_crossed);
        System.out.println(level);
    }
    public void addColorChangersCrossed(){
        this.number_of_color_changers_crossed++;
    }
    public int getStars_Remaining(){
        return stars_remaining;
    }
    public int getLevel(){
        return this.level;
    }
}