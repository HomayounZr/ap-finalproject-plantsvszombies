package helpers.threads;

import models.Zombie;

import javax.swing.*;

public class ZombieGuiThread implements Runnable {

//    private JLabel zombieLabel;
    private Zombie zombie;
    private boolean alive;

    public ZombieGuiThread(Zombie zombie){
        this.zombie = zombie;
        this.alive = true;
    }

    @Override
    public void run() {
        try{

            while(alive){
                zombie.setLocation(zombie.getLocationX() - 5, zombie.getLocationY());
                Thread.sleep(200);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stopThread(){
        this.alive = false;
    }
}
