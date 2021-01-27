package models;

import appStart.Configurations;
import helpers.BufferedImages;

public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(Coordinate coordinate){
        super(
                BufferedImages.zombie_conehead,
                coordinate,
                200,
                Configurations.zombieConeHeadDamage,
                Configurations.zombieConeHeadSpeed
        );
    }

    @Override
    public void attack() {

    }
}
