package models.servermodels;

import models.BoardItem;

import java.io.Serializable;
import java.util.ArrayList;

public class BoardItemsMessage extends RestMessageBody implements Serializable {

    private ArrayList<BoardItem> items;

    public BoardItemsMessage(ArrayList<BoardItem> items){
        this.items = items;
    }

    public ArrayList<BoardItem> getItems() {
        return items;
    }
}
