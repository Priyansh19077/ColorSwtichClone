package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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

import java.util.Random;

import static java.lang.Math.*;

public class Main extends Application{
    int value=0;
    private double degree=0;
    private Timeline timeline1, timeline2, timeline3;
    private Label l1;
    private Pane pane;
    private Scene s1;
    Rectangle r1;
    Circle c1;
    Button b1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.timeline1=new Timeline(new KeyFrame(Duration.millis(20), this::do_step));
        this.timeline2=new Timeline(new KeyFrame(Duration.millis(10), this::changeY));
        this.timeline3=new Timeline(new KeyFrame(Duration.millis(15), this::changeY1));
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
        b1=new Button("Click Me");
        b1.setLayoutX(10);
        b1.setLayoutY(10);
        r1=new Rectangle(190,100,120,120);
        r1.setFill(null);
        r1.setStrokeWidth(10);
        c1=new Circle(250, 350, 10);
        c1.setFill(Paint.valueOf("BLACK"));
        r1.setStroke(Paint.valueOf("BLACK"));
        pane=new Pane();
        s1=new Scene(pane, 500, 500);
        pane.getChildren().add(r1);
        pane.getChildren().add(c1);
        pane.getChildren().add(b1);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline2.pause();
                timeline3.playFromStart();
            }
        });
        primaryStage.setScene(s1);
        primaryStage.show();
    }
    private void do_step(ActionEvent actionEvent){
        degree=(degree+1)%360;
        r1.setRotate(degree);
    }
    private void changeY(ActionEvent actionEvent){
        c1.setCenterY(c1.getCenterY()+1);
    }
    private void changeY1(ActionEvent actionEvent){
        c1.setCenterY(c1.getCenterY()-2);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
