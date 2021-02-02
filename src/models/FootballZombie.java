package models;

import appStart.Configurations;
import helpers.BufferedImages;
import helpers.ImageIcons;
import views.GameState;

/**
 * Football Zombie extending Zombie
 */
public class FootballZombie extends Zombie {
    /**
     * Constructor of the Class
     * @param coordinate Location of the Zombie
     * @param state State Of the Zombie
     */
    public FootballZombie(Coordinate coordinate, GameState state){
        super(
                BufferedImages.zombie_football,
                coordinate,
                1500,
                Configurations.zombieFootballDamage,
                Configurations.zombieFootballSpeed,
                ImageIcons.zombie_football,
                state
        );
    }

    @Override
    public void attack() {

    }
}
