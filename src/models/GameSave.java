package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This Class Saves The Game
 * The Fields Are The Elements that Need TO be Saved as Data
 */

public class GameSave implements Serializable {

    // all cards
    private ArrayList<Card> cards;
    // plants array
    private Plant[][] plants;
    // all zombies
    private CopyOnWriteArrayList<Zombie> zombies;
    // all suns
    private CopyOnWriteArrayList<Sun> suns;
    // all lawn mowers
    private CopyOnWriteArrayList<LawnMower> lawnMowers;
    // all bullets
    private CopyOnWriteArrayList<Bullet> bullets;
    // all player's uns
    private int playerSuns;
    // int passed time since last stage
    private int timePassedLastStage;
    // last stage
    private int lastStage;

    /**
     * Constructor of the Game
     * @param cards Cards
     * @param plants List of Plants
     * @param zombies list of Zombies
     * @param suns List of Suns
     * @param lawnMowers List of LawnMowers
     * @param bullets List of Bullets
     * @param playerSuns Amount of Suns that the Player Had
     * @param timePassedLastStage Passed time of the Stage
     * @param lastStage What Last Stage Was
     */

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
    /**
     * Returning the List of Cards
     * @return list of Cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Returning the List of Plants in 5x9
     * @return list of Plants
     */

    public Plant[][] getPlants() {
        return plants;
    }
    /**
     * making a fresh copy of the underlying array Zombies
     * @return List of Bullets
     */

    public CopyOnWriteArrayList<Zombie> getZombies() {
        return zombies;
    }
    /**
     * making a fresh copy of the underlying array Suns
     * @return List of Bullets
     */

    public CopyOnWriteArrayList<Sun> getSuns() {
        return suns;
    }
    /**
     * making a fresh copy of the underlying array LawnMowers
     * @return List of Bullets
     */

    public CopyOnWriteArrayList<LawnMower> getLawnMowers() {
        return lawnMowers;
    }

    /**
     * making a fresh copy of the underlying array Bullets
     * @return List of Bullets
     */

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Getter Method for Suns that Player Had Last Time
     * @return amount of player Suns
     */

    public int getPlayerSuns() {
        return playerSuns;
    }

    /**
     * Getter Method for Time that Player Had Played His/Her Last Stage Last Time
     * @return amount of Time
     */

    public int getTimePassedLastStage() {
        return timePassedLastStage;
    }

    /**
     * Getter Method for The Stage that the Player Was In Last Time
     * @return The Last Stage
     */

    public int getLastStage() {
        return lastStage;
    }

}
