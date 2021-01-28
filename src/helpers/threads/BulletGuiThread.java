package helpers.threads;

import models.Bullet;

import java.util.ArrayList;

public class BulletGuiThread implements Runnable {

    private ArrayList<Bullet> bullets;
    public BulletGuiThread(ArrayList<Bullet> bullets){
        this.bullets = bullets;
    }

    @Override
    public void run() {
        try{

            while(true){
                for(Bullet bullet: bullets){
                    bullet.setLocation(
                            bullet.getLocationX() + 19,
                            bullet.getLocationY()
                    );
//                    System.out.println("" + bullet.getLocationX() + " - " + bullet.getLocationY());
                }
                Thread.sleep(20);
            }

        } catch (Exception ex){

        }
    }
}
