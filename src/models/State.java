package models;

import java.util.ArrayList;

/**
 * This class represents each state in 9*5 map
 */
public class State {

    private Coordinate coordinate;
    private Plant plant;
    private ArrayList<Zombie> zombies;

    public State(Coordinate coordinate){
        this.coordinate = coordinate;
        this.zombies = new ArrayList<>();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Plant getPlant() {
        return plant;
    }

    public void addZombie(Zombie zombie){
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie){
        zombies.remove(zombie);
    }

    public Zombie checkHasZombie(){
        if(zombies.size() != 0)
            return zombies.get(0);
        else
            return null;
    }
}
