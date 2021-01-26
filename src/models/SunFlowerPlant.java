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

    public void doAction(SunFlowerPlant sunFlowerPlant){
        Sun sun = new Sun(sunFlowerPlant.getCoordinate());
    }

    @Override
    public void doAction() {

    }
}
