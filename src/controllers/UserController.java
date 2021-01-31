package controllers;

import helpers.UserFileHelper;
import models.BoardItem;
import models.servermodels.*;
import models.User;
import models.servermodels.ChangeUsernameMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

    public boolean changeUsername(String prevUsername,String username){
        // check username with server
        try{

            Socket socket = new Socket("localhost",3000);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ChangeUsernameMessage messageBody = new ChangeUsernameMessage(prevUsername,username);


            RestMessage request = new RestMessage("changeUsername",messageBody);
            outputStream.writeObject(request);
            RestMessage response = (RestMessage) inputStream.readObject();
            String content = ((StringMessage)response.getBody()).getContent();
            socket.close();

            if(content.equalsIgnoreCase("EXISTS")){
                return false;
            } else {
                // if was ok add it
                fileHelper.getUser().setUsername(username);
                fileHelper.save();
                return true;
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
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
            socket.close();

            if(content.equalsIgnoreCase("EXISTS")){
                return false;
            } else {
                // if was ok add it
                User user = new User(username);
                fileHelper.setUser(user);
                fileHelper.save();
                return true;
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<BoardItem> getScoreBoard(){
        try{

            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            RestMessage request = new RestMessage("getAll",null);
            outputStream.writeObject(request);

            RestMessage response = (RestMessage) inputStream.readObject();
            BoardItemsMessage content = (BoardItemsMessage) response.getBody();

            socket.close();
            return content.getItems();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean submitDataToServer(){
        try{

            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            User user = getUser();
            BoardItem item = new BoardItem(
                    user.getUsername(),
                    user.getWins_easy(),
                    user.getWins_hard(),
                    user.getLoses_easy(),
                    user.getLoses_hard(),
                    user.getTotalScore()
            );
            NewBoardItemMessage requestBody = new NewBoardItemMessage(item);
            RestMessage request = new RestMessage("newRecord",requestBody);

            outputStream.writeObject(request);

            RestMessage response = (RestMessage) inputStream.readObject();
            String content = ((StringMessage) response.getBody()).getContent();

            socket.close();
            if(content.equals("OK"))
                return true;
        } catch (Exception ex){

        }
        return false;
    }

}
