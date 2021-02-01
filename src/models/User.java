package models;

import java.io.Serializable;

/**
 * This Class Represents The User
 * The Player Of the Game Will Be Instanciated From this Class
 */

public class User implements Serializable {

    private String username;
    private int wins_easy;
    private int wins_hard;
    private int loses_easy;
    private int loses_hard;
    private int totalScore;

    /**
     * Constructor for The Class
     * @param username Users ID
     */
    public User(String username){
        this.username = username;
        wins_easy = wins_hard = loses_hard = loses_easy = totalScore = 0;
    }

    /**
     * A Getter Method For UserName
     * @return UserName
     */

    public String getUsername() {
        return username;
    }

    /**
     * A Setter Method For The UserName
     * @param username user ID
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * A Getter Method For Returning The Count Of Wins In Easy Situation
     * @return win Count
     */

    public int getWins_easy() {
        return wins_easy;
    }
    /**
     * A Setter Method For The Count Of Won GameS in Easy Situation
     * Won Games In Easy Situation Is Increased By One
     */
    public void setWins_easy() {
        this.wins_easy++;
    }
    /**
     * A Getter Method For Returning The Count Of Wins In Hard Situation
     * @return win Count
     */

    public int getWins_hard() {
        return wins_hard;
    }
    /**
     * A Setter Method For The Count Of Won Games in Hard Situation
     * Lost Wins In Hard Situation Is Increased By One
     */
    public void setWins_hard() {
        this.wins_hard++;
    }
    /**
     * A Getter Method For Returning The Count Of Losses In Easy Situation
     * @return win Count
     */

    public int getLoses_easy() {
        return loses_easy;
    }
    /**
     * A Setter Method For The Count Of Lost GameS in Easy Situation
     * Lost Games In Easy Situation Is Increased By One
     */
    public void setLoses_easy() {
        this.loses_easy++;
    }
    /**
     * A Getter Method For Returning The Count Of losses in Hard Situation
     * @return win Count
     */

    public int getLoses_hard() {
        return loses_hard;
    }
    /**
     * A Setter Method For The Count Of Lost GameS in Hard Situation
     * Lost Games In Hard Situation Is Increased By One
     */
    public void setLoses_hard() {
        this.loses_hard++;
    }
    /**
     * A Getter Method For Returning Users Total Score
     * @return Score
     */
    public int getTotalScore() {
        return totalScore;
    }
    /**
     * A Setter Method For The TotalScore Of The User In the Ranking Page
     *
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
