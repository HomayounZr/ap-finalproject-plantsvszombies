package models;

import java.util.ArrayList;

/**
 * The Class Presents the Map of the Game
 * Includes a List of Elements and States
 *
 */
public class Map {

    private ArrayList<State> states;
    private ArrayList<Zombie> zombies;
    private ArrayList<Plant> plants;

    /**
     * Constructor of the Class
     * Initiallizing the Lists and Stuff
     */
    public Map(){
        states = new ArrayList<>();
        zombies = new ArrayList<>();
        plants = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 9;j++){
                Coordinate coordinate = new Coordinate(j,i);
                State newState = new State(coordinate);
                states.add(newState);
            }
        }
    }

    /**
     * A Check Method to Know Which a State Is Empty or NOT
     * @param x Horizontal Point
     * @param y Vertical Point
     * @return Boolean True Or False
     */
    public boolean checkStateIsEmpty(int x, int y){
        Coordinate newCoordinate = new Coordinate(x,y);
        for(State state: states){
            if(state.getCoordinate().equals(newCoordinate)){
                if(state.getPlant() != null){
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * A Method For finding Needed State
     * @param x Horizontal Point
     * @param y Vertical Point
     * @return the State That Was Called
     */
    public State findStateByCoodrinate(int x, int y){
        Coordinate newCoordinate = new Coordinate(x,y);
        for(State state: states){
            if(state.getCoordinate().equals(newCoordinate)){
                return state;
            }
        }
        return null;
    }

    /**
     * Adding A Plant To The Given State of the Map
     * @param plant Plant Type
     */
    public void addNewPlant(Plant plant){
        plants.add(plant);
        Coordinate coordinate = plant.getCoordinate();
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        state.setPlant(plant);
    }

    /**
     * Removing Plant From Map By Given Coordinates
     * @param x Horizontal Point
     * @param y Vertical Point
     */
    public void removePlant(int x,int y){
        State state = findStateByCoodrinate(x,y);
        if(state.getPlant() != null){
            state.setPlant(null);
        }
    }

    /**
     * Adding Zombies to a Given State of the Map
     * @param zombie the Type of the Zombie
     */
    public void addNewZombie(Zombie zombie){
        zombies.add(zombie);
        Coordinate coordinate = zombie.getCoordinate();
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        state.addZombie(zombie);
    }

    /**
     * Removing Zombie From Map By Given Coordinates
     * @param zombie Zombie TYPE
     */
    public void removeZombie(Zombie zombie){
        Coordinate coordinate = zombie.getCoordinate();
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        state.removeZombie(zombie);
    }

    /**
     * Check if a State Includes A PLant the There Is Possibility of Engage
     * @param coordinate Location of the Plant
     * @return Plant
     */
    public Plant checkHitPlant(Coordinate coordinate){
        State state = findStateByCoodrinate(coordinate.getAxis_x(), coordinate.getAxis_y());
        return state.getPlant();
    }

    /**
     * Check if a State Includes A Zombie the There Is Possibility of Engage
     * @param coordinate Location of the Plant
     * @return Plant
     */
    public Zombie checkHitZombie(Coordinate coordinate){
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        return state.checkHasZombie();
    }

}
