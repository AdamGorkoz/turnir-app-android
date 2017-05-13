package turka.turnirapp.model;

import java.util.Date;

/**
 * Created by turka on 5/13/2017.
 */

public class TeamMatch {

    private int ID;
    private Date MatchDate;
    private  int MatchOutcome;
    private String Score;
    private String Opponent;

    public TeamMatch(int ID, Date matchDate, int matchOutcome, String score, String opponent) {
        this.ID = ID;
        MatchDate = matchDate;
        MatchOutcome = matchOutcome;
        Score = score;
        Opponent = opponent;
    }

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
