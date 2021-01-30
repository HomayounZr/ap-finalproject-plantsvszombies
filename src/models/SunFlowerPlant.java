package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

public class SunFlowerPlant extends Plant {

    public SunFlowerPlant(Coordinate coordinate){
        super(
                coordinate,
                50,
                Configurations.sunLoadPlant,
                BufferedImages.plant_sunflower,
                ImageIcons.plant_sunflower
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_sunflower_dying,ImageIcons.plant_sunflower_dying);
    }

    @Override
    public void doAction(){

//        synchronized (suns){
        Sun sun = new Sun(super.getLocationX() + 75,super.getLocationY() - 10);
        suns.add(sun);
//        }

    }

}
