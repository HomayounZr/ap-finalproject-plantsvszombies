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

public class LawnMower implements Serializable {

    private int row;
    private transient BufferedImage image;
    private transient ImageIcon imageIcon;
    private int locationX;
    private int locationY;

    public LawnMower(int row){
        this.row = row;
        imageIcon = ImageIcons.lawn_mower;
        image = BufferedImages.lawn_mower;
    }

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

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public int getRow() {
        return row;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void resumeObject(){
        image = BufferedImages.lawn_mower;
    }
}
