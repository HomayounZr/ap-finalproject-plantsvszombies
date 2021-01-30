package models;

import helpers.threads.BulletLogicalThread;
import helpers.threads.ThreadPool;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bullet {

    private int damage;
    private Coordinate coordinate;
    //    private String imageUri;
    private BufferedImage image;
    private ImageIcon imageIcon;

    private int locationX;
    private int locationY;

    public Bullet(BufferedImage image,int damage,Coordinate coordinate,ImageIcon icon){
        this.damage = damage;
        this.coordinate = coordinate;
        this.image = image;
        this.imageIcon = icon;
//        BulletLogicalThread newThread = new BulletLogicalThread(this);
//        ThreadPool.execute(newThread);
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

    /**
     * code to move one state to the right
     */
    public void moveOneStateRight(){
//        synchronized (bullets) {
        this.coordinate = new Coordinate(coordinate.getAxis_x() + 1, coordinate.getAxis_y());
//        }
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getDamage() {
        return damage;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
