package turka.turnirapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by turka on 10/23/2016.
 */

public class LeagueTeam implements Parcelable {

    private int ID;
    private String Name;
    private int Spot;
    private int Matches;
    private int Points;
    private int Wins;
    private int Losses;
    private int Draws;
    private int Diff;
    private String Goals;

    public LeagueTeam(int ID, String name, int matches, int points, int wins, int losses, int draws, int diff, String goals) {
        this.ID = ID;
        Name = name;
        Matches = matches;
        Points = points;
        Wins = wins;
        Losses = losses;
        Draws = draws;
        Diff = diff;
        Goals = goals;
    }

    public LeagueTeam(Parcel in){
        String[] data= new String[10];
        in.readStringArray(data);

        this.ID = Integer.parseInt(data[0]);
        this.Name = data[1];
        this.Matches = Integer.parseInt(data[2]);
        this.Points = Integer.parseInt(data[3]);
        this.Wins = Integer.parseInt(data[4]);
        this.Losses = Integer.parseInt(data[5]);
        this.Draws = Integer.parseInt(data[6]);
        this.Diff = Integer.parseInt(data[7]);
        this.Goals = data[8];
        this.Spot = Integer.parseInt(data[9]);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMatches() {
        return Matches;
    }

    public void setMatches(int matches) {
        Matches = matches;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{String.valueOf(this.ID),
                this.Name,
                String.valueOf(this.Matches),
                String.valueOf(this.Points),
                String.valueOf(this.Wins),
                String.valueOf(this.Losses),
                String.valueOf(this.Draws),
                String.valueOf(this.Diff),
                this.Goals,
                String.valueOf(this.Spot)}
               );
    }

    public static final Parcelable.Creator<LeagueTeam> CREATOR = new Parcelable.Creator<LeagueTeam>() {

        @Override
        public LeagueTeam createFromParcel(Parcel source) {
            return new LeagueTeam(source);
        }

        @Override
        public LeagueTeam[] newArray(int size) {
            return new LeagueTeam[size];
        }
    };

    public int getSpot() {
        return Spot;
    }

    public void setSpot(int spot) {
        Spot = spot;
    }
}
