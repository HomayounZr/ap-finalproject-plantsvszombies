package helpers.threads;

import models.Bullet;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This thread it used to move bullets in GameFrame and only Gui
 */
public class BulletGuiThread implements Runnable {

    // list of all bullet in game
    private CopyOnWriteArrayList<Bullet> bullets;

    /**
     * constructor
     * @param bullets CopyOnWriteArrayList Bullet
     */
    public BulletGuiThread(CopyOnWriteArrayList<Bullet> bullets){
        this.bullets = bullets;
    }

    @Override
    public void run() {
        try{

            while(true){
//                synchronized (bullets) {
                // mvoe all bullets to the right by changing locationX
                for (Bullet bullet : bullets) {
                    bullet.setLocation(
                            bullet.getLocationX() + 19,
                            bullet.getLocationY()
                    );
//                    System.out.println("" + bullet.getLocationX() + " - " + bullet.getLocationY());
                }
                Thread.sleep(20);
//                }
            }

        } catch (Exception ex){

        }
    }
}
