package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.ThreadPool;
import models.enums.PlantType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.lang.module.Configuration;
import java.nio.Buffer;

/**
 * represent a card
 */
public class Card implements Serializable {

    // name
    private String name;
//    private String imageUri;
    // time to reload
    private double reloadTime;
    // required suns
    private int sunsNeed;
    // plant type
    private PlantType plantType;
    // can be selected
    private boolean isEnable;
    // buffered image
    private transient BufferedImage image;
    // image icons
    private transient ImageIcon imageIcon;

    /**
     * constructor
     * @param type enum PlantType
     */
    public Card(PlantType type){
        this.plantType = type;
        setDetails(type);
        this.isEnable = true;
    }

    /*
    find card details bsaed on plant type
     */
    private void setDetails(PlantType type){
        String name = "";
        String imageUri = "";
        double reloadTime = 0;
        int suns = 0;
        switch (type){
            case SUNFLOWER:
                name = "Sun flower";
                image = BufferedImages.card_sunflower;
                imageIcon = ImageIcons.card_sunflower;
                reloadTime = Configurations.reloadSunFlower;
                suns = 50;
                break;
            case PEASHOOTER:
                name = "Pea Shooter";
                image = BufferedImages.card_peashooter;
                imageIcon = ImageIcons.card_peashooter;
                reloadTime = Configurations.reloadPeaShooter;
                suns = 100;
                break;
            case SNOWPEASHOOTER:
                name = "Snow Pea Shooter";
                image = BufferedImages.card_snowpeashooter;
                imageIcon = ImageIcons.card_snowpeashooter;
                reloadTime = Configurations.reloadSnowPeaShooter;
                suns = 175;
                break;
            case GIANTWALLNUT:
                name = "Giant Wall Nut";
                image = BufferedImages.card_giantwallnut;
                imageIcon = ImageIcons.card_giantwallnut;
                reloadTime = Configurations.reloadWallNut;
                suns = 50;
                break;
            case CHERRYBOMB:
                name = "Cherry Bomb";
                image = BufferedImages.card_cherrybomb;
                imageIcon = ImageIcons.card_cherrybomb;
                reloadTime = Configurations.reloadCherryBomb;
                suns = 150;
                break;
            default:
                break;
        }
        this.name = name;
        this.reloadTime = reloadTime;
        this.sunsNeed = suns;
    }

    /**
     * get image icon
     * @return ImageIcon
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * get required suns
     * @return int
     */
    public int getSunsNeed() {
        return sunsNeed;
    }

    /**
     * get realod time
     * @return double
     */
    public double getReloadTime() {
        return reloadTime;
    }

    /**
     * get name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * method to disable for a while
     * when using it
     */
    public void useCard(){
        this.isEnable = false;
        ThreadPool.execute(new CardReloadThread(this));
    }

    /**
     * enable card again for next use
     */
    public void enableCard(){
        this.isEnable = true;
    }

    /**
     * get plant type
     * @return PlantType
     */
    public PlantType getPlantType() {
        return plantType;
    }

    /**
     *
     * @return
     */
    public boolean getIsEnable(){
        return isEnable;
    }

    /**
     * get Image
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * this method is for re-rendering saved object
     * and re-run it's threads when load a previous
     * saved game
     */
    public void resumeObject(){
        switch (plantType){
            case SUNFLOWER:
                image = BufferedImages.card_sunflower;
                imageIcon = ImageIcons.card_sunflower;
                break;
            case PEASHOOTER:
                image = BufferedImages.card_peashooter;
                imageIcon = ImageIcons.card_peashooter;
                break;
            case SNOWPEASHOOTER:
                image = BufferedImages.card_snowpeashooter;
                imageIcon = ImageIcons.card_snowpeashooter;
                break;
            case GIANTWALLNUT:
                image = BufferedImages.card_giantwallnut;
                imageIcon = ImageIcons.card_giantwallnut;
                break;
            case CHERRYBOMB:
                image = BufferedImages.card_cherrybomb;
                imageIcon = ImageIcons.card_cherrybomb;
                break;
            default:
                break;
        }
        if(!this.isEnable){
            isEnable = true;
        }
    }

}

/**
 * Thread for enabling and disabling a card after using it
 */
class CardReloadThread implements Runnable{
    private Card card;
    public CardReloadThread(Card card){
        this.card = card;
    }

    @Override
    public void run() {
        try{
            Thread.sleep((int)(card.getReloadTime() * 1000));
            card.enableCard();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}