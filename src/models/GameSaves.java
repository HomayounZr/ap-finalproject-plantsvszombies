package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * holds list of all GameSave
 */
public class GameSaves implements Serializable {

    // all saves
    private ArrayList<GameSave> gameSaves;

    /**
     * constructor
     */
    public GameSaves(){
        gameSaves = new ArrayList<>();
    }

    /**
     * get all saves
     * @return
     */
    public ArrayList<GameSave> getGameSaves() {
        return gameSaves;
    }

    /**
     * add new save
     * @param gameSave GameSave
     */
    public void addNewSave(GameSave gameSave){
        gameSaves.add(gameSave);
    }
}
