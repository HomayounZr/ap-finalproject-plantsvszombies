package controllers;

import helpers.UserFileHelper;
import models.servermodels.*;
import models.User;
import models.servermodels.ChangeUsernameMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserController {

    private UserFileHelper fileHelper;

    public UserController(){
        fileHelper = new UserFileHelper();
    }

    public User getUser(){
        return fileHelper.getUser();
    }

    public void saveNewData(int score){
        User user = fileHelper.getUser();
        switch (score){
            case 3:
                user.setWins_easy();
                break;
            case -1:
                user.setLoses_easy();
                break;
            case 10:
                user.setWins_hard();
                break;
            case -3:
                user.setLoses_hard();
                break;
            default:
                break;
        }
        user.setTotalScore(user.getTotalScore() + score);
        fileHelper.save();
    }

    public String changeUsername(String username){
        // connect to server and get data;

        String result = "";

        // if was ok
        result = "ok";
        fileHelper.getUser().setUsername(username);
        fileHelper.save();

        return result;
    }

    public boolean newUser(String username){
        // check username with server
        try{

            Socket socket = new Socket("localhost",3000);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ChangeUsernameMessage messageBody = new ChangeUsernameMessage("",username);

            RestMessage request = new RestMessage("changeUsername",messageBody);
            outputStream.writeObject(request);
            RestMessage response = (RestMessage) inputStream.readObject();
            String content = ((StringMessage)response.getBody()).getContent();
            if(content.equalsIgnoreCase("EXISTS")){
                return false;
            } else {
                // if was ok add it
                User user = new User(username);
                fileHelper.setUser(user);
                return true;
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}
