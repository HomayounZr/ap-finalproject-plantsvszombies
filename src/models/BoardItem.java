package models;

import java.io.Serializable;

/**
 * BoardItem is used to show records on ranking page
 */
public class BoardItem implements Serializable {

    // username
    private String username;
    // number of easy wins
    private int wins_easy;
    // number of hard wins
    private int wins_hard;
    // number of easy loses
    private int loses_easy;
    // number of hard loses
    private int loses_hard;
    // user score
    private int totalScore;

    /**
     * constructor
     * @param username String
     * @param wins_easy int
     * @param wins_hard int
     * @param loses_easy int
     * @param loses_hard int
     * @param totalScore int
     */
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

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return easy wins
     */
    public int getWins_easy() {
        return wins_easy;
    }

    /**
     * set easy wins
     * @param wins_easy int
     */
    public void setWins_easy(int wins_easy) {
        this.wins_easy = wins_easy;
    }

    /**
     * @return hard wins
     */
    public int getWins_hard() {
        return wins_hard;
    }

    /**
     * set hard wins
     * @param wins_hard int
     */
    public void setWins_hard(int wins_hard) {
        this.wins_hard = wins_hard;
    }

    /**
     * @return easy loses
     */
    public int getLoses_easy() {
        return loses_easy;
    }

    /**
     * set easy loses
     * @param loses_easy int
     */
    public void setLoses_easy(int loses_easy) {
        this.loses_easy = loses_easy;
    }

    /**
     * @return int hard loses
     */
    public int getLoses_hard() {
        return loses_hard;
    }

    /**
     * set hard loses
     * @param loses_hard int
     */
    public void setLoses_hard(int loses_hard) {
        this.loses_hard = loses_hard;
    }

    /**
     * @return total score int
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * set total score
     * @param totalScore int
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
