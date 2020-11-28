package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainClass extends Application{
    static DataClass dataClass;
    static Controller c1;
    static String datafile;
    public static void main(String args[]){
        serializeData();
        launch(args);
    }
    public static void serializeData(){
        dataClass=null;
        ObjectInputStream in=null;
        try{
            in=new ObjectInputStream(new FileInputStream("DataClass_Data/data.txt"));
            dataClass=(DataClass)(in.readObject());
            in.close();
        } catch (Exception e){
            System.out.println("No data saved till now");
            dataClass=new DataClass();
        } finally {
        }
        if(dataClass==null){
            dataClass=new DataClass();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(event -> {
            dataClass.serialize();
        });
        c1=new Controller(dataClass, primaryStage);
        c1.runController();
    }
}

