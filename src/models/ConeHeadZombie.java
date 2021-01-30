package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import views.GameState;

public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(Coordinate coordinate, GameState state){
        super(
                BufferedImages.zombie_conehead,
                coordinate,
                560,
                Configurations.zombieConeHeadDamage,
                Configurations.zombieConeHeadSpeed,
                ImageIcons.zombie_conehead,
                state
        );
    }

    @Override
    public void attack() {

    }
}
