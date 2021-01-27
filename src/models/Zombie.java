package models;

import helpers.threads.ZombieGuiThread;
import helpers.threads.ZombieLogicalThread;
import helpers.threads.ThreadPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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
    private String imageUri;
    // new
    private BufferedImage image;

    // adding x and y for easier rendering in GameCanvas
    private int x;
    private int y;

    public Zombie(String imageUri,Coordinate coordinate,int health, int damage, double speed){
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.coordinate = coordinate;
        this.imageUri = imageUri;

        BufferedImage _image = null;
        try{
            _image = ImageIO.read(new File("./images/Gifs/zombie_normal.gif"));
        } catch (Exception ex){
        }
        this.image = _image;
//        ZombieLogicalThread newThread = new ZombieLogicalThread(this);
//        ThreadPool.execute(newThread);

        ZombieGuiThread guiThread = new ZombieGuiThread(this);
        ThreadPool.execute(guiThread);
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

    public String getImageUri() {
        return imageUri;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void attack();

    public void moveOneStateLeft(){
        setCoordinate(new Coordinate(coordinate.getAxis_x() - 1,coordinate.getAxis_y()));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}