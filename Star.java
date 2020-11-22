package sample;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
//    private static Player p
    private static int points;
    private static double x,y;
    private static boolean colliding;
    private static Timeline t;
    private static Image i;
    private static ImageView iv;

//    public Main(double x,double y){
//        this.x=x;
//        this.y=y;
//    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addScore(){

    }

    public void removeStar(){

    }

    public boolean isColliding() {
        return colliding;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane=new Pane();

        try {
            i = new Image(new FileInputStream("Media/Star3.png"));
            ImageView view = new ImageView(i);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        iv=new ImageView(i);
        iv.setFitHeight(50);
        iv.setFitWidth(70);
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        pane.getChildren().addAll(iv);
        Scene s1=new Scene(pane, 500, 600);
        primaryStage.setScene(s1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}