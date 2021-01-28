package models;

import helpers.BufferedImages;
import helpers.ImageIcons;

import javax.swing.*;

public class WallNutPlant extends Plant {

    public WallNutPlant(Coordinate coordinate){
        super(
                coordinate,
                150,
                1,
                BufferedImages.plant_giantwallnut,
                ImageIcons.plant_giantwallnut
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_giantwallnut_dying, ImageIcons.plant_giantwallnut_dying);
    }

    @Override
    public void doAction(){

    }

}
