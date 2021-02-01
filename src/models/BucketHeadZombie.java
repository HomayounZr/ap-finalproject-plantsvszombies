package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import views.GameState;

/**
 * This Class Represents BucketHeadZombies
 * Currently the Most PowerFull Zombie
 */

public class BucketHeadZombie extends Zombie {
    /**
     * Constructor of the Class
     * @param coordinate Location of the Zombie
     * @param state State Of the Zombie
     */
    public BucketHeadZombie(Coordinate coordinate, GameState state){
        super(
                BufferedImages.zombie_buckethead,
                coordinate,
                1300,
                Configurations.zombieBucketHeadDamage,
                Configurations.zombieBucketHeadSpeed,
                ImageIcons.zombie_buckethead,
                state
        );
    }

    @Override
    public void attack() {

    }

}
