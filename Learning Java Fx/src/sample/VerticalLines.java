package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;

public class VerticalLines extends ObstacleClass{
    private double y;
    private int f;
    private double x_changes1;
    private double x_changes2;
    transient private Line v1,v2,v3,v4,v5,v6,v7,v8;
    public VerticalLines(double y, PlayerClass player){
        super(player);
        this.y=y;
        this.f=0;
        this.x_changes1=0;
        this.x_changes2=0;
        timeline=new Timeline(new KeyFrame(Duration.millis(16), this::move_obstacle));
        timeline.setCycleCount(-1);
        v1=new Line(50,y,50,y+100);
        v2=new Line(90,y+25,90,y+75);
        v3=new Line(150,y+15,150,y+85);
        v4=new Line(190,y+30,190,y+70);
        v5=new Line(250,y,250,y+100);
        v6=new Line(290,y+25,290,y+75);
        v7=new Line(350,y+15,350,y+85);
        v8=new Line(390,y+30,390,y+70);
        v1.setStrokeWidth(18);
        v2.setStrokeWidth(10);
        v3.setStrokeWidth(13);
        v4.setStrokeWidth(7);
        v5.setStrokeWidth(18);
        v6.setStrokeWidth(10);
        v7.setStrokeWidth(13);
        v8.setStrokeWidth(7);
        v1.setStroke(Paint.valueOf("YELLOW"));
        v2.setStroke(Paint.valueOf("YELLOW"));
        v3.setStroke(Paint.valueOf("RED"));
        v4.setStroke(Paint.valueOf("RED"));
        v5.setStroke(Paint.valueOf("BLUE"));
        v6.setStroke(Paint.valueOf("BLUE"));
        v7.setStroke(Paint.valueOf("GREEN"));
        v8.setStroke(Paint.valueOf("GREEN"));
    }


    @Override
    public void move_obstacle(ActionEvent event) {
        if(v5.getFill()!=player.getBall().getFill()){
            ArrayList<Line> one=new ArrayList<Line>();
            one.add(v1); one.add(v3); one.add(v7);
            for(int i=0;i<3;i++){
                if(one.get(i).getStroke()==player.getBall().getFill()) {
                    one.get(i).setStroke(v5.getStroke());
                    v5.setStroke(player.getBall().getFill());
                }
            }
            v2.setStroke(v1.getStroke());
            v4.setStroke(v3.getStroke());
            v6.setStroke(v5.getStroke());
            v8.setStroke(v7.getStroke());
        }
        if(v7.getStartX()==220){
            this.f=1;
        }else if(v7.getStartX()==380){
            this.f=0;
        }
        if(f==0){
            x_changes1--;
            v1.setStartX(v1.getStartX()-1);
            v1.setEndX(v1.getEndX()-1);
            v3.setStartX(v3.getStartX()-1);
            v3.setEndX(v3.getEndX()-1);
            v5.setStartX(v5.getStartX()-1);
            v5.setEndX(v5.getEndX()-1);
            v7.setStartX(v7.getStartX()-1);
            v7.setEndX(v7.getEndX()-1);
            x_changes2++;
            v2.setStartX(v2.getStartX()+1);
            v2.setEndX(v2.getEndX()+1);
            v4.setStartX(v4.getStartX()+1);
            v4.setEndX(v4.getEndX()+1);
            v6.setStartX(v6.getStartX()+1);
            v6.setEndX(v6.getEndX()+1);
            v8.setStartX(v8.getStartX()+1);
            v8.setEndX(v8.getEndX()+1);
        }else{
            x_changes1++;
            v1.setStartX(v1.getStartX()+1);
            v1.setEndX(v1.getEndX()+1);
            v3.setStartX(v3.getStartX()+1);
            v3.setEndX(v3.getEndX()+1);
            v5.setStartX(v5.getStartX()+1);
            v5.setEndX(v5.getEndX()+1);
            v7.setStartX(v7.getStartX()+1);
            v7.setEndX(v7.getEndX()+1);
            x_changes2--;
            v2.setStartX(v2.getStartX()-1);
            v2.setEndX(v2.getEndX()-1);
            v4.setStartX(v4.getStartX()-1);
            v4.setEndX(v4.getEndX()-1);
            v6.setStartX(v6.getStartX()-1);
            v6.setEndX(v6.getEndX()-1);
            v8.setStartX(v8.getStartX()-1);
            v8.setEndX(v8.getEndX()-1);
        }
    }

    @Override
    public void detect_collision(PlayerClass player) {

    }

        @Override
    public double getY(){
        return this.y;
    }

        @Override
    public void remove_obstacle(Pane pane) {
        pane.getChildren().removeAll(v1,v2,v3,v4,v5,v6,v7,v8);
    }


        @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(v1,v2,v3,v4,v5,v6,v7,v8);
    }

    public void start_moving(){
        timeline.play();
    }

        @Override
    public void moveDown(double a) {
        y=y+a;
    }

        @Override
    public void stopMoving(){
        timeline.pause();
    }

    @Override
    public void initialize(ObstacleClass obs, PlayerClass player){
        this.player=player;
        timeline=new Timeline(new KeyFrame(Duration.millis(20), this::move_obstacle));
        timeline.setCycleCount(-1);
        v1=new Line(50+x_changes1,y,50+x_changes1,y+100);
        v2=new Line(90+x_changes2,y+25,90+x_changes2,y+75);
        v3=new Line(150+x_changes1,y+15,150+x_changes1,y+85);
        v4=new Line(190+x_changes2,y+30,190+x_changes2,y+70);
        v5=new Line(250+x_changes1,y,250+x_changes1,y+100);
        v6=new Line(290+x_changes2,y+25,290+x_changes2,y+75);
        v7=new Line(350+x_changes1,y+15,350+x_changes1,y+85);
        v8=new Line(390+x_changes2,y+30,390+x_changes2,y+70);
        v1.setStrokeWidth(18);
        v2.setStrokeWidth(10);
        v3.setStrokeWidth(13);
        v4.setStrokeWidth(7);
        v5.setStrokeWidth(18);
        v6.setStrokeWidth(10);
        v7.setStrokeWidth(13);
        v8.setStrokeWidth(7);
        v1.setStroke(Paint.valueOf("YELLOW"));
        v2.setStroke(Paint.valueOf("YELLOW"));
        v3.setStroke(Paint.valueOf("RED"));
        v4.setStroke(Paint.valueOf("RED"));
        v5.setStroke(Paint.valueOf("BLUE"));
        v6.setStroke(Paint.valueOf("BLUE"));
        v7.setStroke(Paint.valueOf("GREEN"));
        v8.setStroke(Paint.valueOf("GREEN"));
        move_obstacle(new ActionEvent());
    }
}