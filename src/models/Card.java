package models;

import appStart.Configurations;
import models.enums.PlantType;

import java.lang.module.Configuration;

public class Card {

    private String name;
    private String imageUri;
    private double reloadTime;
    private int sunsNeed;
    private PlantType plantType;
    private boolean isEnable;

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
                imageUri = "./images/Cards/card_sunflower.png";
                reloadTime = Configurations.reloadSunFlower;
                suns = 50;
                break;
            case PEASHOOTER:
                name = "Pea Shooter";
                imageUri = "./images/Cards/card_peashooter.png";
                reloadTime = Configurations.reloadPeaShooter;
                suns = 100;
                break;
            case SNOWPEASHOOTER:
                name = "Snow Pea Shooter";
                imageUri = "./images/Cards/card_freezepeashooter.png";
                reloadTime = Configurations.reloadSnowPeaShooter;
                suns = 175;
                break;
            case GIANTWALLNUT:
                name = "Giant Wall Nut";
                imageUri = "./images/Cards/card_wallnut.png";
                reloadTime = Configurations.reloadWallNut;
                suns = 50;
                break;
            case CHERRYBOMB:
                name = "Cherry Bomb";
                imageUri = "./images/Cards/card_cherrybomb.png";
                reloadTime = Configurations.reloadCherryBomb;
                suns = 150;
                break;
            default:
                break;
        }
        this.name = name;
        this.imageUri = imageUri;
        this.reloadTime = reloadTime;
        this.sunsNeed = suns;
    }

    public int getSunsNeed() {
        return sunsNeed;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getName() {
        return name;
    }

    public void useCard(){
        this.isEnable = false;
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
}
