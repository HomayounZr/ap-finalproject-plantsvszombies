package models;

import java.io.Serializable;
import java.util.ArrayList;

public class GameSaves implements Serializable {

    private ArrayList<GameSave> gameSaves;

    public GameSaves(){
        gameSaves = new ArrayList<>();
    }

    public ArrayList<GameSave> getGameSaves() {
        return gameSaves;
    }

    public void addNewSave(GameSave gameSave){
        gameSaves.add(gameSave);
    }
}
