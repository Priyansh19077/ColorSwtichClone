package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.event.ActionEvent;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.Random;

import static java.lang.Math.*;

public class Rotating_Cross extends Application{
    private double degree=0;
    private boolean previously_colliding=false;
    int value=0;
    private Timeline timeline1, timeline2, timeline3, timeline4;
    private Pane pane;
    private Scene s1;
    Line line1, line2, line3, line4;
    Line corner1, corner2, corner3, corner4, corner5, corner6, corner7, corner8;
    Line diag1, diag2;
    Line diag3, diag4;
    Line line5,line6,line7,line8;
    Circle c1;
    Button b1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.timeline1=new Timeline(new KeyFrame(Duration.millis(35), this::do_step));
        this.timeline2=new Timeline(new KeyFrame(Duration.millis(8), this::changeY));
        this.timeline3=new Timeline(new KeyFrame(Duration.millis(14), this::changeY1));
        this.timeline4=new Timeline(new KeyFrame(Duration.millis(8), this::collision));
        this.timeline3.setCycleCount(25);
        this.timeline1.setCycleCount(Timeline.INDEFINITE);
        this.timeline2.setCycleCount(Timeline.INDEFINITE);
        this.timeline2.play();
        this.timeline1.play();
        this.timeline3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline2.play();
                timeline3.playFromStart();
                timeline3.pause();
            }
        });
        Circle c2=new Circle();
        c2.setCenterY(250);
        c2.setCenterX(180);
        c2.setRadius(13);
        c2.setFill(Paint.valueOf("BLACK"));
        Circle c3=new Circle();
        c3.setCenterY(250);
        c3.setCenterX(320);
        c3.setRadius(13);
        c3.setFill(Paint.valueOf("BLACK"));
        b1=new Button("");
        b1.setLayoutX(10);
        b1.setLayoutY(10);
        b1.resize(1,1);
        line1=new Line(180, 180, 320, 180);
        diag1=new Line(180, 180, 320, 320);
        diag2=new Line(180, 320, 320, 180);
        diag3=new Line(180, 320, 320, 180);
        diag4=new Line(180, 320, 320, 180);
        line2=new Line(320, 180, 320, 320);
        line3=new Line(320, 320, 180, 320);
        line4=new Line(180, 320, 180, 180);
        line5=new Line(180, 320, 180, 180);
        line6=new Line(180, 320, 180, 180);
        line7=new Line(180, 320, 180, 180);
        line8=new Line(180, 320, 180, 180);
        corner1=new Line(180,180,180,180);
        corner2=new Line(180,320,180,320);
        corner3=new Line(320,180,320,180);
        corner4=new Line(320,320,320,320);
        corner5=new Line(320,320,320,320);
        corner6=new Line(320,320,320,320);
        corner7=new Line(320,320,320,320);
        corner8=new Line(320,320,320,320);
        corner1.setStroke(Paint.valueOf("RED"));
        corner2.setStroke(Paint.valueOf("YELLOW"));
        corner3.setStroke(Paint.valueOf("BLUE"));
        corner4.setStroke(Paint.valueOf("GREEN"));
        corner1.setStrokeWidth(20);
        corner2.setStrokeWidth(20);
        corner3.setStrokeWidth(20);
        corner4.setStrokeWidth(20);
        corner5.setStroke(Paint.valueOf("GREEN"));
        corner6.setStroke(Paint.valueOf("YELLOW"));
        corner7.setStroke(Paint.valueOf("BLUE"));
        corner8.setStroke(Paint.valueOf("RED"));
        corner5.setStrokeWidth(20);
        corner6.setStrokeWidth(20);
        corner7.setStrokeWidth(20);
        corner8.setStrokeWidth(20);
        line1.setStroke(Paint.valueOf("RED"));
        line2.setStroke(Paint.valueOf("BLUE"));
        line3.setStroke(Paint.valueOf("GREEN"));
        line4.setStroke(Paint.valueOf("YELLOW"));
        line1.setStrokeWidth(20);
        line2.setStrokeWidth(20);
        line3.setStrokeWidth(20);
        line4.setStrokeWidth(20);
        line5.setStroke(Paint.valueOf("GREEN"));
        line6.setStroke(Paint.valueOf("BLUE"));
        line7.setStroke(Paint.valueOf("RED"));
        line8.setStroke(Paint.valueOf("YELLOW"));
        line5.setStrokeWidth(20);
        line6.setStrokeWidth(20);
        line7.setStrokeWidth(20);
        line8.setStrokeWidth(20);
        c1=new Circle(250, 350, 10);
        c1.setFill(Paint.valueOf("YELLOW"));
        pane=new Pane();
        s1=new Scene(pane, 500, 600);
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        pane.getChildren().addAll(line1, line2, line3, line4, c1, b1, c2);
        pane.getChildren().addAll(line5, line6,line7,line8, c3);
