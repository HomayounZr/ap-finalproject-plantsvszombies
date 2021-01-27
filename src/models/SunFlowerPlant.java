package models;

import appStart.Configurations;
import helpers.BufferedImages;

public class SunFlowerPlant extends Plant {

    public SunFlowerPlant(Coordinate coordinate){
        super(
                coordinate,
                50,
                Configurations.sunLoadPlant,
                BufferedImages.plant_sunflower
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_sunflower_dying);
    }

    @Override
    public void doAction(){
        // code to generate suns
    }

}
