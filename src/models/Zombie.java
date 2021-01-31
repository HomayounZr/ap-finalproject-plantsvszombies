package models;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.ZombieGuiThread;
import helpers.threads.ZombieLogicalThread;
import helpers.threads.ThreadPool;
import views.GameState;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * this class represents a zombie
 * it's the super class for normal, conehead and buckethead zombie
 *
 */
public abstract class Zombie implements Serializable {

    private int health;
    private double speed;
    private int damage;
    private Coordinate coordinate;
//    private String imageUri;
    // new
    private transient BufferedImage image;
    private transient ImageIcon imageIcon;

    // adding x and y for easier rendering in GameCanvas
    private int locationX;
    private int locationY;

    private transient ZombieLogicalThread logicalThread;
    private transient ZombieGuiThread guiThread;

    public Zombie(BufferedImage image,
                  Coordinate coordinate,
                  int health,
                  int damage,
                  double speed,
                  ImageIcon icon,
                  GameState state){
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.coordinate = coordinate;
        this.image = image;
        this.imageIcon = icon;

        guiThread = new ZombieGuiThread(this);
        ThreadPool.execute(guiThread);

        logicalThread = new ZombieLogicalThread(this,guiThread,state);
        ThreadPool.execute(logicalThread);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public double getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health){
        this.health = health;
        if(health <= 200) {
            image = BufferedImages.zombie_normal;
            imageIcon = ImageIcons.zombie_normal;
        }
    }

    public int getDamage() {
        return damage;
    }

    public abstract void attack();

    public void moveOneStateLeft(){
        setCoordinate(new Coordinate(coordinate.getAxis_x() - 1,coordinate.getAxis_y()));
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void stopThreads(){
        logicalThread.stopThread();
        guiThread.stopThread();
    }

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