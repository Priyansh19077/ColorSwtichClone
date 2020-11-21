package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class Controller{
    private Stage primaryStage;
    private DataClass dataclass;
    public void display_main_menu(Stage primaryStage){
        Pane pane=new Pane();
        Scene s1=new Scene(pane, 500, 620, Color.BLACK);
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
        Button resume_game=new Button("RESUME GAME");
        Button exit_game=new Button("EXIT GAME");
        new_game.setTextFill(Paint.valueOf("Black"));
        resume_game.setTextFill(Paint.valueOf("Black"));
        exit_game.setTextFill(Paint.valueOf("Black"));
        new_game.setStyle("-fx-background-color: #A9FF8A; -fx-background-radius: 10px; -fx-font-size:20");
        resume_game.setStyle("-fx-background-color: #57FFF1; -fx-background-radius: 10px; -fx-font-size:20");
        exit_game.setStyle("-fx-background-color: #FF1A85; -fx-background-radius: 10px; -fx-font-size:20");
        new_game.setLayoutY(180);
        new_game.setLayoutX(150);
        resume_game.setLayoutY(280);
        resume_game.setLayoutX(150);
        exit_game.setLayoutY(380);
        exit_game.setLayoutX(150);
        new_game.setMinWidth(200);
        resume_game.setMinWidth(200);
        exit_game.setMinWidth(200);
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
        Scene s1=new Scene(pane, 500, 620, Color.BLACK);
        primaryStage.setScene(s1);
        Label l1=new Label("<------SAVED GAMES------>");
        l1.setLayoutY(60);
        l1.setLayoutX(66);
        l1.setTextFill(Paint.valueOf("#FFFAF0"));
        l1.setStyle("-fx-font-size:30;");
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        Button main_menu=new Button("Return To Main Menu");
        main_menu.setTextFill(Paint.valueOf("Black"));
        main_menu.setStyle("-fx-background-color: #A9FF8A; -fx-background-radius: 5px; -fx-font-size:17");
        main_menu.setLayoutY(110);
        main_menu.setLayoutX(164);
        main_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                display_main_menu(primaryStage);
            }
        });
        pane.getChildren().addAll(l1, main_menu);
        primaryStage.show();
    }
    public void select_game(){

    }
    public void continue_game(){

    }
    public void new_game(){
        Pane pane=new Pane();
        GameClass g1=new GameClass(pane);
        Scene s1=new Scene(pane, 500, 620, Color.BLACK);
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
    public void exit_game(){
        this.primaryStage.close();
    }
}
