package models;

import helpers.threads.PlantThread;
import helpers.threads.ThreadPool;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Plant {

    private Coordinate coordinate;
//    private String imageUri;
    private BufferedImage image;
    private ImageIcon imageIcon;
    // sun: 50, pea: 70, snowpea: 100, wall-nut: 150, cherrybomb: 70;
    private int health;
    private int actionInterval;
    private boolean isAlive;

    private int locationX;
    private int locationY;

    protected ArrayList<Bullet> bullets;
    protected ArrayList<Sun> suns;

    private PlantThread thread;

    public Plant(Coordinate coordinate,int health,int actionInterval,BufferedImage image,ImageIcon icon){
        this.coordinate = coordinate;
        this.health = health;
        this.actionInterval = actionInterval;
        this.isAlive = true;
        this.image = image;
        this.imageIcon = icon;

        thread = new PlantThread(this);
        ThreadPool.execute(thread);
    }

    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void addGameStateValues(ArrayList<Sun> suns,ArrayList<Bullet> bullets){
        this.suns = suns;
        this.bullets = bullets;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getActionInterval() {
        return actionInterval;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public void decreaseHealth(int amount){
        this.health -= amount;
        if(this.health <= 0){
            this.isAlive = false;
            // code to remove the plant
        }
    }

    // when the plant was dying
    public void changeImageToDying(BufferedImage image,ImageIcon icon) {
        this.image = image;
        this.imageIcon = icon;
    }

    // doing what it should does after some time
    public abstract void doAction();

    // this is a method destructor when object dies
    // to remove the thread according to plant
    public void closeThread(){
        thread.stopThread();
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
