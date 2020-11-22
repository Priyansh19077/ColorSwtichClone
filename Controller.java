package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class Controller{
    private Stage primaryStage;
    Arc a1;
    private DataClass dataclass;
    private GameClass currentGame;
    private Scene s1;
    public void display_main_menu(Stage primaryStage){
        Pane pane=new Pane();
        s1=new Scene(pane, 500, 620, Color.BLACK);
        primaryStage.setScene(s1);
        Label l1=new Label("<-------MAIN MENU------->");
        l1.setLayoutY(50);
        l1.setLayoutX(66);
        l1.setTextFill(Paint.valueOf("#FFFAF0"));
        l1.setStyle("-fx-font-size:30;");
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        Button new_game=new Button("NEW GAME");
        Button resume_game=new Button("CONTINUE GAME");
        Button exit_game=new Button("EXIT GAME");
        new_game.setTextFill(Paint.valueOf("Black"));
        resume_game.setTextFill(Paint.valueOf("Black"));
        exit_game.setTextFill(Paint.valueOf("Black"));
        new_game.setStyle("-fx-background-color: #A9FF8A; -fx-background-radius: 10px; -fx-font-size:20");
        resume_game.setStyle("-fx-background-color: #57FFF1; -fx-background-radius: 10px; -fx-font-size:20");
        exit_game.setStyle("-fx-background-color: #FF1A85; -fx-background-radius: 10px; -fx-font-size:20");
        new_game.setLayoutY(180);
        new_game.setLayoutX(130);
        resume_game.setLayoutY(280);
        resume_game.setLayoutX(130);
        exit_game.setLayoutY(380);
        exit_game.setLayoutX(130);
        new_game.setMinWidth(250);
        resume_game.setMinWidth(250);
        exit_game.setMinWidth(250);
        pane.getChildren().addAll(l1, new_game, resume_game, exit_game);
        new_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new_game();
            }
        });
        resume_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                display_saved_games();
            }
        });
        exit_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exit_game();
            }
        });
        this.primaryStage.show();

    }
    public void display_saved_games(){
        Pane pane=new Pane();
        s1=new Scene(pane, 500, 620, Color.BLACK);
        primaryStage.setScene(s1);
        Label l1=new Label("<------SAVED GAMES------>");
        l1.setLayoutY(60);
        l1.setLayoutX(66);
        l1.setTextFill(Paint.valueOf("#FFFAF0"));
        l1.setStyle("-fx-font-size:30;");
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        Button main_menu=new Button("RETURN TO MAIN MENU");
        main_menu.setTextFill(Paint.valueOf("Black"));
        main_menu.setStyle("-fx-background-color: #A9FF8A; -fx-background-radius: 5px; -fx-font-size:17");
        main_menu.setLayoutY(110);
        main_menu.setLayoutX(150);
        main_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                display_main_menu(primaryStage);
            }
        });
        pane.getChildren().addAll(l1, main_menu);
        primaryStage.show();
    }
    public void select_game(String file_name){ //do the deserialization here

//        continue_game(saved_game);
    }
    public void continue_game(GameClass g1){
        currentGame=g1;
        Pane p1=currentGame.getPane();
//        Pane p2=new Pane();
//        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
//        Background bg=new Background(bf);
//        p2.setBackground(bg);
//        p2.getChildren().addAll(p1.getChildren());
//        p1=null;
//        s1=new Scene(p2, 500, 620, Color.BLACK);
        this.primaryStage.setScene(g1.getScene());
        this.primaryStage.show();
        g1.resumeGame( this);
    }
    public void display_pause_menu(){
        Pane pane=new Pane();
        s1=new Scene(pane, 500, 620, Color.BLACK);
        primaryStage.setScene(s1);
        Label l1=new Label("<-------PAUSE MENU------->");
        l1.setLayoutY(50);
        l1.setLayoutX(60);
        l1.setTextFill(Paint.valueOf("#FFFAF0"));
        l1.setStyle("-fx-font-size:30;");
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        Button new_game=new Button("RESTART GAME");
        Button save_game=new Button("SAVE GAME");
        Button resume_game=new Button("RESUME GAME");
        Button main_menu=new Button("MAIN MENU");
        save_game.setTextFill(Paint.valueOf("Black"));
        new_game.setTextFill(Paint.valueOf("Black"));
        resume_game.setTextFill(Paint.valueOf("Black"));
        main_menu.setTextFill(Paint.valueOf("Black"));
        save_game.setStyle("-fx-background-color: #A9FF8A; -fx-background-radius: 10px; -fx-font-size:20");
        resume_game.setStyle("-fx-background-color: #57FFF1; -fx-background-radius: 10px; -fx-font-size:20");
        main_menu.setStyle("-fx-background-color: #FF1A85; -fx-background-radius: 10px; -fx-font-size:20");
        new_game.setStyle("-fx-background-color: #FFFB8A; -fx-background-radius: 10px; -fx-font-size:20");
        save_game.setLayoutY(180);
        save_game.setLayoutX(150);
        resume_game.setLayoutY(280);
        resume_game.setLayoutX(150);
        new_game.setLayoutY(380);
        new_game.setLayoutX(150);
        main_menu.setLayoutY(480);
        main_menu.setLayoutX(150);
        save_game.setMinWidth(200);
        resume_game.setMinWidth(200);
        main_menu.setMinWidth(200);
        new_game.setMinWidth(200);
        pane.getChildren().addAll(l1, save_game, resume_game, main_menu, new_game);
        save_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save_game();
            }
        });
        resume_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                continue_game(currentGame);
            }
        });
        main_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentGame=null;
                display_main_menu(primaryStage);
            }
        });
        new_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new_game();
            }
        });
        this.primaryStage.show();
    }
    public void new_game(){
        Pane pane=new Pane();
        s1=new Scene(pane, 500, 620, Color.BLACK);
        GameClass g1=new GameClass(pane, this, s1);
        currentGame=g1;
        this.primaryStage.setScene(s1);
        g1.addNodes();
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        this.primaryStage.show();
        g1.startGame();
    }
    public void runController(){
        display_main_menu(primaryStage);
    }
    public Controller(DataClass data, Stage primaryStage){
        this.dataclass=data;
        this.primaryStage=primaryStage;
    }
    public void save_game(){

    }
    public void display_end_game_menu() {
        Pane pane=new Pane();
        currentGame.getPlayer().addScore(10);
        s1=new Scene(pane, 500, 620, Color.BLACK);
        primaryStage.setScene(s1);
        Label l1=new Label("<-------GAME OVER------->");
        l1.setLayoutY(50);
        l1.setLayoutX(50);
        l1.setTextFill(Paint.valueOf("#FFFAF0"));
        l1.setStyle("-fx-font-size:30;");
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        Button new_game=new Button("RESTART GAME");
        Button main_menu=new Button("MAIN MENU");
        new_game.setTextFill(Paint.valueOf("Black"));
        main_menu.setTextFill(Paint.valueOf("Black"));
        String current_score=String.valueOf(currentGame.getPlayer().getScore());
        String current_best_score=String.valueOf(currentGame.getPlayer().getScore());
        Button continue_game=new Button();
        try {
            Image image = new Image(new FileInputStream("Media/play.png"));
            ImageView view = new ImageView(image);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            continue_game.setGraphic(view);
            continue_game.setLayoutY(185.8);
            continue_game.setLayoutX(182.1);
            continue_game.setStyle("-fx-background-color: #000000; -fx-background-radius: 2000px");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        while(current_best_score.length()<("BEST SCORE").length()){
            current_best_score=" "+current_best_score;
        }

        while(current_score.length()<("SCORE").length()){
            current_score=" "+current_score;
        }
        Button score=new Button("SCORE\n"+current_score);
        Button best_score=new Button("BEST SCORE\n"+current_best_score);
        score.setTextFill(Paint.valueOf("YELLOW"));
        best_score.setTextFill(Paint.valueOf("YELLOW"));
        score.setStyle("-fx-background-color: #000000; -fx-background-radius: 10px; -fx-font-size:25");
        best_score.setStyle("-fx-background-color: #000000; -fx-background-radius: 10px; -fx-font-size:25");
        main_menu.setStyle("-fx-background-color: #FF1A85; -fx-background-radius: 10px; -fx-font-size:20");
        new_game.setStyle("-fx-background-color: #FFFB8A; -fx-background-radius: 10px; -fx-font-size:20");
        score.setLayoutY(130);
        best_score.setLayoutY(130);
        score.setLayoutX(0);
        best_score.setLayoutX(330);
        score.setMinWidth(150);
        best_score.setMinWidth(150);
        a1 = new Arc(230, 230, 45, 45, 90, 360);
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.millis(24), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(a1.getLength());
                a1.setLength(a1.getLength()-1);
            }
        }));
        timeline.setCycleCount(-1);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStrokeWidth(12);
        a1.setStroke(Paint.valueOf("CYAN"));
        new_game.setLayoutY(380);
        new_game.setLayoutX(130);
        main_menu.setLayoutY(470);
        main_menu.setLayoutX(130);
        main_menu.setMinWidth(200);
        new_game.setMinWidth(200);
