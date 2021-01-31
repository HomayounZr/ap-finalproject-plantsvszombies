package helpers.threads;

import models.Bullet;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class BulletGuiThread implements Runnable {

    private CopyOnWriteArrayList<Bullet> bullets;
    public BulletGuiThread(CopyOnWriteArrayList<Bullet> bullets){
        this.bullets = bullets;
    }

    @Override
    public void run() {
        try{

            while(true){
//                synchronized (bullets) {
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
