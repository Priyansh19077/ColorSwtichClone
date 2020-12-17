package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ColorChangerClass implements Serializable {
    private double x;
    transient private ArrayList<Color> colors;
    private double y;
    private PlayerClass player;
    transient Timeline t1;
    transient Pane pane;
    public boolean hidden;
    transient Arc a1, a2, a3, a4;
    int degree=0;
    public ColorChangerClass(double x, double y, PlayerClass player, Pane pane){
        this.x=x;
        this.y=y;
        this.hidden=false;
        this.player=player;
        this.pane=pane;
        colors=new ArrayList<Color>();
        colors.add((Color) Paint.valueOf("BLUE"));
        colors.add((Color) Paint.valueOf("GREEN"));
        colors.add((Color) Paint.valueOf("RED"));
        colors.add((Color) Paint.valueOf("YELLOW"));
        this.x=x;
        this.y=y;
        t1=new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Math.abs(player.getBall().getCenterY()-y)<=18){
                    int p=getAColor();
                    player.setIndex(p);
                    player.getBall().setFill(colors.get(p));
                    player.setSafeState(true);
                    hidden=true;
                    removeColorChanger(pane);
                    t1.stop();
                    player.getGame().addColorChangersCrossed();
                }
                degree = (degree - 7) % 360;
                a1.setStartAngle(degree);
                a2.setStartAngle((degree+90)%360);
                a3.setStartAngle((degree+180)%360);
                a4.setStartAngle((degree+270)%360);
            }
        }));
        t1.setCycleCount(-1);
//        t1.play();
        int length=35;
        a1 = new Arc(x, y, length/2, length/2, 0, 90);
        a2 = new Arc(x, y, length/2, length/2, 90, 90);
        a3 = new Arc(x, y, length/2, length/2, 180, 90);
        a4 = new Arc(x, y, length/2, length/2, 270, 90);
        a1.setType(ArcType.ROUND);
        a1.setFill(Paint.valueOf("RED"));
        a2.setType(ArcType.ROUND);
        a2.setFill(Paint.valueOf("BLUE"));
        a3.setType(ArcType.ROUND);
        a3.setFill(Paint.valueOf("GREEN"));
        a4.setType(ArcType.ROUND);
        a4.setFill(Paint.valueOf("YELLOW"));
    }
    public void addColorChanger(Pane pane){
        if(!this.hidden)pane.getChildren().addAll(a1, a2, a3, a4);
    }
    public void removeColorChanger(Pane pane){
        pane.getChildren().removeAll(a1, a2, a3, a4);
        t1.stop();
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public boolean isColliding(){
        return false;
    }
    public int getAColor(){
        Color c1= (Color) player.getBall().getFill();
        Random rand=new Random();
        int p=rand.nextInt(4);
        while(colors.get(p)==c1){
            p=rand.nextInt(4);
        }
        return p;
    }
    public void initialize(ColorChangerClass c1, Pane pane, PlayerClass player){
        this.pane=pane;
        this.player=player;
        colors=new ArrayList<Color>();
        colors.add((Color) Paint.valueOf("BLUE"));
        colors.add((Color) Paint.valueOf("GREEN"));
        colors.add((Color) Paint.valueOf("RED"));
        colors.add((Color) Paint.valueOf("YELLOW"));
        t1=new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Math.abs(player.getBall().getCenterY()-y)<=18){
                    int p=getAColor();
                    player.setIndex(p);
                    player.getBall().setFill(colors.get(p));
                    hidden=true;
                    removeColorChanger(pane);
                    t1.stop();
                    player.getGame().addColorChangersCrossed();
                }
                degree = (degree - 7) % 360;
                a1.setStartAngle(degree);
                a2.setStartAngle((degree+90)%360);
                a3.setStartAngle((degree+180)%360);
                a4.setStartAngle((degree+270)%360);
            }
        }));
        t1.setCycleCount(-1);
//        if(!this.hidden)
//        t1.play();
        int length=35;
        a1 = new Arc(x, y, length/2, length/2, 0, 90);
        a2 = new Arc(x, y, length/2, length/2, 90, 90);
        a3 = new Arc(x, y, length/2, length/2, 180, 90);
        a4 = new Arc(x, y, length/2, length/2, 270, 90);
        a1.setType(ArcType.ROUND);
        a1.setFill(Paint.valueOf("RED"));
        a2.setType(ArcType.ROUND);
        a2.setFill(Paint.valueOf("BLUE"));
        a3.setType(ArcType.ROUND);
        a3.setFill(Paint.valueOf("GREEN"));
        a4.setType(ArcType.ROUND);
        a4.setFill(Paint.valueOf("YELLOW"));
    }

    public void startMoving(){
        if(!this.hidden)
        {
            t1.setCycleCount(-1);
            t1.playFromStart();
        }
    }
    public void stopMoving(){
        t1.stop();
    }
}
