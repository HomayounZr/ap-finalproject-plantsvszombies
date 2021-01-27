package helpers;

import appStart.Configurations;
import models.Coordinate;
import models.NormalZombie;
import models.Zombie;

import java.security.SecureRandom;
import java.util.ArrayList;

public class ZombieGenerator implements Runnable {

    private ArrayList<Zombie> zombies;
    private int duration;
    private int count;
    private SecureRandom random;

    public ZombieGenerator(ArrayList<Zombie> zombies,int duration,int count){
        this.zombies = zombies;
        this.duration = duration;
        this.count = count;
        this.random = new SecureRandom();
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

                int randomRow = random.nextInt(5);
                for(int i = 0;i < count;i++){
                    Zombie zombie = new NormalZombie(new Coordinate(8,randomRow));
                    zombie.setX(700);
                    zombie.setY(140);
                    zombies.add(zombie);
                }

                Thread.sleep(duration * 1000);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
