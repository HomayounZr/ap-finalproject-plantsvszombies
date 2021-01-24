package models;

import appStart.Configurations;

public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(Coordinate coordinate){
        super(
                "./images/Gifs/coneheadzombie.gif",
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
