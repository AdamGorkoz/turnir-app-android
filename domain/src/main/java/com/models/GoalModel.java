package com.models;

/**
 * Created by turka on 5/25/2017.
 */

public class GoalModel {

    private int ID;
    private String Minute;
    private String PlayerName;
    private int TeamID;

    public GoalModel(int ID, String minute, String playerName, int teamID) {
        this.ID = ID;
        Minute = minute;
        PlayerName = playerName;
        TeamID = teamID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMinute() {
        return Minute;
    }

    public void setMinute(String minute) {
        Minute = minute;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }
}
