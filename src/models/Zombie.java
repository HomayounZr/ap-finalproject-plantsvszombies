package models;

import Helpers.Threads.ThreadPool;
import Helpers.Threads.ZombieLogicalThread;

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

    public Zombie(String imageUri,Coordinate coordinate,int health, int damage, double speed){
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.coordinate = coordinate;
        this.imageUri = imageUri;

        ZombieLogicalThread newThread = new ZombieLogicalThread(this);
        ThreadPool.execute(newThread);
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

    public int getDamage() {
        return damage;
    }

    public abstract void attack();

    public void moveOneStateLeft(){
        setCoordinate(new Coordinate(coordinate.getAxis_x() - 1,coordinate.getAxis_y()));
    }

}