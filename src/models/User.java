package models;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private int wins_easy;
    private int wins_hard;
    private int loses_easy;
    private int loses_hard;
    private int totalScore;

    public User(String username){
        this.username = username;
        wins_easy = wins_hard = loses_hard = loses_easy = totalScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins_easy() {
        return wins_easy;
    }

    public void setWins_easy() {
        this.wins_easy++;
    }

    public int getWins_hard() {
        return wins_hard;
    }

    public void setWins_hard() {
        this.wins_hard++;
    }

    public int getLoses_easy() {
        return loses_easy;
    }

    public void setLoses_easy() {
        this.loses_easy++;
    }

    public int getLoses_hard() {
        return loses_hard;
    }

    public void setLoses_hard() {
        this.loses_hard++;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
