package helpers.threads;

import models.Zombie;

import javax.swing.*;

public class ZombieGuiThread implements Runnable {

//    private JLabel zombieLabel;
    private Zombie zombie;
    private boolean alive;
    private boolean pause;

    public ZombieGuiThread(Zombie zombie){
        this.zombie = zombie;
        this.alive = true;
        this.pause = false;
    }

    @Override
    public void run() {
        try{

            while(alive) {
                System.out.println("" + pause);
                if (!pause) {
                    int speed = 100 / (int) zombie.getSpeed() / 5;
                    zombie.setLocation(zombie.getLocationX() - speed, zombie.getLocationY());
                    Thread.sleep(200);
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stopThread(){
        this.alive = false;
    }

    public void pauseThread(){
        this.pause = true;
    }

    public void resumeThread(){
        this.pause = false;
    }
}
