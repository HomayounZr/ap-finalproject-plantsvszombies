package models;

import java.awt.image.BufferedImage;

public abstract class Plant {

    private Coordinate coordinate;
//    private String imageUri;
    private BufferedImage image;
    // sun: 50, pea: 70, snowpea: 100, wall-nut: 150, cherrybomb: 70;
    private int health;
    private int actionInterval;
    private boolean isAlive;

    private int locationX;
    private int locationY;

    public Plant(Coordinate coordinate,int health,int actionInterval,BufferedImage image){
        this.coordinate = coordinate;
        this.health = health;
        this.actionInterval = actionInterval;
        this.isAlive = true;
        this.image = image;

//        PlantThread newThread = new PlantThread(this);
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
    public void changeImageToDying(BufferedImage image) {
        this.image = image;
    }

    // doing what it should does after some time
    public abstract void doAction();

}
