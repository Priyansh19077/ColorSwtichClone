package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class HorizontalLine extends ObstacleClass{
    private double y;
    private double y1;
    private double x_changes1;
    private double x_changes2;
    transient Line v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16;
    public HorizontalLine(double y4, PlayerClass player){
        super(player);
        this.y=y4+15;
        this.y1=-135;
        this.x_changes1=0;
        this.x_changes2=0;
        timeline=new Timeline(new KeyFrame(Duration.millis(5),this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        v1=new Line(8,y,123,y);
        v2=new Line(131,y,246,y);
        v3=new Line(254,y,369,y);
        v4=new Line(377,y,492,y);
        v5=new Line(-492,y,-377,y);
        v6=new Line(-369,y,-254,y);
        v7=new Line(-246,y,-131,y);
        v8=new Line(-123,y,-8,y);
        v9=new Line(8,y+y1,123,y+y1);
        v10=new Line(131,y+y1,246,y+y1);
        v11=new Line(254,y+y1,369,y+y1);
        v12=new Line(377,y+y1,492,y+y1);
        v13=new Line(500,y+y1,615,y+y1);
        v14=new Line(623,y+y1,738,y+y1);
        v15=new Line(746,y+y1,861,y+y1);
        v16=new Line(869,y+y1,984,y+y1);


        v1.setStrokeWidth(12);
        v2.setStrokeWidth(12);
        v3.setStrokeWidth(12);
        v4.setStrokeWidth(12);
        v5.setStrokeWidth(12);
        v6.setStrokeWidth(12);
        v7.setStrokeWidth(12);
        v8.setStrokeWidth(12);
        v9.setStrokeWidth(12);
        v10.setStrokeWidth(12);
        v11.setStrokeWidth(12);
        v12.setStrokeWidth(12);
        v13.setStrokeWidth(12);
        v14.setStrokeWidth(12);
        v15.setStrokeWidth(12);
        v16.setStrokeWidth(12);
        v1.setStroke(Paint.valueOf("RED"));
        v12.setStroke(Paint.valueOf("RED"));
        v2.setStroke(Paint.valueOf("GREEN"));
        v11.setStroke(Paint.valueOf("GREEN"));
        v3.setStroke(Paint.valueOf("BLUE"));
        v10.setStroke(Paint.valueOf("BLUE"));
        v4.setStroke(Paint.valueOf("YELLOW"));
        v9.setStroke(Paint.valueOf("YELLOW"));
        v5.setStroke(Paint.valueOf("RED"));
        v16.setStroke(Paint.valueOf("RED"));
        v6.setStroke(Paint.valueOf("GREEN"));
        v15.setStroke(Paint.valueOf("GREEN"));
        v7.setStroke(Paint.valueOf("BLUE"));
        v14.setStroke(Paint.valueOf("BLUE"));
        v8.setStroke(Paint.valueOf("YELLOW"));
        v13.setStroke(Paint.valueOf("YELLOW"));
    }
    @Override
    public void move_obstacle(ActionEvent event) {
        if(v1.getStartX()>=500){
            v1.setStartX(-492);
            v1.setEndX(-377);
        }
        if(v2.getStartX()>=500){
            v2.setStartX(-492);
            v2.setEndX(-377);
        }
        if(v3.getStartX()>=500){
            v3.setStartX(-492);
            v3.setEndX(-377);
        }
        if(v4.getStartX()>=500){
            v4.setStartX(-492);
            v4.setEndX(-377);
        }
        if(v5.getStartX()>=500){
            v5.setStartX(-492);
            v5.setEndX(-377);
        }
        if(v6.getStartX()>=500){
            v6.setStartX(-492);
            v6.setEndX(-377);
        }
        if(v7.getStartX()>=500){
            v7.setStartX(-492);
            v7.setEndX(-377);
        }
        if(v8.getStartX()>=500){
            v8.setStartX(-492);
            v8.setEndX(-377);
        }
        if(v9.getEndX()<=0){
            v9.setStartX(869);
            v9.setEndX(984);
        }
        if(v10.getEndX()<=0){
            v10.setStartX(869);
            v10.setEndX(984);
        }
        if(v11.getEndX()<=0){
            v11.setStartX(869);
            v11.setEndX(984);
        }
        if(v12.getEndX()<=0){
            v12.setStartX(869);
            v12.setEndX(984);
        }
        if(v13.getEndX()<=0){
            v13.setStartX(869);
            v13.setEndX(984);
        }
        if(v14.getEndX()<=0){
            v14.setStartX(869);
            v14.setEndX(984);
        }
        if(v15.getEndX()<=0){
            v15.setStartX(869);
            v15.setEndX(984);
        }
        if(v16.getEndX()<=0){
            v16.setStartX(869);
            v16.setEndX(984);
        }
//        x_changes1++;
        v1.setStartX(v1.getStartX()+1);
        v1.setEndX(v1.getEndX()+1);
        v2.setStartX(v2.getStartX()+1);
        v2.setEndX(v2.getEndX()+1);
        v3.setStartX(v3.getStartX()+1);
        v3.setEndX(v3.getEndX()+1);
        v4.setStartX(v4.getStartX()+1);
        v4.setEndX(v4.getEndX()+1);
        v5.setStartX(v5.getStartX()+1);
        v5.setEndX(v5.getEndX()+1);
        v6.setStartX(v6.getStartX()+1);
        v6.setEndX(v6.getEndX()+1);
        v7.setStartX(v7.getStartX()+1);
        v7.setEndX(v7.getEndX()+1);
        v8.setStartX(v8.getStartX()+1);
        v8.setEndX(v8.getEndX()+1);
//        x_changes2--;
        v9.setStartX(v9.getStartX()-1);
        v9.setEndX(v9.getEndX()-1);
        v10.setStartX(v10.getStartX()-1);
        v10.setEndX(v10.getEndX()-1);
        v11.setStartX(v11.getStartX()-1);
        v11.setEndX(v11.getEndX()-1);
        v12.setStartX(v12.getStartX()-1);
        v12.setEndX(v12.getEndX()-1);
        v13.setStartX(v13.getStartX()-1);
        v13.setEndX(v13.getEndX()-1);
        v14.setStartX(v14.getStartX()-1);
        v14.setEndX(v14.getEndX()-1);
        v15.setStartX(v15.getStartX()-1);
        v15.setEndX(v15.getEndX()-1);
        v16.setStartX(v16.getStartX()-1);
        v16.setEndX(v16.getEndX()-1);
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
            pane.getChildren().removeAll(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16);
    }


        @Override
    public void add_obstacle(Pane pane) {
        pane.getChildren().addAll(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16);
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
        timeline=new Timeline(new KeyFrame(Duration.millis(5),this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        v1=new Line(8,y,123,y);
        v2=new Line(131,y,246,y);
        v3=new Line(254,y,369,y);
        v4=new Line(377,y,492,y);
        v5=new Line(-492,y,-377,y);
        v6=new Line(-369,y,-254,y);
        v7=new Line(-246,y,-131,y);
        v8=new Line(-123,y,-8,y);
        v9=new Line(8,y+y1,123,y+y1);
        v10=new Line(131,y+y1,246,y+y1);
        v11=new Line(254,y+y1,369,y+y1);
        v12=new Line(377,y+y1,492,y+y1);
        v13=new Line(500,y+y1,615,y+y1);
        v14=new Line(623,y+y1,738,y+y1);
        v15=new Line(746,y+y1,861,y+y1);
        v16=new Line(869,y+y1,984,y+y1);

        v1.setStrokeWidth(12);
        v2.setStrokeWidth(12);
        v3.setStrokeWidth(12);
        v4.setStrokeWidth(12);
        v5.setStrokeWidth(12);
        v6.setStrokeWidth(12);
        v7.setStrokeWidth(12);
        v8.setStrokeWidth(12);
        v9.setStrokeWidth(12);
        v10.setStrokeWidth(12);
        v11.setStrokeWidth(12);
        v12.setStrokeWidth(12);
        v13.setStrokeWidth(12);
        v14.setStrokeWidth(12);
        v15.setStrokeWidth(12);
        v16.setStrokeWidth(12);
        v1.setStroke(Paint.valueOf("RED"));
        v12.setStroke(Paint.valueOf("RED"));
        v2.setStroke(Paint.valueOf("GREEN"));
        v11.setStroke(Paint.valueOf("GREEN"));
        v3.setStroke(Paint.valueOf("BLUE"));
        v10.setStroke(Paint.valueOf("BLUE"));
        v4.setStroke(Paint.valueOf("YELLOW"));
        v9.setStroke(Paint.valueOf("YELLOW"));
        v5.setStroke(Paint.valueOf("RED"));
        v16.setStroke(Paint.valueOf("RED"));
        v6.setStroke(Paint.valueOf("GREEN"));
        v15.setStroke(Paint.valueOf("GREEN"));
        v7.setStroke(Paint.valueOf("BLUE"));
        v14.setStroke(Paint.valueOf("BLUE"));
        v8.setStroke(Paint.valueOf("YELLOW"));
        v13.setStroke(Paint.valueOf("YELLOW"));
        move_obstacle(new ActionEvent());
    }
}