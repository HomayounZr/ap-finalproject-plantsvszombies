package helpers.threads;

import appStart.Configurations;
import models.*;
import views.GameState;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ZombieGenerator implements Runnable {

    private CopyOnWriteArrayList<Zombie> zombies;
    private int duration;
    private int count;
    private SecureRandom random;
    private GameState state;
    private boolean running;
    private ArrayList<Integer> validRows;

    public ZombieGenerator(CopyOnWriteArrayList<Zombie> zombies,int duration,int count,GameState state){
        this.zombies = zombies;
        this.duration = duration;
        this.count = count;
        this.random = new SecureRandom();
        this.state = state;
        this.running = true;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int findValidRows(){
        validRows = new ArrayList<>();
        for(LawnMower lawnMower: state.getLawnMowers()){
            validRows.add(lawnMower.getRow());
        }

        // find rows with least plant
        Plant[][] plants = state.getPlants();
        int minRowIndex = validRows.get(0);
        int plantsCount = 0;
        for(int i = 0;i < 9;i++){
            if(plants[i][minRowIndex] != null)
                plantsCount++;
        }
        Iterator<Integer> it = validRows.iterator();
        it.next();
        int i = 1;
        while(it.hasNext()){
            int count = 0;
            if(i >= validRows.size())
                break;
            int row = validRows.get(i);
            for(int j = 0;j < 9;j++){
                if(plants[j][row] != null){
                    count++;
                }
            }
            if(count > plantsCount){
                it.remove();
            } else {
                plantsCount = count;
                minRowIndex = i;
            }
            i++;
        }

        // find row with most zombies
        int rowIndex = 0;
        int zombiesCount = 0;
        for(Zombie zombie: zombies){
            if(zombie.getCoordinate().getAxis_y() == validRows.get(0)){
                zombiesCount++;
            }
        }
        it = validRows.iterator();
        it.next();
        i = 1;
        while(it.hasNext()){
            int count = 0;
            if(i >= validRows.size())
                break;
            for(Zombie zombie: zombies){
                if(zombie.getCoordinate().getAxis_y() == validRows.get(i)){
                    count++;
                }
            }
            if(count < zombiesCount){
                it.remove();
            } else {
                zombiesCount = count;
                rowIndex = i;
            }
            i++;
        }

        int randomRow = random.nextInt(validRows.size());
        return validRows.get(randomRow);
    }

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

                for(int i = 0;i < count;i++){
                    findValidRows();
//                    int randomRow = random.nextInt(5);
//                    while(!isValidRow(randomRow)){
//                        randomRow = random.nextInt(5);
//                    }
                    int randomRow = findValidRows();
                    int locationY = 110 + randomRow * 120;
                    Zombie zombie = null;
                    int zombieType = random.nextInt(3);
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
                        default:
                            break;
                    }
                    zombie.setLocation(900,locationY);
                    zombies.add(zombie);
                }

                AudioThreadPool.execute(new AudioPlayer("./sounds/zombies_coming.wav",3,false));

                Thread.sleep(duration * 1000);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stopThread(){
        this.running = false;
    }
}
