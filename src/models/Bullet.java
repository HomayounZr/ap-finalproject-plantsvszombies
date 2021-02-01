package models;

import helpers.BufferedImages;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This Class Presents Bullets that Plants Shoot to kill Zombies
 * Both Frozen and Pea Bullets
 */

public class Bullet implements Serializable {

    private int damage;
    private Coordinate coordinate;
//    private String imageUri;
    private transient BufferedImage image;
    private transient ImageIcon imageIcon;
    private int type;

    private int locationX;
    private int locationY;

    /**
     * Constructor Of the Class
     * @param image Image of the Bullet
     * @param damage Damage Of the Bullet
     * @param coordinate Location of the Bullet
     * @param icon Icon of the Bullet
     * @param type Type of the Bullet
     */

    public Bullet(BufferedImage image,int damage,Coordinate coordinate,ImageIcon icon,int type){
        this.damage = damage;
        this.coordinate = coordinate;
        this.image = image;
        this.imageIcon = icon;
        this.type = type;
//        BulletLogicalThread newThread = new BulletLogicalThread(this);
//        ThreadPool.execute(newThread);
    }

    /**
     * Setter Method For Bullets Location
     * @param locationX Horizontal PoiNT
     * @param locationY Vertical Point
     */

    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Getter Method for Horizontal Location
     * @return X'S Vector Point
     */

    public int getLocationX() {
        return locationX;
    }
    /**
     * Getter Method for Vertical Location
     * @return Y'S Vector Point
     */

    public int getLocationY() {
        return locationY;
    }

    /**
     * code to move Bullets one state to the right
     */
    public void moveOneStateRight(){
//        synchronized (bullets) {
            this.coordinate = new Coordinate(coordinate.getAxis_x() + 1, coordinate.getAxis_y());
//        }
    }

    /**
     * Achieving the Location of Each Bullet
     * @return Coordinate of Bullets
     */

    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Getting The Buffered Image of Each Bullet
     * @return bufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Returns the Amount of Bullets Damage
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Getter Method for the Image Icon of the Bullet
     * @return ImageIcon
     */

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Loading The Existing Bullet From Last Time The Game Was Saved
     */
    public void resumeObject(){
        if(type == 0){
            image = BufferedImages.bullet_normal;
        } else {
            image = BufferedImages.bullet_frozen;
        }
    }

}
