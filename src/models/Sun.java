package models;

import helpers.BufferedImages;

import java.io.Serializable;

public class Sun implements Serializable {

    private int locationX;
    private int locationY;

    public Sun(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

}
