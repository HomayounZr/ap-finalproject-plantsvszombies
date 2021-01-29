package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

public class BucketHeadZombie extends Zombie {

    public BucketHeadZombie(Coordinate coordinate,Plant[][] plants){
        super(
                BufferedImages.zombie_buckethead,
                coordinate,
                1300,
                Configurations.zombieBucketHeadDamage,
                Configurations.zombieBucketHeadSpeed,
                ImageIcons.zombie_buckethead,
                plants
        );
    }

    @Override
    public void attack() {

    }

}
