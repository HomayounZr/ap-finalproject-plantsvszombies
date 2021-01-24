package models;

import appStart.Configurations;

public class NormalZombie extends Zombie {

    public NormalZombie(Coordinate coordinate){
        super(
                "./images/Gifs/zombie_normal.gif",
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
