package models;

import java.util.ArrayList;

public class Map {

    private ArrayList<State> states;
    private ArrayList<Zombie> zombies;
    private ArrayList<Plant> plants;

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

    public State findStateByCoodrinate(int x, int y){
        Coordinate newCoordinate = new Coordinate(x,y);
        for(State state: states){
            if(state.getCoordinate().equals(newCoordinate)){
                return state;
            }
        }
        return null;
    }

    public void addNewPlant(Plant plant){
        plants.add(plant);
        Coordinate coordinate = plant.getCoordinate();
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        state.setPlant(plant);
    }

    public void removePlant(int x,int y){
        State state = findStateByCoodrinate(x,y);
        if(state.getPlant() != null){
            state.setPlant(null);
        }
    }

    public void addNewZombie(Zombie zombie){
        zombies.add(zombie);
        Coordinate coordinate = zombie.getCoordinate();
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        state.addZombie(zombie);
    }

    public void removeZombie(Zombie zombie){
        Coordinate coordinate = zombie.getCoordinate();
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        state.removeZombie(zombie);
    }

    public Plant checkHitPlant(Coordinate coordinate){
        State state = findStateByCoodrinate(coordinate.getAxis_x(), coordinate.getAxis_y());
        return state.getPlant();
    }

    public Zombie checkHitZombie(Coordinate coordinate){
        State state = findStateByCoodrinate(coordinate.getAxis_x(),coordinate.getAxis_y());
        return state.checkHasZombie();
    }

}
