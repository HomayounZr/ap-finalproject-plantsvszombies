package helpers.threads;

import appStart.GameManagement;
import model.Plant;
import model.Zombie;

public class ZombieLogicalThread implements Runnable {

    private Zombie zombie;

    public ZombieLogicalThread(Zombie zombie){
        this.zombie = zombie;
    }

    @Override
    public void run() {
        try{

            while(true){
                // move the zombie by one coordinate to the left
                zombie.moveOneStateLeft();
                /*
                    check if zombie arrived to a plant
                    then break the loop
                    implement this in GameController, Map.java and State.java
                    then call and check here
                */
                Plant plant = GameManagement.gameController.checkHitPlant(zombie.getCoordinate());
                if(plant != null){
                    // start eating the plant

                    break;
                }

                // sleep the thread based on zombie's speed
                Thread.sleep((long)(zombie.getSpeed() * 1000));

            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
