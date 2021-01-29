package helpers.threads;

import appStart.GameManagement;
import models.Coordinate;
import models.Plant;
import models.Zombie;

import java.util.ArrayList;

public class ZombieLogicalThread implements Runnable {

    private Zombie zombie;
    private Plant[][] plants;
    private boolean alive;
    private ZombieGuiThread guiThread;

    public ZombieLogicalThread(Zombie zombie,ZombieGuiThread guiThread,Plant[][] plants){
        this.zombie = zombie;
        this.plants = plants;
        this.alive = true;
        this.guiThread = guiThread;
    }

    @Override
    public void run() {
        try{

            while(true){
                // move the zombie by one coordinate to the left
                Thread.sleep((int)(zombie.getSpeed() * 1000));
                zombie.moveOneStateLeft();
                /*
                    check if zombie arrived to a plant
                    then break the loop
                    implement this in GameController, Map.java and State.java
                    then call and check here
                */
//                Plant plant = GameManagement.gameController.checkHitPlant(zombie.getCoordinate());
//                if(plant != null){
                    // start eating the plant

//                    break;
//                }
                Plant plant = checkHitPlant(zombie.getCoordinate());
                if(plant != null){
                    System.out.println("hit plant");
                    guiThread.pauseThread();
                    do{
                        plant.decreaseHealth(zombie.getDamage());
                        System.out.println("eating... " + plant.getHealth());
                        if(plant.getHealth() <= 0){
                            plant.closeThread();
                            plants[zombie.getCoordinate().getAxis_x()][zombie.getCoordinate().getAxis_y()] = null;
                            break;
                        }
                        Thread.sleep(1000);
                    } while (this.alive);
                    guiThread.resumeThread();

                }

                // sleep the thread based on zombie's speed

            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    private Plant checkHitPlant(Coordinate coordinate){
        return plants[coordinate.getAxis_x()][coordinate.getAxis_y()];
    }

    public void stopThread(){
        this.alive = false;
    }

}

