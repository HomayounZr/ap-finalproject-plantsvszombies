package models.servermodels;

import models.BoardItem;

import java.io.Serializable;

/**
 * send a new board item to socket to save in server
 */
public class NewBoardItemMessage extends RestMessageBody implements Serializable {

    private BoardItem item;

    /**
     * constructor
     * @param item BoardItem
     */
    public NewBoardItemMessage(BoardItem item){
        this.item = item;
    }

    public BoardItem getItem() {
        return item;
    }
}
