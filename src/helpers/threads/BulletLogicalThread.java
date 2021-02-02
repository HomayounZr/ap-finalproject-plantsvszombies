package helpers.threads;

import appStart.Configurations;
import appStart.GameManagement;
import models.Bullet;
import models.Zombie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This thread is for moving bullets on state by time unit
 * and checks for all logical possibilities to hit a zombie
 */
public class BulletLogicalThread implements Runnable {

    // all bullets
    private CopyOnWriteArrayList<Bullet> bullets;
    // all zombies
    private CopyOnWriteArrayList<Zombie> zombies;

    /**
     * constructor
     * @param bullets CopyOnWriteArrayList Bullet
     * @param zombies CopyOnWriteArrayList Zombie
     */
    public BulletLogicalThread(CopyOnWriteArrayList<Bullet> bullets,CopyOnWriteArrayList<Zombie> zombies){
        this.zombies = zombies;
        this.bullets = bullets;
    }

    @Override
    public void run() {
        try{

            Iterator<Bullet> bulletIt;
            while(true){

//                synchronized (bullets){
//                    System.out.println("" + bullets.size());
                bulletIt = bullets.iterator();
                while(bulletIt.hasNext()){
                    Bullet bullet = bulletIt.next();
                    bullet.moveOneStateRight();
                    // check if hit zombie erase bullet
                    Zombie zombie = checkHitZombie(bullet);
                    if(zombie != null){
                        System.out.println("hit zombie");

                        zombie.setHealth(zombie.getHealth() - bullet.getDamage());
                        // half the speed if a frozen pee hits zombie
                        if(bullet.getDamage() == 35){
                            if(zombie.getSpeed() == Configurations.zombieConeHeadSpeed || zombie.getSpeed() == Configurations.zombieFootballSpeed || zombie.getSpeed() == Configurations.zombieNormalSpeed){
                                zombie.halfSpeed();
                            }
                        }
                        bullets.remove(bullet);
                        if(zombie.getHealth() <= 0){
                            zombie.stopThreads();
                            zombies.remove(zombie);
                        }

//                      System.out.println(zombie.getHealth() + " - " + bullet.getCoordinate().getAxis_x());
                    }

                    // check if bullet is at the end of map
                    if(bullet.getCoordinate().getAxis_x() > 8 || bullet.getLocationX() > 1000)
                        bullets.remove(bullet);

    //              System.out.println(bullet.getCoordinate().getAxis_x() + " - " + bullet.getCoordinate().getAxis_y());
                }
//              }
                Thread.sleep(100);

            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // check coordinate of bullet to find if hit a zombie
    // then return zombie of that coordinate
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