//        pane.getChildren().addAll(l1, main_menu, new_game, a1, score, best_score, actual_best_score, actual_score);
        pane.getChildren().addAll(l1, main_menu, new_game, best_score, score, continue_game, a1);
        timeline.playFromStart();
        main_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentGame=null;
                display_main_menu(primaryStage);
                timeline.stop();
            }
        });
        continue_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                continue_game(currentGame);
                timeline.stop();
                timeline.setCycleCount(0);
            }
        });
        new_game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new_game();
                timeline.stop();
            }
        });
        a1.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(a1.getLength()<=-1){
                    continue_game.setVisible(false);
                    timeline.stop();
                    a1.setVisible(false);
                    timeline.setCycleCount(0);
                    Timeline t1=new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            score.setVisible(false);
                            best_score.setVisible(false);
                        }
                    }));
                    t1.setCycleCount(25);
                    t1.play();
                    t1.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            score.setLayoutX(130);
                            score.setMinWidth(200);
                            score.setLayoutY(150);
                            score.setFont(Font.font("COMIC SANS"));
                            score.setTextFill(Paint.valueOf("CYAN"));
                            best_score.setLayoutX(130);
                            best_score.setMinWidth(200);
                            best_score.setLayoutY(250);
                            score.setVisible(true);
                            best_score.setVisible(true);
                        }
                    });

//                    continue_with_stars.setVisible(false);
                }
            }
        });
        this.primaryStage.show();
    }
    public void exit_game(){
        this.primaryStage.close();
    }
}
