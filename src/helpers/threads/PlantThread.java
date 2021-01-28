package helpers.threads;

import models.Plant;

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
                Thread.sleep(plant.getActionInterval() * 1000);
            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }

    }


}
