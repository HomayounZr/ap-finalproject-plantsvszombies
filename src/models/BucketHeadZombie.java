package models;

import appStart.Configurations;

public class BucketHeadZombie extends Zombie {

    public BucketHeadZombie(Coordinate coordinate){
        super(
                "./images/Gifs/bucketheadzombie.gif",
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed);
    }

    @Override
    public void attack() {

    }

}
