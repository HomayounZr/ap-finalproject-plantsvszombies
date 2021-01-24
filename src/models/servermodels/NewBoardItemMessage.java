package models.servermodels;

import models.BoardItem;

import java.io.Serializable;

public class NewBoardItemMessage extends RestMessageBody implements Serializable {

    private BoardItem item;

    public NewBoardItemMessage(BoardItem item){
        this.item = item;
    }

    public BoardItem getItem() {
        return item;
    }
}
