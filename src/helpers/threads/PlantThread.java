package helpers.threads;

import models.CherryBombPlant;
import models.Plant;
import models.Zombie;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this thread invoke plant's doAction method based on it's interval
 */
public class PlantThread implements Runnable {

    // plant instance
    private Plant plant;
    // if plant is alive
    private boolean alive;

    /**
     * constructor
     * @param plant Plant
     */
    public PlantThread(Plant plant){
        this.plant = plant;
        this.alive = true;
    }

    /**
     * stop the thread
     */
    public void stopThread(){
        this.alive = false;
    }

    @Override
    public void run() {

        try{

            while(alive){
                plant.doAction();
                if(plant instanceof CherryBombPlant){
                    // find near zombies
                    // and remove them
                    // then remove the plant it self
                }
                Thread.sleep(plant.getActionInterval() * 1000);
            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }

    }


}
