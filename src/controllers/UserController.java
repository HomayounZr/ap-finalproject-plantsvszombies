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
 */

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

    public void getSavesFromServer(){
        String username = getUser().getUsername();
        try{

            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            RestMessage request = new RestMessage("getSaves",new StringMessage(username));
            outputStream.writeObject(request);

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] buffer = new byte[4096];
            BufferedInputStream in = new BufferedInputStream(dataInputStream);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("./data/serversaves.txt")));
            while(in.available() > 0){
                int count = in.read(buffer);
                out.write(buffer,0,count);
            }
            out.flush();

            dataInputStream.close();
            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean saveSavesToServer(GameSave save){
        getSavesFromServer();
        try{

            File file = new File("./data/serversaves.txt");

            try{
                ObjectInputStream saveInputStream = new ObjectInputStream(new FileInputStream(file));

                GameSaves currentSaves = (GameSaves) saveInputStream.readObject();
                saveInputStream.close();
                if(currentSaves == null){
                    currentSaves = new GameSaves();
                }
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

            Socket socket = new Socket("localhost",3000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            RestMessage request = new RestMessage("newSave",new StringMessage(getUser().getUsername()));
            outputStream.writeObject(request);

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
            socket.close();

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
