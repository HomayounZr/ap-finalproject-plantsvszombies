package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.AudioPlayer;
import helpers.threads.AudioThreadPool;

import javax.swing.*;

/**
 * The Class RePresent SnowPeaShooter Is Instantiated From Plant Class
 * Which Shoots Frozen Peas And Slows The Zombies
 */

public class SnowPeaShooterPlant extends Plant {

    //No Extra Fields

    /**
     * Constructor Of The Class
     * @param coordinate Coordinate
     */
    public SnowPeaShooterPlant(Coordinate coordinate){
        super(
                coordinate,
                100,
                1,
                BufferedImages.plant_snowpeashooter,
                ImageIcons.plant_snowpeashooter
        );
    }

    /**
     * A Method For Changing Element Image to Dying Gif
     * Case OF HP == 0
     */
    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_snowpeashooter_dying,ImageIcons.plant_snowpeashooter_dying);
    }

    /**
     * Action Method Is Implemented To Shoot Frozen Peas At Zombies
     * Using An Instance Of The Bullet Class
     */
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
        // playing sound
        if(Configurations.hasSound)
            AudioThreadPool.execute(new AudioPlayer("./sounds/shoot.wav",2,false));

    }

}
