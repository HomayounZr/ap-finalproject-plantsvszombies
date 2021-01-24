package models;

import helpers.threads.BulletLogicalThread;
import helpers.threads.ThreadPool;

public class Bullet {

    private int damage;
    private Coordinate coordinate;
    private String imageUri;

    public Bullet(String imageUri,int damage,Coordinate coordinate){
        this.damage = damage;
        this.coordinate = coordinate;
        this.imageUri = imageUri;

        BulletLogicalThread newThread = new BulletLogicalThread(this);
        ThreadPool.execute(newThread);
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

    public String getImageUri() {
        return imageUri;
    }

    public int getDamage() {
        return damage;
    }

}
