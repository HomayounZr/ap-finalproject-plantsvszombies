package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.GifHelper;
import helpers.ImageIcons;
import views.GameState;

/**
 * The Normal Zombie Class , Are Just Like the Zombie
 * No Special Ability
 * The Father to all Types Of zombies
 */

public class NormalZombie extends Zombie {

    public NormalZombie(Coordinate coordinate, GameState state){
        super(
                BufferedImages.zombie_normal,
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed,
                ImageIcons.zombie_normal,
//                GifHelper.zombie_normal,
                state
        );
    }

    @Override
    public void attack() {

    }
}
