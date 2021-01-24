package controllers;

import helpers.threads.ThreadPool;
import models.*;

public class GameController {

    private Player player;
    private Map map;

    public GameController(){
//        Configurations.changeToEasy();
        map = new Map();
    }

    public void startGame(){
        ThreadPool.init();
    }

    public void collectSun(int amount){
        player.collectSun(amount);
    }

    public boolean checkStateIsEmpty(int x,int y){
        return map.checkStateIsEmpty(x,y);
    }

    public void addNewPlant(Plant plant){
        map.addNewPlant(plant);
    }

    public void removePlant(int x,int y){
        map.removePlant(x,y);
    }

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
