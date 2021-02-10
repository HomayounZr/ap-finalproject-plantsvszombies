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

//    private transient Image imageGif;

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
//                  Image gif,
                  GameState state){
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.coordinate = coordinate;
        this.image = image;
        this.imageIcon = icon;
//        this.imageGif = gif;

        // running gui thread
        guiThread = new ZombieGuiThread(this);
        ThreadPool.execute(guiThread);
        // running logical thread
        logicalThread = new ZombieLogicalThread(this,guiThread,state);
        ThreadPool.execute(logicalThread);
    }

    /**
     * method for returning image specially gifs
     * @return Image
     */
//    public Image getImageGif() {
//        return imageGif;
//    }

    /**
     * A method for Returning the needed Image
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * A Method for Returning the Coordinate of a Zombie
     * @return Coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * A set Method for Placing a Zombie
     * @param coordinate Coordinate, Vertical and Horizontal Points of the Zombie
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Method for Knowing How Fast a Zombie is
     * how much it takes to move one state to the left
     * @return double speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * set speed to original half when a frozen pee hits it
     * it multiply it by 2 because speed field is actually the time
     * that zombie moves a state to the left
     */
    public void halfSpeed() {
        this.speed *= 2;
    }

    /**
     * get zombie health
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setting Zombies Health after getting hit by bullets
     * Changing Cone AND Bucket To A Normal Zombie in the Specified Situation
     * @param health int Hp loss of Zombie
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
     * @return int damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Zombie Attack **useless**
     */
    public abstract void attack();

    /**
     * Moving zombie one state forward
     */
    public void moveOneStateLeft(){
        setCoordinate(new Coordinate(coordinate.getAxis_x() - 1,coordinate.getAxis_y()));
    }

    /**
     * get ImageIcon
     * @return ImageIcon
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Returning the Vertical Point of Zombie
     * @return int locationY
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     *  Returning the Horizontal Point of Zombie
     * @return int locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * Setting The Location of A Zombie In the Map
     * @param locationX int Vertical point
     * @param locationY int Horizontal point
     */
    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * stop zombie threads when dies
     */
    public void stopThreads(){
        logicalThread.stopThread();
        guiThread.stopThread();
    }

    /**
     * this method is for re-rendering saved object
     * and re-run it's threads when load a previous
     * saved game
     */
    public void resumeObject(GameState state){
        if(this instanceof NormalZombie){
            image = BufferedImages.zombie_normal;
        } else if(this instanceof ConeHeadZombie){
            image = BufferedImages.zombie_conehead;
            if(this.health <= 200)
                image = BufferedImages.zombie_normal;
        } else if(this instanceof BucketHeadZombie){
            image = BufferedImages.zombie_buckethead;
            if(this.health <= 200)
                image = BufferedImages.zombie_normal;
        } else {
            image = BufferedImages.zombie_football;
            if(this.health <= 200)
                image = BufferedImages.zombie_normal;
        }

        guiThread = new ZombieGuiThread(this);
        ThreadPool.execute(guiThread);

        logicalThread = new ZombieLogicalThread(this,guiThread,state);
        ThreadPool.execute(logicalThread);
    }

}