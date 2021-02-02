package models;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.LawnMowerGuiThread;
import helpers.threads.ThreadPool;
import views.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;

/**
 * A LawnMower Class , Implements LawnMowers
 */

public class LawnMower implements Serializable {

    // row
    private int row;
    // buffered image
    private transient BufferedImage image;
    // image icon
    private transient ImageIcon imageIcon;
    // x location on frame
    private int locationX;
    // y location on frame
    private int locationY;

    /**
     * Constructor for the Class
     * @param row the number of the row 0 to 4
     */
    public LawnMower(int row){
        this.row = row;
        imageIcon = ImageIcons.lawn_mower;
        image = BufferedImages.lawn_mower;
    }

    /**
     * The Method Activates Lawn Mowers
     * if Zombies Reach the Edge of the Map the Element Will be Activated
     * @param state GameState
     */
    public void activate(GameState state){
        imageIcon = ImageIcons.lawn_mower_active;
        image = BufferedImages.lawn_mower_active;

        Iterator<Zombie> it = state.getZombies().iterator();
        while(it.hasNext()){
            Zombie zombie = it.next();
            if(zombie.getCoordinate().getAxis_y() == row){
                zombie.stopThreads();
                state.getZombies().remove(zombie);
            }
        }
        LawnMowerGuiThread guiThread = new LawnMowerGuiThread(this,state);
        ThreadPool.execute(guiThread);
    }

    /**
     * Image of the LawnMowers
     * @return ImageIcon
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * The RowNumber Of the LawnMowers
     * @return int rowNumber
     */
    public int getRow() {
        return row;
    }

    /**
     * The BufferedImage Of LawnMowers
     * @return BufferedImage image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Setting LawnMowers Location
     * @param locationX Horizontal Point
     * @param locationY Vertical Point
     */
    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Getter For Horizontal Point
     * @return X's Vector Point
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * Getter For Vertical Point
     * @return Y's Vector Point
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * this method is for re-rendering saved object
     * and re-run it's threads when load a previous
     * saved game
     */
    public void resumeObject(){
        image = BufferedImages.lawn_mower;
    }
}
