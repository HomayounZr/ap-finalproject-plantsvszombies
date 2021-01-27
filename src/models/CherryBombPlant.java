package models;

import helpers.BufferedImages;

public class CherryBombPlant extends Plant {

    public CherryBombPlant(Coordinate coordinate){
        super(
                coordinate,
                70,
                1,
                BufferedImages.plant_cherrybomb
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_cherrybomb_dying);
    }

    @Override
    public void doAction(){

    }

}
