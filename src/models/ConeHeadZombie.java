package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(Coordinate coordinate,Plant[][] plants){
        super(
                BufferedImages.zombie_conehead,
                coordinate,
                560,
                Configurations.zombieConeHeadDamage,
                Configurations.zombieConeHeadSpeed,
                ImageIcons.zombie_conehead,
                plants
        );
    }

    @Override
    public void attack() {

    }
}
