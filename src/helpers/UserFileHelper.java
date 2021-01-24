package helpers;

import models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserFileHelper {

    private static final String path = "./data/user.txt";
    private File file;
    private User user;

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

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void save(){
        if(file.exists())
            file.delete();

        ObjectOutputStream outputStream = null;
        try{

            Files.createFile(Paths.get(path));
            file = new File(path);
            outputStream = new ObjectOutputStream(new FileOutputStream(file));

            outputStream.writeObject(user);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
