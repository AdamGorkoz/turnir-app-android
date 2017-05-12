package com.models;

/**
 * Created by turka on 11/4/2016.
 */

public class LeagueTeamModel {

    private int ID;

    public LeagueTeamModel(){

    }

    public LeagueTeamModel(int ID, int wins, int points, int losses, int draws, int matches, int diff, String goals, String name) {
        this.ID = ID;
        Wins = wins;
        Points = points;
        Losses = losses;
        Draws = draws;
        Matches = matches;
        Diff = diff;
        Goals = goals;
        Name = name;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getLosses() {
        return Losses;
    }

    public void setLosses(int losses) {
        Losses = losses;
    }

    public int getDraws() {
        return Draws;
    }

    public void setDraws(int draws) {
        Draws = draws;
    }

    public int getMatches() {
        return Matches;
    }

    public void setMatches(int matches) {
        Matches = matches;
    }

    public int getDiff() {
        return Diff;
    }

    public void setDiff(int diff) {
        Diff = diff;
    }

    public String getGoals() {
        return Goals;
    }

    public void setGoals(String goals) {
        Goals = goals;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private int Wins;
    private int Points;
    private int Losses;
    private int Draws;
    private int Matches;
    private int Diff;
    private String Goals;
    private String Name;

}
