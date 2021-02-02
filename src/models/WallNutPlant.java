package models;

import helpers.BufferedImages;
import helpers.ImageIcons;

import javax.swing.*;

/**
 * A Class For Implementing The Walnut Element Of the Game
 * The Class Is Inherited From Plant
 */

public class WallNutPlant extends Plant {

    /**
     * Constructor Of The Class
     * @param coordinate The Location Of The WalNut Implementation
     */
    public WallNutPlant(Coordinate coordinate){
        super(
                coordinate,
                150,
                1,
                BufferedImages.plant_giantwallnut,
                ImageIcons.plant_giantwallnut
        );
    }

    /**
     * A Method For Changing Element Image to Dying Gif
     * Case OF HP == 0
     */

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_giantwallnut_dying, ImageIcons.plant_giantwallnut_dying);
    }
    
    /**
     * Doing what it should do after some time
     */
    @Override
    public void doAction(){

    }

}
