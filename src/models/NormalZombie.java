package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import views.GameState;

public class NormalZombie extends Zombie {

    public NormalZombie(Coordinate coordinate, GameState state){
        super(
                BufferedImages.zombie_normal,
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed,
                ImageIcons.zombie_normal,
                state
        );
    }

    @Override
    public void attack() {

    }
}
