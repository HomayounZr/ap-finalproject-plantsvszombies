package models;

import helpers.BufferedImages;
import helpers.ImageIcons;

import javax.swing.*;

public class SnowPeaShooterPlant extends Plant {

    public SnowPeaShooterPlant(Coordinate coordinate){
        super(
                coordinate,
                100,
                1,
                BufferedImages.plant_snowpeashooter,
                ImageIcons.plant_snowpeashooter
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_snowpeashooter_dying,ImageIcons.plant_snowpeashooter_dying);
    }

    @Override
    public void doAction(){
        // coed to generate frozen pee
        Bullet bullet = new Bullet(
                BufferedImages.bullet_frozen,
                35,
                super.getCoordinate(),
                ImageIcons.bullet_frozen,
                1
        );
        bullet.setLocation(super.getLocationX() + 25,super.getLocationY() - 10);
//        synchronized (bullets){
            bullets.add(bullet);
//        }
    }

}
