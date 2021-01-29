package helpers.threads;

import appStart.GameManagement;
import models.Coordinate;
import models.Plant;
import models.Zombie;

import java.util.ArrayList;

public class ZombieLogicalThread implements Runnable {

    private Zombie zombie;
//    private Plant[][] plants;
    private boolean alive;

    public ZombieLogicalThread(Zombie zombie){
        this.zombie = zombie;
//        this.plants = plants;
        this.alive = true;
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
//                Plant plant = checkHitPlant(zombie.getCoordinate());
//                if(plant != null){
//                    System.out.println("hit plant");
//                    do{
//
//                    } while (this.alive && plant.getIsAlive());
//                }

                // sleep the thread based on zombie's speed

            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    private Plant checkHitPlant(Coordinate coordinate){
//        return plants[coordinate.getAxis_x()][coordinate.getAxis_y()];
        return null;
    }

    public void stopThread(){
        this.alive = false;
    }

}

