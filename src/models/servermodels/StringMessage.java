package models.servermodels;

import java.io.Serializable;

/**
 * sending just some string data to server as body
 */
public class StringMessage extends RestMessageBody implements Serializable {

    private String content;

    /**
     * constructor
     * @param content String
     */
    public StringMessage(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
