package models;

import helpers.BufferedImages;

public class PeaShooterPlant extends Plant {

    private int bulletDamage;

    public PeaShooterPlant(Coordinate coordinate){
        super(
                coordinate,
                70,
                1,
                BufferedImages.plant_peashooter
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_snowpeashooter_dying);
    }

    @Override
    public void doAction(){
        // code to generate normal pee
        Bullet bullet = new Bullet(
                BufferedImages.bullet_normal,
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
