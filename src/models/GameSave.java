package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameSave implements Serializable {

    private ArrayList<Card> cards;
    private Plant[][] plants;
    private CopyOnWriteArrayList<Zombie> zombies;
    private CopyOnWriteArrayList<Sun> suns;
    private CopyOnWriteArrayList<LawnMower> lawnMowers;
    private CopyOnWriteArrayList<Bullet> bullets;

    private int playerSuns;
    private int timePassedLastStage;
    private int lastStage;

    public GameSave(
            ArrayList<Card> cards,
            Plant[][] plants,
            CopyOnWriteArrayList<Zombie> zombies,
            CopyOnWriteArrayList<Sun> suns,
            CopyOnWriteArrayList<LawnMower> lawnMowers,
            CopyOnWriteArrayList<Bullet> bullets,
            int playerSuns,
            int timePassedLastStage,
            int lastStage
    ){
        this.cards = cards;
        this.plants = plants;
        this.zombies=  zombies;
        this.suns = suns;
        this.lawnMowers = lawnMowers;
        this.bullets = bullets;
        this.playerSuns = playerSuns;
        this.timePassedLastStage = timePassedLastStage;
        this.lastStage = lastStage;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Plant[][] getPlants() {
        return plants;
    }

    public CopyOnWriteArrayList<Zombie> getZombies() {
        return zombies;
    }

    public CopyOnWriteArrayList<Sun> getSuns() {
        return suns;
    }

    public CopyOnWriteArrayList<LawnMower> getLawnMowers() {
        return lawnMowers;
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getPlayerSuns() {
        return playerSuns;
    }

    public int getTimePassedLastStage() {
        return timePassedLastStage;
    }

    public int getLastStage() {
        return lastStage;
    }

}
