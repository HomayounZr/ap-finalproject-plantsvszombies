package models;

import helpers.threads.BulletLogicalThread;
import helpers.threads.ThreadPool;

import java.awt.image.BufferedImage;

public class Bullet {

    private int damage;
    private Coordinate coordinate;
//    private String imageUri;
    private BufferedImage image;

    private int locationX;
    private int locationY;

    public Bullet(BufferedImage image,int damage,Coordinate coordinate){
        this.damage = damage;
        this.coordinate = coordinate;
        this.image = image;

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
        this.coordinate = new Coordinate(coordinate.getAxis_x() + 1,coordinate.getAxis_y());
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

}
