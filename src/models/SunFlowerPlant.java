package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

/**
 * Class Represents The SunFlower Elements
 * Instantiated From Plant Class
 * Loads Its Image And Health And Coordinate
 */

public class SunFlowerPlant extends Plant {
    /**
     * Constructor Of The Class
     * @param coordinate Location Of THE Plant
     */

    public SunFlowerPlant(Coordinate coordinate){
        super(
                coordinate,
                50,
                Configurations.sunLoadPlant,
                BufferedImages.plant_sunflower,
                ImageIcons.plant_sunflower
        );
    }
    /**
     * A Method For Changing Element Image to Dying Gif
     * Case OF HP == 0
     */
    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_sunflower_dying,ImageIcons.plant_sunflower_dying);
    }

    /**
     * The Action Method Created New Suns In the Game MAP Around The Sunflower
     */

    @Override
    public void doAction(){

//        synchronized (suns){
            Sun sun = new Sun(super.getLocationX() + 75,super.getLocationY() - 10);
            suns.add(sun);
//        }

    }

}
