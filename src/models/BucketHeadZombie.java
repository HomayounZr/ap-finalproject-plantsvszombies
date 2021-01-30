package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import views.GameState;

public class BucketHeadZombie extends Zombie {

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
