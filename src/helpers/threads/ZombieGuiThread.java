package helpers.threads;

import models.Zombie;

import javax.swing.*;

public class ZombieGuiThread implements Runnable {

//    private JLabel zombieLabel;
    private Zombie zombie;

    public ZombieGuiThread(Zombie zombie){
        this.zombie = zombie;
    }

    @Override
    public void run() {
        try{

            while(true){
                zombie.setX(zombie.getX() - 1);
                Thread.sleep(100);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
