package models;

import helpers.BufferedImages;
import helpers.ImageIcons;

/**
 * The Class Present the Killer Type Plant Which Explodes in Zombie Radius
 */

public class CherryBombPlant extends Plant {
    /**
     * Constructor of the Class
     * @param coordinate Location of the Cherry
     */

    public CherryBombPlant(Coordinate coordinate){
        super(
                coordinate,
                70,
                1,
                BufferedImages.plant_cherrybomb,
                ImageIcons.plant_cherrybomb
        );
    }
    /**
     * A Method For Changing Element Image to Dying Gif
     * Case OF HP == 0
     */
    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_cherrybomb_dying,ImageIcons.plant_cherrybomb_dying);
    }

    @Override
    public void doAction(){

    }

}
