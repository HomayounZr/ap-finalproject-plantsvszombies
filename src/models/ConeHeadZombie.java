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
    /**
     * This Method Is for the Action of Losing the Cone
     * for the Zombie When Its HP is lower than A Specified Number
     * @param zombie
     * @param imageUri
     */
    public void loseCone(ConeHeadZombie zombie, String imageUri){
        if(zombie.getHealth()<100){
            zombie.setImageUri(imageUri);
        }
    }
    @Override
    public void attack() {

    }
}
