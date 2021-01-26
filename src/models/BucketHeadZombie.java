package models;

import appStart.Configurations;

import java.awt.*;

public class BucketHeadZombie extends Zombie {

    public BucketHeadZombie(Coordinate coordinate){
        super(
                "./images/Gifs/bucketheadzombie.gif",
                coordinate,
                200,
                Configurations.zombieNormalDamage,
                Configurations.zombieNormalSpeed);
    }

    /**
     * This Method Is for the Action of Losing the Bucket
     * for the Zombie When Its HP is lower than A Specified Number
     * @param zombie
     * @param imageUri
     */
    public void loseBucket(BucketHeadZombie zombie , String imageUri){
        if(zombie.getHealth()<100){
            //The Image Should Be a Gif of A Falling Bucket
            zombie.setImageUri(imageUri);
        }
    }
    @Override
    public void attack() {

    }

}
