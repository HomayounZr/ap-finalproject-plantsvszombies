package models;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.ZombieLogicalThread;
import helpers.threads.ZombieGuiThread;
import helpers.threads.ThreadPool;
import views.GameState;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * this class represents a zombie
 * it's the super class for normal, conehead and buckethead zombie
 *
 */
public abstract class Zombie {

    private int health;
    private double speed;
    private int damage;
    private Coordinate coordinate;
    //    private String imageUri;
    // new
    private BufferedImage image;
    private ImageIcon imageIcon;

    // adding x and y for easier rendering in GameCanvas
    private int locationX;
    private int locationY;

    private ZombieLogicalThread logicalThread;
    private ZombieGuiThread guiThread;

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
}