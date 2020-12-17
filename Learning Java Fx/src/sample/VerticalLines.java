package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

public class VerticalLines extends ObstacleClass implements Collidable{
    private double y;
    private int f;
    private double x_changes1;
    private double x_changes2;
    transient private ArrayList<Line> lines;
    transient private ArrayList<Color> colors;
    transient private Timeline collision;
    public VerticalLines(double y, PlayerClass player){
        super(player, player.getGame());
        this.y=y;
        this.f=0;
        this.x_changes1=0;
        this.x_changes2=0;
        timeline=new Timeline(new KeyFrame(Duration.millis(game.getTime()*16), this::move_obstacle));
        timeline.setCycleCount(-1);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        lines=new ArrayList<Line>();
        lines.add(new Line(50,y,50,y+100));
        lines.add(new Line(90,y+25,90,y+75));
        lines.add(new Line(150,y+15,150,y+85));
        lines.add(new Line(190,y+30,190,y+70));
        lines.add(new Line(250,y,250,y+100));
        lines.add(new Line(290,y+25,290,y+75));
        lines.add(new Line(350,y+15,350,y+85));
        lines.add(new Line(390,y+30,390,y+70));
        lines.get(0).setStrokeWidth(18);
        lines.get(4).setStrokeWidth(18);
        lines.get(1).setStrokeWidth(10);
        lines.get(5).setStrokeWidth(10);
        lines.get(2).setStrokeWidth(13);
        lines.get(6).setStrokeWidth(13);
        lines.get(3).setStrokeWidth(7);
        lines.get(7).setStrokeWidth(7);
        lines.get(0).setStroke(Paint.valueOf("YELLOW"));
        lines.get(1).setStroke(Paint.valueOf("YELLOW"));
        lines.get(2).setStroke(Paint.valueOf("RED"));
        lines.get(3).setStroke(Paint.valueOf("RED"));
        lines.get(4).setStroke(Paint.valueOf("BLUE"));
        lines.get(5).setStroke(Paint.valueOf("BLUE"));
        lines.get(6).setStroke(Paint.valueOf("GREEN"));
        lines.get(7).setStroke(Paint.valueOf("GREEN"));
    }


    @Override
    public void move_obstacle(ActionEvent event) {
        if(lines.get(4).getFill()!=player.getBall().getFill()){
            ArrayList<Line> one=new ArrayList<Line>();
            one.add(lines.get(0)); one.add(lines.get(2)); one.add(lines.get(6));
            for(int i=0;i<3;i++){
                if(one.get(i).getStroke()==player.getBall().getFill()) {
                    one.get(i).setStroke(lines.get(4).getStroke());
                    lines.get(4).setStroke(player.getBall().getFill());
                }
            }
            lines.get(1).setStroke(lines.get(0).getStroke());
            lines.get(3).setStroke(lines.get(2).getStroke());
            lines.get(5).setStroke(lines.get(4).getStroke());
            lines.get(7).setStroke(lines.get(6).getStroke());
        }
        if(lines.get(6).getStartX()==220){
            this.f=1;
        }else if(lines.get(6).getStartX()==380){
            this.f=0;
        }
        if(f==0){
            x_changes1--;
            x_changes2++;
            for(int i=0;i<8;i++){
                if(i%2==0) {
                    lines.get(i).setStartX(lines.get(i).getStartX() - 1);
                    lines.get(i).setEndX(lines.get(i).getEndX() - 1);
                }else{
                    lines.get(i).setStartX(lines.get(i).getStartX() + 1);
                    lines.get(i).setEndX(lines.get(i).getEndX() + 1);
                }
            }
        }else{
            x_changes1++;
            x_changes2--;
            for(int i=0;i<8;i++){
                if(i%2==0) {
                    lines.get(i).setStartX(lines.get(i).getStartX() + 1);
                    lines.get(i).setEndX(lines.get(i).getEndX() + 1);
                }else{
                    lines.get(i).setStartX(lines.get(i).getStartX() - 1);
                    lines.get(i).setEndX(lines.get(i).getEndX() - 1);
                }
            }

        }
    }

    @Override
    public void detect_collision(ActionEvent event) {
        this.check_crossed();
        if(player.getBall().getFill()==Paint.valueOf("WHITE"))
            return;
        Circle ball=player.getBall();
        for(int i=0;i<8;i++){
            Shape intersection=Shape.intersect(ball, lines.get(i));
            if(intersection.getBoundsInLocal().getWidth()!=-1 && lines.get(i).getStroke()!=ball.getFill()){
                System.out.println("Game over!! Collision Detected");
                player.stopMoving();
                timeline.pause();
                collision.pause();
                game.endGame();
                break;
            }
        }
    }

        @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void check_crossed() {
        if(player.getBall().getCenterY()<y-10){
            if(!left_behind){
                left_behind=true;
                System.out.println("Vertical Lines crossed");
                game.addObstaclesCrossed();

            }
        }
    }

    @Override
    public void remove_obstacle(Pane pane) {
        for(int i=0;i<8;i++)
            pane.getChildren().remove(lines.get(i));
        timeline.stop();
        collision.stop();
    }


        @Override
    public void add_obstacle(Pane pane) {
        for(int i=0;i<8;i++)
            pane.getChildren().add(lines.get(i));
    }

    public void start_moving(){
        timeline.play();
        collision.play();
    }

        @Override
    public void moveDown(double a) {
        y=y+a;
    }

        @Override
    public void stopMoving(){
        timeline.pause();
        collision.pause();
    }

    @Override
    public void initialize(ObstacleClass obs, PlayerClass player){
        this.player=player;
        this.game=player.getGame();
        timeline=new Timeline(new KeyFrame(Duration.millis(game.getTime()*16), this::move_obstacle));
        timeline.setCycleCount(-1);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        lines=new ArrayList<Line>();
        lines.add(new Line(50+x_changes1,y,50+x_changes1,y+100));
        lines.add(new Line(90+x_changes2,y+25,90+x_changes2,y+75));
        lines.add(new Line(150+x_changes1,y+15,150+x_changes1,y+85));
        lines.add(new Line(190+x_changes2,y+30,190+x_changes2,y+70));
        lines.add(new Line(250+x_changes1,y,250+x_changes1,y+100));
        lines.add(new Line(290+x_changes2,y+25,290+x_changes2,y+75));
        lines.add(new Line(350+x_changes1,y+15,350+x_changes1,y+85));
        lines.add(new Line(390+x_changes2,y+30,390+x_changes2,y+70));
        lines.get(0).setStrokeWidth(18);
        lines.get(1).setStrokeWidth(10);
        lines.get(2).setStrokeWidth(13);
        lines.get(3).setStrokeWidth(7);
        lines.get(4).setStrokeWidth(18);
        lines.get(5).setStrokeWidth(10);
        lines.get(6).setStrokeWidth(13);
        lines.get(7).setStrokeWidth(7);
        lines.get(0).setStroke(Paint.valueOf("YELLOW"));
        lines.get(1).setStroke(Paint.valueOf("YELLOW"));
        lines.get(2).setStroke(Paint.valueOf("RED"));
        lines.get(3).setStroke(Paint.valueOf("RED"));
        lines.get(4).setStroke(Paint.valueOf("BLUE"));
        lines.get(5).setStroke(Paint.valueOf("BLUE"));
        lines.get(6).setStroke(Paint.valueOf("GREEN"));
        lines.get(7).setStroke(Paint.valueOf("GREEN"));
        move_obstacle(new ActionEvent());
    }
}