package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application{
    static DataClass dataClass;
    static Controller c1;
    static String datafile;
    public static void main(String args[]){
        serializeData();
        launch(args);
    }
    public static void serializeData(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        c1=new Controller(dataClass, primaryStage);
        c1.runController();
    }
}
