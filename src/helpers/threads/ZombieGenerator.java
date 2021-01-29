package helpers.threads;

import appStart.Configurations;
import models.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ZombieGenerator implements Runnable {

    private CopyOnWriteArrayList<Zombie> zombies;
    private int duration;
    private int count;
    private SecureRandom random;
    private Plant[][] plants;

    public ZombieGenerator(CopyOnWriteArrayList<Zombie> zombies,int duration,int count,Plant[][] plants){
        this.zombies = zombies;
        this.duration = duration;
        this.count = count;
        this.random = new SecureRandom();
        this.plants = plants;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try{

            while(true){

                for(int i = 0;i < count;i++){
                    int randomRow = random.nextInt(5);
                    int locationY = 110 + randomRow * 120;
                    Zombie zombie = null;
                    int zombieType = random.nextInt(3);
                    switch (zombieType){
                        case 0:
                            zombie = new NormalZombie(new Coordinate(8,randomRow),plants);
                            break;
                        case 1:
                            zombie = new ConeHeadZombie(new Coordinate(8,randomRow),plants);
                            break;
                        case 2:
                            zombie = new BucketHeadZombie(new Coordinate(8,randomRow),plants);
                            break;
                        default:
                            break;
                    }
                    zombie.setLocation(900,locationY);
                    zombies.add(zombie);
                }

                Thread.sleep(duration * 1000);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
