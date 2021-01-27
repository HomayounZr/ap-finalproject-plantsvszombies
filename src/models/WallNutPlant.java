package models;

import helpers.BufferedImages;

public class WallNutPlant extends Plant {

    public WallNutPlant(Coordinate coordinate){
        super(
                coordinate,
                150,
                1,
                BufferedImages.plant_giantwallnut
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_giantwallnut_dying);
    }

    @Override
    public void doAction(){

    }

}
