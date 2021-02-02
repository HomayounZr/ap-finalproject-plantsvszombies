package helpers.threads;

import appStart.Configurations;
import models.*;
import views.GameState;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this thread is used for creating new zombies
 * based on stage duration whe give it
 */
public class ZombieGenerator implements Runnable {

    // all zombies
    private CopyOnWriteArrayList<Zombie> zombies;
    // duration to create new zombies
    private int duration;
    // count of zombies to make
    private int count;
    // random generator
    private SecureRandom random;
    // game state
    private GameState state;
    private boolean running;
    // valid rows to generate zombies
    private ArrayList<Integer> validRows;

    /**
     * constructor
     * @param zombies CopyOnWriteArrayList Zombie
     * @param duration int
     * @param count int
     * @param state GameState
     */
    public ZombieGenerator(CopyOnWriteArrayList<Zombie> zombies,int duration,int count,GameState state){
        this.zombies = zombies;
        this.duration = duration;
        this.count = count;
        this.random = new SecureRandom();
        this.state = state;
        this.running = true;
    }

    /**
     * set new duration when stage changes
     * @param duration int
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * set new count when stage changes
     * @param count int
     */
    public void setCount(int count) {
        this.count = count;
    }

    /*
    find valid row to generate zombies each time
    Smart Zombie Generator
     */
//    private synchronized int findValidRows(){
//        // find rows whit lawn mower
//        validRows = new ArrayList<>();
//        for(LawnMower lawnMower: state.getLawnMowers()){
//            validRows.add(lawnMower.getRow());
//        }
//
//        // find rows with least plant
//        Plant[][] plants = state.getPlants();
//        int minRowIndex = validRows.get(0);
//        int plantsCount = 0;
//        for(int i = 0;i < 9;i++){
//            if(plants[i][minRowIndex] != null)
//                plantsCount++;
//        }
//        Iterator<Integer> it = validRows.iterator();
//        it.next();
//        int i = 1;
//        while(it.hasNext()){
//            int count = 0;
//            if(i >= validRows.size())
//                break;
//            int row = validRows.get(i);
//            for(int j = 0;j < 9;j++){
//                if(plants[j][row] != null){
//                    count++;
//                }
//            }
//            if(count < plantsCount){
//                plantsCount = count;
//                minRowIndex = row;
//            }
//            i++;
//            it.next();
//        }
//
////        it = validRows.iterator();
////        while()
//
//        // find row with most zombies
//        int rowIndex = validRows.get(0);
//        int zombiesCount = 0;
//        for(Zombie zombie: zombies){
//            if(zombie.getCoordinate().getAxis_y() == validRows.get(0)){
//                zombiesCount++;
//            }
//        }
//        it = validRows.iterator();
////        it.next();
//        i = 0;
//        while(it.hasNext()){
//            int count = 0;
//            if(i >= validRows.size())
//                break;
//            for(Zombie zombie: zombies){
//                if(zombie.getCoordinate().getAxis_y() == validRows.get(i)){
//                    count++;
//                }
//            }
//            if(count < zombiesCount){
//                it.remove();
//            } else if(count >= zombiesCount) {
//                zombiesCount = count;
//                rowIndex = i;
////                validRows.remove(i);
//            }
//            i++;
//            it.next();
//        }
//
//        // select a random row from valid ones
//        int randomRow = random.nextInt(validRows.size());
//        return validRows.get(randomRow);
//    }

    /*
    find a row with this specifications

     */
    private synchronized int findVaildRows(){
        validRows = new ArrayList<>();
        for(LawnMower lawnMower: state.getLawnMowers()){
            validRows.add(lawnMower.getRow());
        }

        int minPlantRowIndex = validRows.get(0);
        int minPlantsInRow = 0;
        Plant[][] plants = state.getPlants();
        for(int i = 0;i < 9;i++){
            if(plants[i][minPlantRowIndex] != null){
                minPlantsInRow++;
            }
        }
        for(Integer row: validRows){
            int newPlantsInRow = 0;
            for(int j = 0;j < 9;j++){
                if(plants[j][row] != null){
                    newPlantsInRow++;
                }
            }
            if(newPlantsInRow < minPlantsInRow){
                minPlantsInRow = newPlantsInRow;
                minPlantRowIndex = row;
            }
        }
        Iterator<Integer> it = validRows.iterator();
        while(it.hasNext()){
            int row = it.next();
            int newPlantsInRow = 0;
            for(int j = 0;j < 9;j++){
                if(plants[j][row] != null){
                    newPlantsInRow++;
                }
            }
            if(newPlantsInRow > minPlantsInRow){
                it.remove();
            }
        }

        int maxZombiesRowIndex = validRows.get(0);
        int maxZombiesInRow = 0;
        for(Zombie zombie: zombies){
            if(zombie.getCoordinate().getAxis_y() == maxZombiesRowIndex){
                maxZombiesInRow++;
            }
        }
        for(Integer row: validRows){
            int newZombiesInRow = 0;
            for(Zombie zombie: zombies){
                if(zombie.getCoordinate().getAxis_y() == row){
                    newZombiesInRow++;
                }
            }
            if(newZombiesInRow > maxZombiesInRow){
                maxZombiesInRow = newZombiesInRow;
                maxZombiesRowIndex = row;
            }
        }
        it = validRows.iterator();
        while(it.hasNext()){
            int row = it.next();
            int newZombiesInRow = 0;
            for(Zombie zombie: zombies){
                if(zombie.getCoordinate().getAxis_y() == row){
                    newZombiesInRow++;
                }
            }
            if(newZombiesInRow < maxZombiesInRow){
                it.remove();
            }
        }

        // select a random row from valid ones
        int randomRow = random.nextInt(validRows.size());
        return validRows.get(randomRow);
    }

    // check if selected row is valid
    private boolean isValidRow(int _row){
        for(Integer row: validRows){
            if(row == _row)
                return true;
        }
        return false;
    }

    @Override
    public void run() {
        try{

            while(running){

                // playing sound
                if(Configurations.hasSound)
                    AudioThreadPool.execute(new AudioPlayer("./sounds/zombies_coming.wav",3,false));
                for(int i = 0;i < count;i++){
                    // refresh valid rows each time
//                    findValidRows();
//                    int randomRow = random.nextInt(5);
//                    while(!isValidRow(randomRow)){
//                        randomRow = random.nextInt(5);
//                    }
                    int randomRow = findVaildRows();
                    System.out.println("" + validRows.size() + " - " + randomRow);
                    int locationY = 110 + randomRow * 120;
                    Zombie zombie = null;
                    // select a random number for new zombie type
                    int zombieType = random.nextInt(4);
                    switch (zombieType){
                        case 0:
                            zombie = new NormalZombie(new Coordinate(8,randomRow),state);
                            break;
                        case 1:
                            zombie = new ConeHeadZombie(new Coordinate(8,randomRow),state);
                            break;
                        case 2:
                            zombie = new BucketHeadZombie(new Coordinate(8,randomRow),state);
                            break;
                        case 3:
                            zombie = new FootballZombie(new Coordinate(8,randomRow),state);
                        default:
                            break;
                    }
                    zombie.setLocation(900,locationY);
                    zombies.add(zombie);
                    Thread.sleep(2000);
                }

                Thread.sleep(duration * 1000 - 2000 * (count - 1));
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * stop the thread
     */
    public void stopThread(){
        this.running = false;
    }
}
