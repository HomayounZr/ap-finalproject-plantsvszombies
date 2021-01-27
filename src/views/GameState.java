package views;

import models.*;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GameState {

    private Coordinate coordinate;
    // Fix A Number For The Initial Amount of Suns
    private int totalSun;
    private LawnMower[] lawnMowers;
    private Plant[][] plants;
    //For Checking the Presence of Zombies in Row , Good Help For Shooting Bullets
    private boolean[] zombieInRow;
    private ArrayList<Zombie> zombieList;
    public static boolean gameOver = false;
    private Iterator<Plant> plantIterator;
    private int gameType;
    //0 for the Easy
    //1 for the Hard
    private int waveCounter;
    // Might Need A Frame Counter
    private int frameCounter = 0;
    //Defining a MouseHandler Based of Template, No KeyHandlers Might Be Needed
    private MouseHandler mouseHandler;

    public GameState(){
        totalSun = 100;
        plants = new Plant[5][9];
        zombieInRow = new boolean[5];
        for (int i = 0; i < 5; i++) {
            zombieInRow[i] = false;
        }
        mouseHandler = new MouseHandler();
        //Don't get What The Problem Is

    }

    /**
     * The Whole Game Runs On this , First Implement other Methods like Gameover , Hit , Attack and ...
     */
    public void update(){
        while (!gameOver){
            gameOver();
            if (frameCounter == 0) {
                waveCounter++;
                System.out.println("Wave :" + waveCounter );
            }
            //Zombie Location
            int x;
            int y;
            //Plant Location
            int plantLocX, plantLocY;
            frameCounter++;
            stop();

        }

    }

    /**
     * Zombie Collision Handled
     */
    public void stop() {

        for (Zombie z : zombieList) {
            boolean stoppedByAnother;
            if (z.stoppedByAnother) {
                z.zombieMove = true;
                z.stoppedByAnother = false;
            }
        }

        for (Zombie x : zombieList) {
            for (Zombie y : zombieList) {
                if (!x.zombieMove && y.zombieMove && x.getCoordinate().getAxis_y() + 40 == y.getCoordinate().getAxis_x() && x.getCoordinate().getAxis_x() == y.getCoordinate().getAxis_y()) {
                    y.zombieMove = false;
                    y.stoppedByAnother = true;
                }
                if (x.counter > 0 && x.getCoordinate().getAxis_y() + 40 == y.getCoordinate().getAxis_x() && x.getCoordinate().getAxis_x() == y.getCoordinate().getAxis_y()) {
                    y.zombieMove = false;
                    y.stoppedByAnother = true;
                }
            }
        }
    }

    /**
     * A Method For Checking Game Ending Situations
     * If The Zombies Reach To End of The Map , U Lose
     */
    public void gameOver() {
        for (Zombie z : zombieList) {
            if (z.getCoordinate().getAxis_x() == 0) {
                gameOver = true;
            }
        }
    }

    /**
     * A Method For Returning The Amount of Suns That Players Have
     * @return Total Sun
     */
    public int getTotalSun() {
        return totalSun;
    }
    /**
     * A Method to Know What Kinds Of Zombies Have Entered the Map
     *
     * @return ArrayList OF Zombies
     */
    public ArrayList<Zombie> getZombieList()
    {
        return zombieList;
    }

    /**
     * A Method For Knowing What Location of The Map Are Occupied
     * @return Plants
     */

    public Plant[][] getPlants() {
        return plants;
    }
    public LawnMower[] getLawnMowers(){
        return lawnMowers;
    }

    /**
     * The Next 2 Methods Are For the Attacking Effects on Zombies And Plants
     * First The Zombie
     */
    public void zombieLoseHP(Zombie zombie , Bullet bullet){
        if(bullet.getCoordinate() == zombie.getCoordinate()){
            //Added A SetHealth Method in Zombie Class
            // The 50 is a Prompt
            zombie.setHealth(zombie.getHealth() - 50);
        }
        //Find A Way to Delete The Bullet After The Hit
    }

    /**
     * A Method For Loss of Hp in Plants
     */
    public void plantLoseHp(Zombie zombie, Plant plant) {
        if(zombie.getCoordinate() == plant.getCoordinate()){
            //In This Place U Should Stop The Zombie From Moving
            if(zombie instanceof BucketHeadZombie){
                plant.decreaseHealth(50);
            }
            if(zombie instanceof ConeHeadZombie){
                plant.decreaseHealth(75);
            }
            if(zombie instanceof NormalZombie){
                plant.decreaseHealth(25);
            }
        }
    }

    /**
     * The Next 3 Method Are For Clearing the Map Of Elements
     * Preferably Used in The Start of a New Game
     *
     *
     * A Method for Clearing the Map Off of Zombies
     */

    public void removeAllZombies() {
        Iterator<Zombie> zombieIterator = zombieList.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            zombieIterator.remove();
        }
    }

    /**
     * A Method for Clearing the Map Off of Plants
     */

    public void removeAllPlants() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (plants[i][j] != null) {
                    plants[i][j] = null;
                }
            }
        }
    }

    /**
     * A Method for Clearing the Map Off of Bullets
     */
    public void removeAllBullet() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (plants[i][j] != null) {
                    if (plants[i][j] instanceof PeaShooterPlant) {
                        Iterator<Bullet> bulletIterator;
                        //Adding an Extra Field In Plant Class IIIIIMMMMPPPP
                        //So Tough To UnderStand , Try For A Better Approach
                        bulletIterator = plants[i][j].bullet.iterator();
                        while (bulletIterator.hasNext()) {
                            Bullet bullet = bulletIterator.next();
                            bulletIterator.remove();
                        }
                    }
                }
            }
        }
    }
    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }



    /**
     * This Event Only Happens When We Want To Deploy a Plant in the Map
     * We Need one these For Gathering the Random Suns In The Map
     *
     *
     * THE NUMBERS ARE FAKE , JUST TO SHOW THE 5 CARDS TO CHOSE AND THE RANGE BETWEEN THEM
     * SHOULD FIND THE RIGHT NUMBERS
     *
     */
    //Only a MouseHandler Needed , No KeyHandlers
    class MouseHandler implements MouseListener, MouseMotionListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedCard = -1;
            int locationX= coordinate.getAxis_x();
            int locationY = coordinate.getAxis_y();
            if (locationY < 35 && locationY > 10) {
                if (locationX < 15 && locationX > 10 ) {
                    selectedCard = 0;
                }
                if (locationX < 21 && locationX > 16 && totalSun >= 100) {
                        selectedCard = 1;
                }
                if (locationX < 27 && locationX > 22 && totalSun >= 50) {
                    selectedCard= 2;
                }
                if ( locationX < 33 && locationX > 28 && totalSun >= 175) {
                    selectedCard = 3;
                }
                if (locationX < 39 && locationX > 34 && totalSun >= 150) {
                    selectedCard = 4;
                }
            }
            if (locationX < 76.5 && locationX > 40 && locationY< 59.5 && locationY > 13 && selectedCard != -1) {
                //The Above Parameters Should Be In the 9X5 of The Map
                //Plant Hast Been Planted
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

}
