package models;

import helpers.BufferedImages;
import helpers.ImageIcons;

import javax.swing.*;

public class PeaShooterPlant extends Plant {

    private int bulletDamage;

    public PeaShooterPlant(Coordinate coordinate){
        super(
                coordinate,
                70,
                1,
                BufferedImages.plant_peashooter,
                ImageIcons.plant_peashooter
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying(BufferedImages.plant_peashooter_dying, ImageIcons.plant_peashooter_dying);
    }

    @Override
    public void doAction(){
        // code to generate normal pee
        Bullet bullet = new Bullet(
                BufferedImages.bullet_normal,
                35,
                super.getCoordinate(),
                ImageIcons.bullet_normal
        );
        bullet.setLocation(super.getLocationX() + 25,super.getLocationY() - 10);
//        synchronized (bullets){
            bullets.add(bullet);
//        }
    }

}
