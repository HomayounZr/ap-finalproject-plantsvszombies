package models;

/**
 * Player Class is used to Create A New Player For The Game
 * Setting Its Sun Amount
 * Its Instantiated From User Class
 */

public class Player {

    private User user;
    private int suns;

    /**
     * Constructor Of The Class
     * @param username User ID
     */

    public Player(String username){
        this.user = new User(username);
        suns = 0;
    }

    /**
     * Adding Given Amount Of Suns To Players Info
     * @param amount the Increment Amount
     */

    public void collectSun(int amount){
        this.suns += amount;
    }



}
