package models;

import appStart.Configurations;

public class SunFlowerPlant extends Plant {

    public SunFlowerPlant(Coordinate coordinate){
        super(
                "./images/Gifs/sun_flower.gif",
                coordinate,
                50,
                Configurations.sunLoadPlant
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying("./images/Gifs/sun_flower_dying.gif");
    }

    @Override
    public void doAction(){
        // code to generate suns
    }

}
