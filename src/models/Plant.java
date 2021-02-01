package models;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.PlantThread;
import helpers.threads.ThreadPool;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Class Represents One Of the Key Elements of the GAME
 * The father Class Too All Plant Type Elements
 */

public abstract class Plant implements Serializable {

    private Coordinate coordinate;
//    private String imageUri;
    private transient BufferedImage image;
    private transient ImageIcon imageIcon;
    // sun: 50, pea: 70, snowpea: 100, wall-nut: 150, cherrybomb: 70;
    private int health;
    private int actionInterval;
    private boolean isAlive;

    private int locationX;
    private int locationY;

    protected CopyOnWriteArrayList<Bullet> bullets;
    protected CopyOnWriteArrayList<Sun> suns;

    private transient PlantThread thread;

    /**
     * Constructor Of the Class
     * @param coordinate location of the Plant
     * @param health Hp of the Plant
     * @param actionInterval Coincide Happening
     * @param image Image Of the Plant
     * @param icon Icon Of the Plant
     */
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

    /**
     * Setter location for Plants in the games
     * @param locationX Horizontal Point
     * @param locationY Vertical Point
     */

    public void setLocation(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Adding The Created Elements of the Plants , Suns and Bullets
     * @param suns Sun Created
     * @param bullets Bullet Created
     */

    public void addGameStateValues(CopyOnWriteArrayList<Sun> suns,CopyOnWriteArrayList<Bullet> bullets){
        this.suns = suns;
        this.bullets = bullets;
    }

    /**
     * a Method for Getting The Horizontal Point of the Plant
     * @return Horizontal Point
     */

    public int getLocationX() {
        return locationX;
    }
    /**
     * a Method for Getting The Vertical Point of the Plant
     * @return Vertical Point
     */

    public int getLocationY() {
        return locationY;
    }

    /**
     * A Getter Method For Image of The Plant
     * @return Buffered Image
     */

    public BufferedImage getImage() {
        return image;
    }

    /**
     * A Getter Method for the Coordinates Of the Plant
     * @return Coordinates
     */

    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * If The Plants Coincide In Action
     * @return 1 if The Interval Is True , 0 is Not
     */

    public int getActionInterval() {
        return actionInterval;
    }

    /**
     * This Method Is To Achieve the Fact That If a Plant Is Dead Or Alive
     * @return boolean Dead or Alive
     */
    public boolean getIsAlive(){
        return isAlive;
    }

    /**
     * A Method For Lowering The Hp of The Plant
     * @param amount the Damage of each Element
     */
    public void decreaseHealth(int amount){
        this.health -= amount;
        if(this.health <= 0){
            this.isAlive = false;
            // code to remove the plant
        }
    }
    /**
     * A Method For Changing Element Image to Dying Gif
     * Case OF HP == 0
     */
    public void changeImageToDying(BufferedImage image,ImageIcon icon) {
        this.image = image;
        this.imageIcon = icon;
    }

    /**
     * Doing what it should do after some time
     */
    public abstract void doAction();
    /**
    * This is a method destructor when object dies
    * to remove the thread according to plant
    *
    */
    public void closeThread(){
        thread.stopThread();
    }

    /**
     * A Getter Method For Image Icon
     * @return Image Icon Of the Plant
     */

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * A Method For Receiving How Much The Plants Hp Is
     * @return Plants Hp
     */

    public int getHealth() {
        return health;
    }

    /**
     * A Method for resuming a Saved Game Based of Where Game elements where
     * it builds their thread
     */

    public void resumeObject(){
        if(this instanceof SunFlowerPlant){
            imageIcon = ImageIcons.plant_sunflower;
            image = BufferedImages.plant_sunflower;
        } else if(this instanceof PeaShooterPlant){
            imageIcon = ImageIcons.plant_peashooter;
            image = BufferedImages.plant_peashooter;
        } else if(this instanceof SnowPeaShooterPlant){
            imageIcon = ImageIcons.plant_snowpeashooter;
            image = BufferedImages.plant_snowpeashooter;
        } else if(this instanceof WallNutPlant){
            imageIcon = ImageIcons.plant_giantwallnut;
            image = BufferedImages.plant_giantwallnut;
        } else {
            imageIcon = ImageIcons.plant_cherrybomb;
            image = BufferedImages.plant_cherrybomb;
        }

        thread = new PlantThread(this);
        ThreadPool.execute(thread);
    }

}
