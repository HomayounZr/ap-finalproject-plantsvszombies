package helpers.threads;

import models.Plant;

public class PlantThread implements Runnable {

    private Plant plant;

    public PlantThread(Plant plant){
        this.plant = plant;
    }

    @Override
    public void run() {

        try{

            while(plant.getIsAlive()){
                Thread.sleep(plant.getActionInterval());
                plant.doAction();
            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }

    }


}
