package models;

import helpers.BufferedImages;

public class SnowPeaShooterPlant extends Plant {

    public SnowPeaShooterPlant(Coordinate coordinate){
        super(
                coordinate,
                100,
                1,
                BufferedImages.plant_snowpeashooter
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_snowpeashooter_dying);
    }

    @Override
    public void doAction(){
        // coed to generate frozen pee
        Bullet bullet = new Bullet(
                BufferedImages.bullet_frozen,
                35,
                super.getCoordinate()
        );
        /*
        create thread for moving bullet in Helper folder
        haven't said anything special about bullet speed
        choose whatever you like for sleep time in thread
        something between 0.1 and 1 seconds per state is logical
         */
    }

}
