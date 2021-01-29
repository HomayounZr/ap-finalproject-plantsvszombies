package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;

public class NormalZombie extends Zombie {

    public NormalZombie(Coordinate coordinate,Plant[][] plants){
        super(
                BufferedImages.zombie_normal,
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed,
                ImageIcons.zombie_normal,
                plants
        );
    }

    @Override
    public void attack() {

    }
}
