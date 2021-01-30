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
import java.util.Iterator;

public class LawnMower {

    private String imageUri;
    private int row;
    private BufferedImage image;
    private ImageIcon imageIcon;
    private int locationX;
    private int locationY;

    public LawnMower(int row){
        imageUri = "./images/Gifs/lawn_mower.gif";
        this.row = row;
        imageIcon = ImageIcons.lawn_mower;
        image = BufferedImages.lawn_mower;
    }

    public void activate(GameState state){
        imageIcon = ImageIcons.lawn_mower_active;
        image = BufferedImages.lawn_mower_active;

        LawnMowerGuiThread guiThread = new LawnMowerGuiThread(this);
        ThreadPool.execute(guiThread);

        Iterator<Zombie> it = state.getZombies().iterator();
        while(it.hasNext()){
            Zombie zombie = it.next();
            if(zombie.getCoordinate().getAxis_y() == row){
                zombie.stopThreads();
                state.getZombies().remove(zombie);
            }
        }
        state.getLawnMowers().remove(this);
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
}
