package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

public class BucketHeadZombie extends Zombie {

    public BucketHeadZombie(Coordinate coordinate){
        super(
                BufferedImages.zombie_buckethead,
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed,
                ImageIcons.zombie_buckethead
        );
    }

    @Override
    public void attack() {

    }

}
