package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.GifHelper;
import helpers.ImageIcons;
import views.GameState;

/**
 * The Second Type of Zombies
 */

public class ConeHeadZombie extends Zombie {
    /**
     * Constructor of the Class
     * @param coordinate Location of the Zombie
     * @param state GameState
     */
    public ConeHeadZombie(Coordinate coordinate, GameState state){
        super(
                BufferedImages.zombie_conehead,
                coordinate,
                560,
                Configurations.zombieConeHeadDamage,
                Configurations.zombieConeHeadSpeed,
                ImageIcons.zombie_conehead,
                GifHelper.zombie_conehead,
                state
        );
    }

    @Override
    public void attack() {

    }
}
