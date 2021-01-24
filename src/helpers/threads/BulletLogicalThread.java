package helpers.threads;

import appStart.GameManagement;
import models.Bullet;
import models.Zombie;

public class BulletLogicalThread implements Runnable {

    private Bullet bullet;

    public BulletLogicalThread(Bullet bullet){
        this.bullet = bullet;
    }

    @Override
    public void run() {
        try{

            while(true){

                Thread.sleep(1000);

                // check if heated a zombie
                Zombie zombie = GameManagement.gameController.checkHitZombie(bullet.getCoordinate());
                if(zombie != null){
                    // decrease health of first zombie


                    break;
                }

                // move one state to right
                bullet.moveOneStateRight();

            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
