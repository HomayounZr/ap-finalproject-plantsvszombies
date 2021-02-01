package models;

import java.util.ArrayList;

/**
 * This class represents each state in 9*5 map
 */
public class State {

    private Coordinate coordinate;
    private Plant plant;
    private ArrayList<Zombie> zombies;

    /**
     * Constructor Of the Class
     * @param coordinate The Exact Location of the State In the 2DArray
     */
    public State(Coordinate coordinate){
        this.coordinate = coordinate;
        this.zombies = new ArrayList<>();
    }

    /**
     * Getter Method for Coordinates
     * @return Location OF a State
     */

    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Setter Method for Plant in the State
     * @param plant
     */

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    /**
     * Getter Method for Plant in the State
     * Check If their Are Any Plants In The State Or Not, Null If Empty
     */

    public Plant getPlant() {
        return plant;
    }

    /**
     * Adding Zombies in the Given State
     * @param zombie Different Types of Zombies
     */
    public void addZombie(Zombie zombie){
        zombies.add(zombie);
    }

    /**
     * Removing Zombies From Each State that is Needed
     * @param zombie
     */

    public void removeZombie(Zombie zombie){
        zombies.remove(zombie);
    }

    /**
     * Checks if the State Includes Zombie Or Not
     * @return if the State Includes Zombie Or Not
     */

    public Zombie checkHasZombie(){
        if(zombies.size() != 0)
            return zombies.get(0);
        else
            return null;
    }
}
