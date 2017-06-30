package com.models;

import java.util.Date;
import java.util.List;

/**
 * Created by turka on 6/30/2017.
 */

public class LiveMatchModel {
    private int ID;
    private Date MatchDate;
    private int FirstTeamID;
    private String FirstTeamName;
    private int FirstTeamGoals;
    private int SecondTeamID;
    private String SecondTeamName;
    private int SecondTeamGoals;
    private String RefereeName;
    private int MatchStatus;
    private String Time;
    private Date StartTime;
    private Date SecondHalfStartTime;
    private Boolean IsSecondHalf;
    private String Score;
    private int MatchOutcome;
    private List<GoalModel> Goals;
    private int Field;
    private int MatchTypeID;

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

    public int getFirstTeamID() {
        return FirstTeamID;
    }

    public void setFirstTeamID(int firstTeamID) {
        FirstTeamID = firstTeamID;
    }

    public String getFirstTeamName() {
        return FirstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        FirstTeamName = firstTeamName;
    }

    public int getFirstTeamGoals() {
        return FirstTeamGoals;
    }

    public void setFirstTeamGoals(int firstTeamGoals) {
        FirstTeamGoals = firstTeamGoals;
    }

    public int getSecondTeamID() {
        return SecondTeamID;
    }

    public void setSecondTeamID(int secondTeamID) {
        SecondTeamID = secondTeamID;
    }

    public String getSecondTeamName() {
        return SecondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        SecondTeamName = secondTeamName;
    }

    public int getSecondTeamGoals() {
        return SecondTeamGoals;
    }

    public void setSecondTeamGoals(int secondTeamGoals) {
        SecondTeamGoals = secondTeamGoals;
    }

    public String getRefereeName() {
        return RefereeName;
    }

    public void setRefereeName(String refereeName) {
        RefereeName = refereeName;
    }

    public int getMatchStatus() {
        return MatchStatus;
    }

    public void setMatchStatus(int matchStatus) {
        MatchStatus = matchStatus;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getSecondHalfStartTime() {
        return SecondHalfStartTime;
    }

    public void setSecondHalfStartTime(Date secondHalfStartTime) {
        SecondHalfStartTime = secondHalfStartTime;
    }

    public Boolean getSecondHalf() {
        return IsSecondHalf;
    }

    public void setSecondHalf(Boolean secondHalf) {
        IsSecondHalf = secondHalf;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public int getMatchOutcome() {
        return MatchOutcome;
    }

    public void setMatchOutcome(int matchOutcome) {
        MatchOutcome = matchOutcome;
    }

    public List<GoalModel> getGoals() {
        return Goals;
    }

    public void setGoals(List<GoalModel> goals) {
        Goals = goals;
    }

    public int getField() {
        return Field;
    }

    public void setField(int field) {
        Field = field;
    }

    public int getMatchTypeID() {
        return MatchTypeID;
    }

    public void setMatchTypeID(int matchTypeID) {
        MatchTypeID = matchTypeID;
    }
}
