package models;

import helpers.BufferedImages;

import java.io.Serializable;

/**
 * Sun Class is to repressents the Sun Elements That Are Created By SunFlowers
 */

public class Sun implements Serializable {

    // location x
    private int locationX;
    // location y
    private int locationY;

    /**
     * Constructor Of the Class
     * @param locationX Horizontal Point
     * @param locationY Vertical Point
     */
    public Sun(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * For Returning the Sun Location ON X's Vector
     * @return Horizontal Point
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * For Returning the Sun Location ON Y's Vector
     * @return Vertical Point
     */
    public int getLocationY() {
        return locationY;
    }

}
