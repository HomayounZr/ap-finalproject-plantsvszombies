package models.servermodels;

import java.io.Serializable;

/**
 * Class for sending previous and new username through socket
 */
public class ChangeUsernameMessage extends RestMessageBody implements Serializable {

    private String prevUsername;
    private String newUsername;

    /**
     * constructor
     * @param prevUsername String previous username
     * @param newUsername String new username
     */
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
