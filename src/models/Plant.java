package models;


import helpers.threads.PlantThread;
import helpers.threads.ThreadPool;


import java.util.ArrayList;

public abstract class Plant {

    private Coordinate coordinate;
    private String imageUri;
    // sun: 50, pea: 70, snowpea: 100, wall-nut: 150, cherrybomb: 70;
    private int health;
    private int actionInterval;
    private boolean isAlive;
    public ArrayList<Bullet> bullet;

    public Plant(String imageUri,Coordinate coordinate,int health,int actionInterval){
        this.imageUri = imageUri;
        this.coordinate = coordinate;
        this.health = health;
        this.actionInterval = actionInterval;
        this.isAlive = true;
        bullet = new ArrayList<Bullet>();

        PlantThread newThread = new PlantThread(this);
        ThreadPool.execute(newThread);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getImageUri() {
        return imageUri;
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
    public void changeImageToDying(String imageUri) {
        this.imageUri = imageUri;
    }

    // doing what it should does after some time
    public abstract void doAction();

}
