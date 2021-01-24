package models.servermodels;

import java.io.Serializable;

public class StringMessage extends RestMessageBody implements Serializable {

    private String content;

    public StringMessage(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
