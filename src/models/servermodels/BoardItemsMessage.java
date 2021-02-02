package models.servermodels;

import models.BoardItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for sending ArrayList BoardItems through socket
 */
public class BoardItemsMessage extends RestMessageBody implements Serializable {

    private ArrayList<BoardItem> items;

    /**
     * constructor
     * @param items ArrayList BoardItem
     */
    public BoardItemsMessage(ArrayList<BoardItem> items){
        this.items = items;
    }

    public ArrayList<BoardItem> getItems() {
        return items;
    }
}
