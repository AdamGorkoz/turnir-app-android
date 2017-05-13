package com.models;

import java.util.Date;

/**
 * Created by turka on 5/12/2017.
 */

public class TeamMatchModel {
    private int ID;
    private Date MatchDate;
    private  int MatchOutcome;
    private String Score;
    private String Opponent;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(Date matchDate) {
        MatchDate = matchDate;
    }

    public int getMatchOutcome() {
        return MatchOutcome;
    }

    public void setMatchOutcome(int matchOutcome) {
        MatchOutcome = matchOutcome;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getOpponent() {
        return Opponent;
    }

    public void setOpponent(String opponent) {
        Opponent = opponent;
    }
}
