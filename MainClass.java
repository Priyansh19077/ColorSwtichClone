package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainClass extends Application {
    @Override
    public void start(Stage primaryStage){
        Pane pane=new Pane();
        GameClass g1=new GameClass(pane);
        Scene s1=new Scene(pane, 500, 620, Color.BLACK);
        primaryStage.setScene(s1);
        g1.addNodes();
        BackgroundFill bf=new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg=new Background(bf);
        pane.setBackground(bg);
        primaryStage.show();
        g1.startGame();
    }
    public static void main(String args[]){
        launch(args);
    }
}
