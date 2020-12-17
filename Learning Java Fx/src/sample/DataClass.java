package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DataClass implements Serializable {
    private HashMap<String, String> games;
    private ArrayList<String> saved_games;
    private int best_score;
    public DataClass(){
        games=new HashMap<>();
        saved_games=new ArrayList<>();
    }
    public HashMap<String, String> fileNames(){
        return this.games;
    }
    public ArrayList<String> savedGames(){
        return this.saved_games;
    }
    public GameClass loadGame(int index){
        //deserialized
        String p=saved_games.get(index);
        GameClass g1;
        ObjectInputStream in=null;
        try{
            in=new ObjectInputStream(new FileInputStream("Saved_Games/"+p));
            g1=(GameClass) (in.readObject());
            in.close();
        } catch (Exception e){
            System.out.println("Could not get game");
            return null;
        } finally {
            games.remove(p);
            saved_games.remove(index);
        }

        return g1;
    }
    public void saveGame(GameClass g){
        Date d1=new Date();
        String p=d1.toGMTString();
        String game_name="GAME "+d1.getDate()+" "+d1.getMonth()+" "+d1.getYear()+" "+d1.getHours()+" "+d1.getMinutes()+" "+d1.getSeconds();
        System.out.println(game_name);
        System.out.println(d1.toLocaleString());
        saved_games.add(game_name);
        games.put(game_name, d1.toLocaleString());
        ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("Saved_Games/"+game_name));
            out.writeObject(g);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR in serializing game");
        }
    }
    public void removeGame(int index){
        String p=saved_games.get(index-1);
        games.remove(p);
    }
    public void updateGame(GameClass g){
        //serialize
    }
    public void clearData(){
        saved_games.clear();
        games.clear();
    }
    public boolean checkNoGamesSaved(){
        return (saved_games.size()==0);
    }
    public void serialize(){
        ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("DataClass_Data/data.txt"));
            out.writeObject(this);
            out.close();
        } catch (Exception e){
            System.out.println("ERROR in serializing file");
        }
    }
    public void setBest_score(int a) {
        best_score = a;
    }
    public int getBest_score(){
        return this.best_score;
    }
}
