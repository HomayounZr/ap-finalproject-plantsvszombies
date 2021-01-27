package models;

import appStart.Configurations;
import helpers.BufferedImages;

public class NormalZombie extends Zombie {

    public NormalZombie(Coordinate coordinate){
        super(
                BufferedImages.zombie_normal,
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed
        );
    }

    @Override
    public void attack() {

    }
}
