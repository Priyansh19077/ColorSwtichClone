package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;

public class HorizontalLine extends ObstacleClass implements Collidable{
    private double y;
    private double y1;
    transient private ArrayList<Line> lines;
    private ArrayList<Double> start_x_values;
    private ArrayList<Double> end_x_values;
    transient private ArrayList<Color> colors;
    transient private Timeline collision;
    public HorizontalLine(double y4, PlayerClass player){
        super(player, player.getGame());
        this.y=y4+25;
        this.y1=-145;
        timeline=new Timeline(new KeyFrame(Duration.millis(game.getTime()*5),this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        start_x_values=new ArrayList<Double>();
        end_x_values=new ArrayList<Double>();
        colors=new ArrayList<Color>();
        colors.add((Color)Paint.valueOf("RED"));
        colors.add((Color)Paint.valueOf("GREEN"));
        colors.add((Color)Paint.valueOf("BLUE"));
        colors.add((Color)Paint.valueOf("YELLOW"));
        lines=new ArrayList<Line>();
        lines.add(new Line(8,y,123,y));
        lines.add(new Line(131,y,246,y));
        lines.add(new Line(254,y,369,y));
        lines.add(new Line(377,y,492,y));
        lines.add(new Line(-492,y,-377,y));
        lines.add(new Line(-369,y,-254,y));
        lines.add(new Line(-246,y,-131,y));
        lines.add(new Line(-123,y,-8,y));
        lines.add(new Line(8,y+y1,123,y+y1));
        lines.add(new Line(131,y+y1,246,y+y1));
        lines.add(new Line(254,y+y1,369,y+y1));
        lines.add(new Line(377,y+y1,492,y+y1));
        lines.add(new Line(500,y+y1,615,y+y1));
        lines.add(new Line(623,y+y1,738,y+y1));
        lines.add(new Line(746,y+y1,861,y+y1));
        lines.add(new Line(869,y+y1,984,y+y1));
        for(int i=0;i<16;i++) {
            lines.get(i).setStrokeWidth(12);
            start_x_values.add(lines.get(i).getStartX());
            end_x_values.add(lines.get(i).getEndX());
        }
        for(int i=0;i<4;i++){
            lines.get(i).setStroke(colors.get(i));
            lines.get(i+4).setStroke(colors.get(i));
        }
        for(int i=0;i<4;i++){
            int p=i+8;
            lines.get(p).setStroke(colors.get((i-1+4)%4));
            lines.get(p+4).setStroke(colors.get((i-1+4)%4));
        }
        move_obstacle(new ActionEvent());
    }
    @Override
    public void move_obstacle(ActionEvent event) {
        if(lines.get(0).getStartX()>=500){
            lines.get(0).setStartX(-492);
            lines.get(0).setEndX(-377);
        }
        if(lines.get(1).getStartX()>=500){
            lines.get(1).setStartX(-492);
            lines.get(1).setEndX(-377);
        }
        if(lines.get(2).getStartX()>=500){
            lines.get(2).setStartX(-492);
            lines.get(2).setEndX(-377);
        }
        if(lines.get(3).getStartX()>=500){
            lines.get(3).setStartX(-492);
            lines.get(3).setEndX(-377);
        }
        if(lines.get(4).getStartX()>=500){
            lines.get(4).setStartX(-492);
            lines.get(4).setEndX(-377);
        }
        if(lines.get(5).getStartX()>=500){
            lines.get(5).setStartX(-492);
            lines.get(5).setEndX(-377);
        }
        if(lines.get(6).getStartX()>=500){
            lines.get(6).setStartX(-492);
            lines.get(6).setEndX(-377);
        }
        if(lines.get(7).getStartX()>=500){
            lines.get(7).setStartX(-492);
            lines.get(7).setEndX(-377);
        }
        if(lines.get(8).getEndX()<=0){
            lines.get(8).setStartX(869);
            lines.get(8).setEndX(984);
        }
        if(lines.get(9).getEndX()<=0){
            lines.get(9).setStartX(869);
            lines.get(9).setEndX(984);
        }
        if(lines.get(10).getEndX()<=0){
            lines.get(10).setStartX(869);
            lines.get(10).setEndX(984);
        }
        if(lines.get(11).getEndX()<=0){
            lines.get(11).setStartX(869);
            lines.get(11).setEndX(984);
        }
        if(lines.get(12).getEndX()<=0){
            lines.get(12).setStartX(869);
            lines.get(12).setEndX(984);
        }
        if(lines.get(13).getEndX()<=0){
            lines.get(13).setStartX(869);
            lines.get(13).setEndX(984);
        }
        if(lines.get(14).getEndX()<=0){
            lines.get(14).setStartX(869);
            lines.get(14).setEndX(984);
        }
        if(lines.get(15).getEndX()<=0){
            lines.get(15).setStartX(869);
            lines.get(15).setEndX(984);
        }

        for(int i=0;i<8;i++){
            lines.get(i).setStartX(lines.get(i).getStartX()+1);
            lines.get(i).setEndX(lines.get(i).getEndX()+1);
        }
        for(int i=0;i<8;i++){
            lines.get(i+8).setStartX(lines.get(i+8).getStartX()-1);
            lines.get(i+8).setEndX(lines.get(i+8).getEndX()-1);
        }
        for(int i=0;i<16;i++){
            start_x_values.set(i, lines.get(i).getStartX());
            end_x_values.set(i, lines.get(i).getEndX());
        }
    }

    @Override
    public void detect_collision(ActionEvent event) {
        this.check_crossed();
        if(player.getBall().getFill()==Paint.valueOf("WHITE"))
            return;
        Circle ball=player.getBall();
        for(int i=0;i<16;i++){
            Shape intersection=Shape.intersect(ball, lines.get(i));
            if(intersection.getBoundsInLocal().getWidth()!=-1 && lines.get(i).getStroke()!=ball.getFill()){
                System.out.println("Game over!! Collision Detected");
                for(int j=0;j<16;j++){
                    start_x_values.set(j, lines.get(j).getStartX());
                    end_x_values.set(j, lines.get(j).getEndX());
                }
                player.stopMoving();
                timeline.pause();
                collision.pause();
                game.endGame();
            }
        }
    }

        @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void check_crossed() {
        if(player.getBall().getCenterY()<y+y1){
            if(!left_behind){
                left_behind=true;
                System.out.println("Horizontal line crossed");
                game.addObstaclesCrossed();
            }
        }
    }

    @Override
    public void remove_obstacle(Pane pane) {
        for(int i=0;i<16;i++)
            pane.getChildren().remove(lines.get(i));
        timeline.stop();
        collision.stop();
    }


        @Override
    public void add_obstacle(Pane pane) {
        for(int i=0;i<16;i++)
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
        timeline=new Timeline(new KeyFrame(Duration.millis(game.getTime()*5),this::move_obstacle));
        timeline.setCycleCount(Timeline.INDEFINITE);
        collision=new Timeline(new KeyFrame(Duration.millis(50), this::detect_collision));
        collision.setCycleCount(-1);
        colors=new ArrayList<Color>();
        colors.add((Color)Paint.valueOf("RED"));
        colors.add((Color)Paint.valueOf("GREEN"));
        colors.add((Color)Paint.valueOf("BLUE"));
        colors.add((Color)Paint.valueOf("YELLOW"));
        lines=new ArrayList<Line>();
        for(int i=0;i<8;i++){
            lines.add(new Line(start_x_values.get(i), y, end_x_values.get(i), y));
            lines.get(i).setStrokeWidth(12);
        }
        for(int i=8;i<16;i++){
            lines.add(new Line(start_x_values.get(i), y+y1, end_x_values.get(i), y+y1));
            lines.get(i).setStrokeWidth(12);
        }
        for(int i=0;i<4;i++){
            lines.get(i).setStroke(colors.get(i));
            lines.get(i+4).setStroke(colors.get(i));
        }
        for(int i=0;i<4;i++){
            int p=i+8;
            lines.get(p).setStroke(colors.get((i-1+4)%4));
            lines.get(p+4).setStroke(colors.get((i-1+4)%4));
        }
        move_obstacle(new ActionEvent());
    }
}