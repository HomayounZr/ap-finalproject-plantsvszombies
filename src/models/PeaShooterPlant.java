package models;

import helpers.BufferedImages;
import helpers.ImageIcons;

import javax.swing.*;

/**
 * An Instantiated Class for Peashooters
 * They Hit Zombies , And Lower the Health Of them
 */

public class PeaShooterPlant extends Plant {
    //  External Field Of Bullet Damage
    private int bulletDamage;

    /**
     * Constructor of the Class
     * @param coordinate Location of the Shooter
     */
    public PeaShooterPlant(Coordinate coordinate){
        super(
                coordinate,
                70,
                1,
                BufferedImages.plant_peashooter,
                ImageIcons.plant_peashooter
        );
    }
    /**
     * A Method For Changing Element Image to Dying Gif
     * Case OF HP == 0
     */

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_peashooter_dying, ImageIcons.plant_peashooter_dying);
    }
    /**
     * Action Method Is Implemented To Shoot Peas At Zombies
     * Using An Instance Of The Bullet Class
     */


    @Override
    public void doAction(){
        // code to generate normal pee
        Bullet bullet = new Bullet(
                BufferedImages.bullet_normal,
                35,
                super.getCoordinate(),
                ImageIcons.bullet_normal,
                0
        );
        bullet.setLocation(super.getLocationX() + 25,super.getLocationY() - 10);
//        synchronized (bullets){
            bullets.add(bullet);
//        }
    }

}