//        pane.getChildren().addAll(corner8,corner7,corner6,corner1,corner5,corner4,corner2,corner3);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline2.play();
                timeline2.pause();
                timeline3.playFromStart();
            }
        });
        primaryStage.setScene(s1);
        primaryStage.show();
    }

    private void collision(ActionEvent actionEvent) {
        if(colliding()){
            if(!previously_colliding){
                previously_colliding=true;
                value++;
            }
        }
        else{
            previously_colliding=false;
        }
    }
    private boolean colliding(){
        return false;
    }
    private void do_step(ActionEvent actionEvent) {
        degree = (degree + 1) % 360;
        double pi=3.1415926535897932;
        double v1=Math.toRadians((degree));

        diag1.setStartX(180+80*Math.cos(v1));
        diag1.setStartY(250+80*Math.sin(v1));
        diag1.setEndX(180+80*Math.cos(v1+pi));
        diag1.setEndY(250+80*Math.sin(v1+pi));
        diag2.setStartX(180+80*Math.cos(v1-pi/2));
        diag2.setStartY(250+80*Math.sin(v1-pi/2));
        diag2.setEndX(180+80*Math.cos(v1+pi/2));
        diag2.setEndY(250+80*Math.sin(v1+pi/2));

        double degree1=(360-degree)%360;
        v1=Math.toRadians(degree1);
        diag3.setStartX(320+80*Math.cos(v1));
        diag3.setStartY(250+80*Math.sin(v1));
        diag3.setEndX(320+80*Math.cos(v1+pi));
        diag3.setEndY(250+80*Math.sin(v1+pi));
        diag4.setStartX(320+80*Math.cos(v1-pi/2));
        diag4.setStartY(250+80*Math.sin(v1-pi/2));
        diag4.setEndX(320+80*Math.cos(v1+pi/2));
        diag4.setEndY(250+80*Math.sin(v1+pi/2));




        change_coordintate(diag1.getStartX(), diag1.getStartY(), diag1.getStartX(), diag1.getStartY(), corner1);
        change_coordintate(diag1.getEndX(), diag1.getEndY(), diag1.getEndX(), diag1.getEndY(), corner4);
        change_coordintate(diag2.getStartX(), diag2.getStartY(), diag2.getStartX(), diag2.getStartY(), corner2);
        change_coordintate(diag2.getEndX(), diag2.getEndY(), diag2.getEndX(), diag2.getEndY(), corner3);


        change_coordintate(diag3.getStartX(), diag3.getStartY(), diag3.getStartX(), diag3.getStartY(), corner5);
        change_coordintate(diag3.getEndX(), diag3.getEndY(), diag3.getEndX(), diag3.getEndY(), corner8);
        change_coordintate(diag4.getStartX(), diag4.getStartY(), diag4.getStartX(), diag4.getStartY(), corner6);
        change_coordintate(diag4.getEndX(), diag4.getEndY(), diag4.getEndX(), diag4.getEndY(), corner7);


        change_coordintate(corner1.getEndX(), corner1.getEndY(), 180,250, line1);
        change_coordintate(corner3.getEndX(), corner3.getEndY(), 180,250, line2);
        change_coordintate(corner4.getEndX(), corner4.getEndY(), 180,250, line3);
        change_coordintate(corner2.getEndX(), corner2.getEndY(), 180,250, line4);


        change_coordintate(corner5.getEndX(), corner5.getEndY(), 320,250, line5);
        change_coordintate(corner7.getEndX(), corner7.getEndY(), 320,250, line6);
        change_coordintate(corner8.getEndX(), corner8.getEndY(), 320,250, line7);
        change_coordintate(corner6.getEndX(), corner6.getEndY(), 320,250, line8);
    }
    private void changeY(ActionEvent actionEvent){
        change_position(1);
    }
    private void changeY1(ActionEvent actionEvent){
        change_position(-2);
    }

    private void change_position(int p){
        c1.setCenterY(c1.getCenterY()+p);
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void change_coordintate(double a, double b, double c, double d, Line n1){
        n1.setStartX(a);
        n1.setStartY(b);
        n1.setEndX(c);
        n1.setEndY(d);
    }

}
