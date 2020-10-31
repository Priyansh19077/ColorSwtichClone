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
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.event.ActionEvent;

import java.util.Random;

import static java.lang.Math.*;

public class Colored_Arc_Moving extends Application{
    private double degree=0;
    private boolean previously_colliding=false;
    int value=0;
    private Timeline timeline1, timeline2, timeline3, timeline4;
    private Pane pane;
    private Scene s1;
    private Arc a1,a2, a3, a4;
    Circle c1;
    Button b1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.timeline1=new Timeline(new KeyFrame(Duration.millis(15), this::do_step));
        this.timeline2=new Timeline(new KeyFrame(Duration.millis(8), this::changeY));
        this.timeline3=new Timeline(new KeyFrame(Duration.millis(14), this::changeY1));
        this.timeline4=new Timeline(new KeyFrame(Duration.millis(8), this::collision));
        this.timeline3.setCycleCount(25);
        this.timeline1.setCycleCount(Timeline.INDEFINITE);
        this.timeline2.setCycleCount(Timeline.INDEFINITE);
        this.timeline2.play();
        this.timeline1.play();
        double pi=3.1415926535897932;
        a1 = new Arc(250, 250, 80, 80, 0, (float)2*pi*80/6.62);
        a2 = new Arc(250, 250, 80, 80, 0, (float)2*pi*80/6.62);
        a3 = new Arc(250, 250, 80, 80, 0, (float)2*pi*80/6.62);
        a4 = new Arc(250, 250, 80, 80, 0, (float)2*pi*80/6.62);
        a1.setType(ArcType.OPEN);
        a1.setFill(null);
        a1.setStroke(Paint.valueOf("RED"));
        a1.setStrokeWidth(20);
        a2.setType(ArcType.OPEN);
        a2.setFill(null);
        a2.setStroke(Paint.valueOf("BLUE"));
        a2.setStrokeWidth(20);
        a3.setType(ArcType.OPEN);
        a3.setFill(null);
        a3.setStroke(Paint.valueOf("GREEN"));
        a3.setStrokeWidth(20);
        a4.setType(ArcType.OPEN);
        a4.setFill(null);
        a4.setStroke(Paint.valueOf("YELLOW"));
        a4.setStrokeWidth(20);
        this.timeline3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline2.play();
                timeline3.playFromStart();
                timeline3.pause();
            }
        });
        b1=new Button("");
        b1.setLayoutX(10);
        b1.setLayoutY(10);
        b1.resize(1,1);
        c1=new Circle(250, 350, 10);
        c1.setFill(Paint.valueOf("YELLOW"));
        pane=new Pane();
        s1=new Scene(pane, 500, 600);
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        pane.getChildren().addAll(c1, b1, a1,a2, a3, a4);
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
        a1.setStartAngle(degree);
        a2.setStartAngle((degree+90)%360);
        a3.setStartAngle((degree+180)%360);
        a4.setStartAngle((degree+270)%360);
        double pi=3.1415926535897932;
        double v1=Math.toRadians((degree));
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

}
