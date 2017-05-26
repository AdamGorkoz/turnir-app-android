package com.models;

/**
 * Created by turka on 5/25/2017.
 */

public class MatchEventModel {

    private String PlayerName;
    private String Minute;
    private int TeamId;
    private int Type;
    private int Subtype;

    public MatchEventModel(String playerName, String minute, int teamId, int type, int subtype) {
        PlayerName = playerName;
        Minute = minute;
        TeamId = teamId;
        Type = type;
        Subtype = subtype;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getMinute() {
        return Minute;
    }

    public void setMinute(String minute) {
        Minute = minute;
    }

    public int getTeamId() {
        return TeamId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getSubtype() {
        return Subtype;
    }

    public void setSubtype(int subtype) {
        Subtype = subtype;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;

    }


}
