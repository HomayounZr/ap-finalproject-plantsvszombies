package models;

public class Player {

    private User user;
    private int suns;


    public Player(String username){
        this.user = new User(username);
        suns = 0;
    }

    public void collectSun(int amount){
        this.suns += amount;
    }



}
