package helpers;

import models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * file helper for user like saving new username, game results,...
 */
public class UserFileHelper {

    // destination
    private static final String path = "./data/user.txt";
    // file instance
    private File file;
    // user instance
    private User user;

    /**
     * constructor
     * creating a new file when doesn't exist
     * and initiating the file
     */
    public UserFileHelper(){
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
     * load all previous data in file
     * and load the in memory
     */
    private void loadData(){
        user = null;
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){

            while(true){
                User content = (User) inputStream.readObject();
                if(content != null){
                    user = content;
                }
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * get user
     * @return User
     */
    public User getUser(){
        return user;
    }

    /**
     * set new user
     * @param user User
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * save the changes to new file
     * after each operation on user object
     */
    public void save(){
        if(file.exists())
            file.delete();

//        ObjectOutputStream outputStream = null;
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));){

//            Files.createFile(Paths.get(path));
//            file = new File(path);


            outputStream.writeObject(user);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
