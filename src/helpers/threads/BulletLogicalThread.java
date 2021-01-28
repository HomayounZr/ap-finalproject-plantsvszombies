package helpers.threads;

import appStart.GameManagement;
import models.Bullet;
import models.Zombie;

import java.util.ArrayList;
import java.util.Iterator;

public class BulletLogicalThread implements Runnable {

    private ArrayList<Bullet> bullets;
    private ArrayList<Zombie> zombies;

    public BulletLogicalThread(ArrayList<Bullet> bullets,ArrayList<Zombie> zombies){
        this.zombies = zombies;
        this.bullets = bullets;
    }

    @Override
    public void run() {
        try{

            Iterator<Bullet> bulletIt;
            while(true){

                synchronized (bullets){
                    bulletIt = bullets.iterator();
                    while(bulletIt.hasNext()){
                        Bullet bullet = bulletIt.next();
                        bullet.moveOneStateRight();


                        // check if hit zombie erase bullet
                        Zombie zombie = checkHitZombie(bullet);
                        if(zombie != null){

                            zombie.setHealth(zombie.getHealth() - bullet.getDamage());
                            bullets.remove(bullet);
                            if(zombie.getHealth() <= 0)
                                zombies.remove(zombie);
                        }

                        // check if bullet is at the end of map
                        if(bullet.getCoordinate().getAxis_x() > 8)
                            bullets.remove(bullet);

    //                    System.out.println(bullet.getCoordinate().getAxis_x() + " - " + bullet.getCoordinate().getAxis_y());
                    }
                }


                Thread.sleep(100);

            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private Zombie checkHitZombie(Bullet bullet){
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while(zombieIterator.hasNext()){
            Zombie zombie = zombieIterator.next();
            if(zombie.getCoordinate().equals(bullet.getCoordinate()))
                return zombie;

        }
        return null;
    }
}
