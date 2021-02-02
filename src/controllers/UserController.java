package controllers;

import helpers.UserFileHelper;
import models.BoardItem;
import models.GameSave;
import models.GameSaves;
import models.servermodels.*;
import models.User;
import models.servermodels.ChangeUsernameMessage;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This Class Holds User Control
 * saves new data to the file
 * send and recieve data from/to the server
 */

public class UserController {

    private UserFileHelper fileHelper;

    /**
     * constructor
     * initializing filehelper
     */
    public UserController(){
        fileHelper = new UserFileHelper();
    }

    /**
     * get current user
     * @return User
     */
    public User getUser(){
        return fileHelper.getUser();
    }

    /**
     * add new data to file when game finish
     * and add them previous records
     * @param score Integer gamescore
     */
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

    /**
     * change username and check it with server
     * @param prevUsername String
     * @param username String new username
     * @return boolean based on operation result
     */
    public boolean changeUsername(String prevUsername,String username){
        // check username with server
        try{
            // creating socket with the server
            Socket socket = new Socket("localhost",3000);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // create request body
            ChangeUsernameMessage messageBody = new ChangeUsernameMessage(prevUsername,username);
            // create a new request object and send to server
            RestMessage request = new RestMessage("changeUsername",messageBody);
            outputStream.writeObject(request);
            // read server response
            RestMessage response = (RestMessage) inputStream.readObject();
            String content = ((StringMessage)response.getBody()).getContent();
            socket.close();
            // if username was already taken
            if(content.equalsIgnoreCase("EXISTS")){
                return false;
            } else {
                // if was ok then save new username
                fileHelper.getUser().setUsername(username);
                fileHelper.save();
                return true;
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * select a new username when user opens game for first time
     * @param username String
     * @return boolean based on operation success
     */
    public boolean newUser(String username){
        // check username with server
        try{
            // create a new socket
            Socket socket = new Socket("localhost",3000);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // create request body object
            ChangeUsernameMessage messageBody = new ChangeUsernameMessage("",username);
            // create a new request object
            RestMessage request = new RestMessage("changeUsername",messageBody);
            outputStream.writeObject(request);
            // read server's response
            RestMessage response = (RestMessage) inputStream.readObject();
            String content = ((StringMessage)response.getBody()).getContent();
            socket.close();
            // if username was already taken
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

    /**
     * get scoreboard and the scores and winnings of
     * other players from server
     * @return ArrayList BoardItem
     */
    public ArrayList<BoardItem> getScoreBoard(){
        try{
            // create socket
            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // create a new request object
            RestMessage request = new RestMessage("getAll",null);
            outputStream.writeObject(request);
            // read server's response
            RestMessage response = (RestMessage) inputStream.readObject();
            BoardItemsMessage content = (BoardItemsMessage) response.getBody();

            socket.close();
            return content.getItems();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * add new data to server when user finish a game
     * @return boolean doesn't matter
     */
    public boolean submitDataToServer(){
        try{
            // creating socket
            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // create request body data and send the request
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
            // read server's response
            RestMessage response = (RestMessage) inputStream.readObject();
            String content = ((StringMessage) response.getBody()).getContent();

            socket.close();
            if(content.equals("OK"))
                return true;
        } catch (Exception ex){

        }
        return false;
    }

    /**
     * get all user saves from server
     * and the write it to ./data/serevrsaves.txt
     */
    public void getSavesFromServer(){
        String username = getUser().getUsername();
        try{
            // establishing socket
            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // sending request
            RestMessage request = new RestMessage("getSaves",new StringMessage(username));
            outputStream.writeObject(request);
            // reading data from buffer and creating the file
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] buffer = new byte[4096];
            BufferedInputStream in = new BufferedInputStream(dataInputStream);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("./data/serversaves.txt")));
            while(in.available() > 0){
                int count = in.read(buffer);
                out.write(buffer,0,count);
            }
            out.flush();

//            dataInputStream.close();
//            inputStream.close();
//            outputStream.close();
//            socket.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * save a new save to the server
     * @param save GameSave
     * @return boolean
     */
    public boolean saveSavesToServer(GameSave save){
        // recieve all current user saves in server
        getSavesFromServer();
        try{
            // connect to file
            File file = new File("./data/serversaves.txt");

            try{
                ObjectInputStream saveInputStream = new ObjectInputStream(new FileInputStream(file));
                // reading all previous game saves in file
                GameSaves currentSaves = (GameSaves) saveInputStream.readObject();
                saveInputStream.close();
                if(currentSaves == null){
                    currentSaves = new GameSaves();
                }
                // add new save to file and write it down to local desination
                currentSaves.addNewSave(save);
//                saveInputStream.close();
                if(file.exists()){
                    file.delete();
                    file.createNewFile();
                }
                ObjectOutputStream saveOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                saveOutputStream.writeObject(currentSaves);
                saveOutputStream.close();
            } catch (Exception ex){
                GameSaves currentSaves = new GameSaves();
                currentSaves.addNewSave(save);
                if(file.exists()){
                    file.delete();
                    file.createNewFile();
                }
                ObjectOutputStream saveOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                saveOutputStream.writeObject(currentSaves);
                saveOutputStream.close();
            }
            // establishing socket
            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // sending request with username
            RestMessage request = new RestMessage("newSave",new StringMessage(getUser().getUsername()));
            outputStream.writeObject(request);
            // start sending file to server with buffer
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            byte[] buffer = new byte[4096];
            while(in.available() > 0){
                int count = in.read(buffer);
                out.write(buffer,0,count);
            }
//            out.flush();
//
//            out.close();
//            in.close();
//            inputStream.close();
//            outputStream.close();
//            socket.close();

            return true;

        } catch (Exception ex){
//            JOptionPane.showMessageDialog(
//                    null,
//                    ex.getMessage()
//            );
            ex.printStackTrace();
        }
        return false;
    }

}
