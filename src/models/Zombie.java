package models;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.ZombieGuiThread;
import helpers.threads.ZombieLogicalThread;
import helpers.threads.ThreadPool;
import views.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This Abstract Class Represents all the Zombies from this Class
 * Other Types of Zombies are Instantiated from this Class
 *
 */
public abstract class Zombie implements Serializable {

    private int health;
    private double speed;
    private int damage;
    private Coordinate coordinate;
    // private String imageUri;
    // new
    private transient BufferedImage image;
    private transient ImageIcon imageIcon;

    // adding x and y for easier rendering in GameCanvas
    private int locationX;
    private int locationY;

    private transient ZombieLogicalThread logicalThread;
    private transient ZombieGuiThread guiThread;

    private Image imageGif;

    /**
     * Constructor
     *
     * @param image Zombies Main Image
     * @param coordinate Zombies Location in the Map
     * @param health Zombies Total Hp In the Game
     * @param damage Zombies Power To Hit Plants
     * @param speed Zombies Speed for Moving
     * @param icon Zombies Icon Image
     * @param state State of Each Zombie In The Game
     */

    public Zombie(BufferedImage image,
                  Coordinate coordinate,
                  int health,
                  int damage,
                  double speed,
                  ImageIcon icon,
                  Image gif,
                  GameState state){
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.coordinate = coordinate;
        this.image = image;
        this.imageIcon = icon;
        this.imageGif = gif;

        guiThread = new ZombieGuiThread(this);
        ThreadPool.execute(guiThread);

        logicalThread = new ZombieLogicalThread(this,guiThread,state);
        ThreadPool.execute(logicalThread);
    }

    /**
     * method for returning image specially gifs
     * @return Image
     */
    public Image getImageGif() {
        return imageGif;
    }

    /**
     * A method for Returning the needed Image
     * @return image
     */

    public BufferedImage getImage() {
        return image;
    }

    /**
     * A Method for Returning the Coordinate of a Zombie
     * @return coordinate
     */

    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * A set Method for Placing a Zombie
     * @param coordinate Vertical and Horizontal Points of the Zombie
     */

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Method for Knowing How Fast a Zombie is
     * @return speed
     */

    public double getSpeed() {
        return speed;
    }

    /**
     * Returning Zombies Hp in each State
     * @return health
     */

    public int getHealth() {
        return health;
    }

    /**
     * Setting Zombies Health after getting hit by bullets
     * Changing Cone AND Bucket To A Normal Zombie in the Specified Situation
     * @param health Hp loss of Zombie
     */
    public void setHealth(int health){
        this.health = health;
        if(health <= 200) {
            image = BufferedImages.zombie_normal;
            imageIcon = ImageIcons.zombie_normal;
        }
    }

    /**
     * Returning How Much Damage can a Zombie Cost on Plants Life
     * @return damage
     */

    public int getDamage() {
        return damage;
    }

    /**
     * Zombie Attack
     */

    public abstract void attack();

    /**
     * A Method for Game Current by Moving Zombies
     */

    public void moveOneStateLeft(){
        setCoordinate(new Coordinate(coordinate.getAxis_x() - 1,coordinate.getAxis_y()));
    }

    /**
     * A Method For Zombies Icon In The Map
     * @return imageIcon
     */

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Returning the Vertical Point of Zombie
     * @return locationY
     */

    public int getLocationY() {
        return locationY;
    }

    /**
     *  Returning the Horizontal Point of Zombie
     * @return locationX
     */

    public int getLocationX() {
        return locationX;
    }

    /**
     * Setting The Location of A Zombie In the Map
     * @param locationX Vertical point
     * @param locationY Horizontal point
     */

    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Removing A Zombie of The Map in Case of Its Death
     */

    public void stopThreads(){
        logicalThread.stopThread();
        guiThread.stopThread();
    }

    /**
     * A Method for resuming a Saved Game Based of Where Game elements where , it builds their thread
     * @param state the State of the map that zombie was placed in the last load
     */

    public void resumeObject(GameState state){
        if(this instanceof NormalZombie){
            image = BufferedImages.zombie_normal;
        } else if(this instanceof ConeHeadZombie){
            image = BufferedImages.zombie_conehead;
        } else {
            image = BufferedImages.zombie_buckethead;
        }

        guiThread = new ZombieGuiThread(this);
        ThreadPool.execute(guiThread);

        logicalThread = new ZombieLogicalThread(this,guiThread,state);
        ThreadPool.execute(logicalThread);
    }

}