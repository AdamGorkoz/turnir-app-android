package turka.turnirapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by turka on 5/13/2017.
 */

public class TeamMatch implements Parcelable {

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

    public TeamMatch(Parcel in){
        String[] data= new String[5];
        in.readStringArray(data);

        this.ID = Integer.parseInt(data[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.MatchDate = sdf.parse(data[1]);
        } catch (ParseException e) {
            this.MatchDate = null;
        }
        this.MatchOutcome = Integer.parseInt(data[2]);
        this.Score = data[3];
        this.Opponent = data[4];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(this.MatchDate);
        parcel.writeStringArray(new String[]{String.valueOf(this.ID),
                dateStr,
                String.valueOf(this.MatchOutcome),
                this.Score,
                this.Opponent}
        );
    }

    public static final Parcelable.Creator<TeamMatch> CREATOR = new Parcelable.Creator<TeamMatch>() {

        @Override
        public TeamMatch createFromParcel(Parcel source) {
            return new TeamMatch(source);
        }

        @Override
        public TeamMatch[] newArray(int size) {
            return new TeamMatch[size];
        }
    };
}
