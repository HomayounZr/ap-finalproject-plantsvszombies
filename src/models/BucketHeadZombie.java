package models;

import appStart.Configurations;
import helpers.BufferedImages;

public class BucketHeadZombie extends Zombie {

    public BucketHeadZombie(Coordinate coordinate){
        super(
                BufferedImages.zombie_buckethead,
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed);
    }

    @Override
    public void attack() {

    }

}
