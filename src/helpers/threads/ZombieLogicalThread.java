package helpers.threads;

import appStart.GameManagement;
import models.*;
import views.GameState;

import java.util.ArrayList;
import java.util.Iterator;

public class ZombieLogicalThread implements Runnable {

    private Zombie zombie;
    private boolean alive;
    private ZombieGuiThread guiThread;
    private GameState state;

    public ZombieLogicalThread(Zombie zombie,ZombieGuiThread guiThread,GameState state){
        this.zombie = zombie;
        this.alive = true;
        this.guiThread = guiThread;
        this.state = state;
    }

    @Override
    public void run() {
        try{

            while(alive){
                // move the zombie by one coordinate to the left
                /*
                    check if zombie arrived to a plant
                    then break the loop
                    implement this in GameController, Map.java and State.java
                    then call and check here
                */
                Plant[][] plants = state.getPlants();
                Plant plant = checkHitPlant(plants,zombie.getCoordinate());
                if(plant != null){
                    System.out.println("hit plant");
                    guiThread.pauseThread();
                    boolean isCherryBomb = false;
                    if(plant instanceof CherryBombPlant){
                        isCherryBomb = true;
                        // find near zombies and remove them
                        int x = plant.getCoordinate().getAxis_x();
                        int y = plant.getCoordinate().getAxis_y();
                        int min_x = x - 1 < 0 ? 0 : x - 1;
                        int max_x = x + 1 > 8 ? 8 : x + 1;
                        int min_y = y - 1 < 0 ? 0 : y - 1;
                        int max_y = y + 1 > 4 ? 4 : y + 1;
//                        System.out.println("" + min_x + " " + max_x + " " + min_y + " " + max_y);

                        for(int i = min_x;i <= max_x;i++){
                            for(int j = min_y;j <= max_y;j++){
                                if(i != x || j != y) {
//                                    System.out.println("loop " + i + " " + j);
                                    Iterator<Zombie> zombieIterator = state.getZombies().iterator();
                                    while (zombieIterator.hasNext()) {
//                                        System.out.println("zombie");
                                        Zombie zombie = zombieIterator.next();
                                        if (zombie.getCoordinate().getAxis_x() == i && zombie.getCoordinate().getAxis_y() == j) {
                                            zombie.stopThreads();
                                            state.getZombies().remove(zombie);
                                        }
                                    }
                                }
                            }
                        }

                        zombie.stopThreads();
                        state.getZombies().remove(zombie);

                        // removing the plant
                        plants[zombie.getCoordinate().getAxis_x()][zombie.getCoordinate().getAxis_y()] = null;
                    }
                    if(!isCherryBomb) {
                        do {
                            plant.decreaseHealth(zombie.getDamage());
                            System.out.println("eating... " + plant.getHealth());
                            if (plant.getHealth() <= 0) {
                                AudioThreadPool.execute(new AudioPlayer("./sounds/chomp.wav",0.5,false));
                                plant.closeThread();
                                plants[zombie.getCoordinate().getAxis_x()][zombie.getCoordinate().getAxis_y()] = null;
                                break;
                            }
                            Thread.sleep(1000);
                        } while (this.alive);
                    }
                    guiThread.resumeThread();

                }


                // check if zombie arived at a lawn mower
                if(plant == null && zombie.getCoordinate().getAxis_x() <= 0){
//                    synchronized (state.getLawnMowers()){
                    Iterator<LawnMower> it = state.getLawnMowers().iterator();
                    while(it.hasNext()){
                        LawnMower lawnMower = it.next();
                        System.out.println("hit lawn mower");
                        if(lawnMower.getRow() == zombie.getCoordinate().getAxis_y()){
                            alive = false;
                            lawnMower.activate(state);
                            AudioThreadPool.execute(new AudioPlayer("./sounds/lamborghini.wav",3,false));
                            break;
                        }
                    }
//                    }
                }

                if(zombie.getCoordinate().getAxis_x() < 0 || zombie.getLocationX() < 0){
                    zombie.stopThreads();
                    state.getZombies().remove(zombie);
                }

                Thread.sleep((int)(zombie.getSpeed() * 1000));
                zombie.moveOneStateLeft();
            }

            AudioThreadPool.execute(new AudioPlayer("./sounds/chomp.wav",0.5,false));

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    private Plant checkHitPlant(Plant[][] plants,Coordinate coordinate){
        int x = zombie.getCoordinate().getAxis_x();
        int y = zombie.getCoordinate().getAxis_y();
        if(x < 0 || x > 8 || y < 0 || y > 4)
            return null;
        return plants[coordinate.getAxis_x()][coordinate.getAxis_y()];
    }

    public void stopThread(){
        this.alive = false;
    }

}

