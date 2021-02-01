package controllers;

import helpers.threads.ThreadPool;
import models.*;

/**
 * The Class That Controls The Game
 */
public class GameController {

    private Player player;
    private Map map;

    /**
     * Constructor Of the Class
     */

    public GameController(){
//        Configurations.changeToEasy();
        map = new Map();
    }

    /**
     * Initializing The Game
     */

    public void startGame(){
        ThreadPool.init();
    }

    /**
     * Increasing the Amount Of Suns that Player Has
     * @param amount int Sun
     */

    public void collectSun(int amount){
        player.collectSun(amount);
    }

    /**
     *
     * @param x Horizontal Point
     * @param y Vertical Point
     * @return True Or False of Emptyness of a State
     */

    public boolean checkStateIsEmpty(int x,int y){
        return map.checkStateIsEmpty(x,y);
    }

    /**
     * Adding A Plant To the Map
     * @param plant Plant Type
     */

    public void addNewPlant(Plant plant){
        map.addNewPlant(plant);
    }

    /**
     * Removing a Plant From The Map
     * @param x Horizontal Point
     * @param y Vertical Point
     */

    public void removePlant(int x,int y){
        map.removePlant(x,y);
    }

    /**
     * Adding a Zombie To the Map
     * @param zombie Zombie Type
     */

    public void addNewZombie(Zombie zombie){
        map.addNewZombie(zombie);
    }

    public Plant checkHitPlant(Coordinate coordinate){
        return map.checkHitPlant(coordinate);
    }

    public Zombie checkHitZombie(Coordinate coordinate){
        return map.checkHitZombie(coordinate);
    }

}
