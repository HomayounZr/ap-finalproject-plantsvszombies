package models;

import helpers.BufferedImages;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Bullet implements Serializable {

    private int damage;
    private Coordinate coordinate;
//    private String imageUri;
    private transient BufferedImage image;
    private transient ImageIcon imageIcon;
    private int type;

    private int locationX;
    private int locationY;

    public Bullet(BufferedImage image,int damage,Coordinate coordinate,ImageIcon icon,int type){
        this.damage = damage;
        this.coordinate = coordinate;
        this.image = image;
        this.imageIcon = icon;
        this.type = type;
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

    public void resumeObject(){
        if(type == 0){
            image = BufferedImages.bullet_normal;
        } else {
            image = BufferedImages.bullet_frozen;
        }
    }

}
