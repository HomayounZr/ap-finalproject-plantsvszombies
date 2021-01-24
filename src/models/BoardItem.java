package models;

import java.io.Serializable;

public class BoardItem implements Serializable {

    private String username;
    private int wins_easy;
    private int wins_hard;
    private int loses_easy;
    private int loses_hard;
    private int totalScore;

    public BoardItem(
            String username,
            int wins_easy, int wins_hard,
            int loses_easy, int loses_hard,
            int totalScore
    ){
        this.username = username;
        this.wins_easy = wins_easy;
        this.wins_hard = wins_hard;
        this.loses_easy = loses_easy;
        this.loses_hard = loses_hard;
        this.totalScore = totalScore;
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

    public void setWins_easy(int wins_easy) {
        this.wins_easy = wins_easy;
    }

    public int getWins_hard() {
        return wins_hard;
    }

    public void setWins_hard(int wins_hard) {
        this.wins_hard = wins_hard;
    }

    public int getLoses_easy() {
        return loses_easy;
    }

    public void setLoses_easy(int loses_easy) {
        this.loses_easy = loses_easy;
    }

    public int getLoses_hard() {
        return loses_hard;
    }

    public void setLoses_hard(int loses_hard) {
        this.loses_hard = loses_hard;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
