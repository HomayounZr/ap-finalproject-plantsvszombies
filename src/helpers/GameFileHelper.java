package helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameFileHelper {

    private static final String path = "./data/gamedata.txt";
    private File file;
//    private

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
