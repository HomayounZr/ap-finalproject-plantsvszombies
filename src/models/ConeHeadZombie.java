package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(Coordinate coordinate){
        super(
                BufferedImages.zombie_conehead,
                coordinate,
                200,
                Configurations.zombieConeHeadDamage,
                Configurations.zombieConeHeadSpeed,
                ImageIcons.zombie_conehead
        );
    }

    @Override
    public void attack() {

    }
}
