package helpers.threads;

import models.CherryBombPlant;
import models.Plant;
import models.Zombie;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlantThread implements Runnable {

    private Plant plant;
    private boolean alive;

    public PlantThread(Plant plant){
        this.plant = plant;
        this.alive = true;
    }

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
