package helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * this class is for saving a current game to
 * the file and reading it again for loading game
 * another time
 *
 * **NOTE**
 * this class won't be used all logics and codes
 * is implemented in GameState and ShowGameSaves
 */
public class GameFileHelper {

    // destination
    private static final String path = "./data/gamedata.txt";
    // file
    private File file;

    /**
     * constructor
     * creating a new file when doesn't exist
     * and initiating the file
     */
    public GameFileHelper(){
        file = new File(path);
        if(!file.exists()){
            try{
                Files.createFile(Paths.get(path));
                file = new File(path);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        loadData();
    }

    /*
     * load previous data and write them on memory
     */
    private void loadData(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void saveGame(){
        // logic for saving game
    }

    public void loadGame(){
        // logic for loading game
    }

    /**
     * save new file after changes
     */
    public void save(){
        if(file.exists())
            file.delete();

        ObjectOutputStream outputStream = null;
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(file));

            // saving the file

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try{
                outputStream.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

}
