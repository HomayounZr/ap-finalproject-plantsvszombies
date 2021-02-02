package helpers.threads;

import models.Zombie;

import javax.swing.*;

/**
 * this thread is just for moving zombie in Gui
 */
public class ZombieGuiThread implements Runnable {

//    private JLabel zombieLabel;
    // zombie
    private Zombie zombie;
    // when removing zombie
    private boolean alive;
    // pause for eating plant
    private boolean pause;

    /**
     * constructor
     * @param zombie Zombie
     */
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

    /**
     * stop the whole thread when removing zombie
     */
    public void stopThread(){
        this.alive = false;
    }

    /**
     * pause thread when zombie start eating until finished
     */
    public void pauseThread(){
        this.pause = true;
    }

    public void resumeThread(){
        this.pause = false;
    }
}
