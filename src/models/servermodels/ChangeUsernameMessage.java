package models.servermodels;

import java.io.Serializable;

public class ChangeUsernameMessage extends RestMessageBody implements Serializable {

    private String prevUsername;
    private String newUsername;

    public ChangeUsernameMessage(String prevUsername,String newUsername){
        this.prevUsername = prevUsername;
        this.newUsername = newUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getPrevUsername() {
        return prevUsername;
    }

}
