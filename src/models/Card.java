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
import java.lang.module.Configuration;
import java.nio.Buffer;

public class Card {

    private String name;
//    private String imageUri;
    private double reloadTime;
    private int sunsNeed;
    private PlantType plantType;
    private boolean isEnable;
    private BufferedImage image;
    private ImageIcon imageIcon;

    public Card(PlantType type){
        this.plantType = type;
        setDetails(type);
        this.isEnable = true;
    }

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

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public int getSunsNeed() {
        return sunsNeed;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public String getName() {
        return name;
    }

    public void useCard(){
        this.isEnable = false;
        ThreadPool.execute(new CardReloadThread(this));
    }

    public void enableCard(){
        this.isEnable = true;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public boolean getIsEnable(){
        return isEnable;
    }

    public BufferedImage getImage() {
        return image;
    }
}

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