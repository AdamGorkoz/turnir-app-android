package turka.turnirapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.models.GoalModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by turka on 6/30/2017.
 */

public class LiveMatch implements Parcelable {
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

    public LiveMatch(int ID, Date matchDate, int firstTeamID, String firstTeamName, int firstTeamGoals, int secondTeamID, String secondTeamName, int secondTeamGoals, String refereeName, int matchStatus, String time, Date startTime, Date secondHalfStartTime, Boolean isSecondHalf, String score, int matchOutcome, List<GoalModel> goals, int field, int matchTypeID) {
        this.ID = ID;
        MatchDate = matchDate;
        FirstTeamID = firstTeamID;
        FirstTeamName = firstTeamName;
        FirstTeamGoals = firstTeamGoals;
        SecondTeamID = secondTeamID;
        SecondTeamName = secondTeamName;
        SecondTeamGoals = secondTeamGoals;
        RefereeName = refereeName;
        MatchStatus = matchStatus;
        Time = time;
        StartTime = startTime;
        SecondHalfStartTime = secondHalfStartTime;
        IsSecondHalf = isSecondHalf;
        Score = score;
        MatchOutcome = matchOutcome;
        Goals = goals;
        Field = field;
        MatchTypeID = matchTypeID;
    }

    public LiveMatch(Parcel in){
        String[] data= new String[18];
        in.readStringArray(data);

        this.ID = Integer.parseInt(data[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.MatchDate = sdf.parse(data[1]);
        } catch (Exception e) {
            this.MatchDate = null;
        }
        this.FirstTeamID = Integer.parseInt(data[2]);
        this.FirstTeamName = data[3];
        this.FirstTeamGoals = Integer.parseInt(data[4]);
        this.SecondTeamID = Integer.parseInt(data[5]);
        this.SecondTeamName = data[6];
        this.SecondTeamGoals = Integer.parseInt(data[7]);
        this.RefereeName = data[8];
        this.MatchStatus = Integer.parseInt(data[9]);
        this.Time = data[10];
        try {
            this.StartTime = sdf.parse(data[11]);
        } catch (Exception e) {
            this.StartTime = null;
        }
        try {
            this.SecondHalfStartTime = sdf.parse(data[12]);
        } catch (Exception e) {
            this.SecondHalfStartTime = null;
        }
        this.IsSecondHalf = data[13] != null ? Boolean.parseBoolean(data[13]) : null;
        this.Score = data[14];
        this.MatchOutcome = Integer.parseInt(data[15]);
        this.Goals = null;
        this.Field = Integer.parseInt(data[16]);
        this.MatchTypeID = Integer.parseInt(data[17]);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        parcel.writeStringArray(new String[]{String.valueOf(this.ID),
                sdf.format(this.MatchDate),
                String.valueOf(this.FirstTeamID),
                this.FirstTeamName,
                String.valueOf(this.FirstTeamGoals),
                String.valueOf(this.SecondTeamID),
                this.SecondTeamName,
                String.valueOf(this.SecondTeamGoals),
                this.RefereeName,
                String.valueOf(this.MatchStatus),
                this.Time,
                this.StartTime != null ? sdf.format(this.StartTime) : null,
                this.SecondHalfStartTime != null ? sdf.format(this.SecondHalfStartTime) : null,
                this.IsSecondHalf != null ? String.valueOf(this.IsSecondHalf) : null,
                this.Score,
                String.valueOf(this.MatchOutcome),
                String.valueOf(this.MatchStatus),
                String.valueOf(this.MatchTypeID)}
        );
    }

    public static final Parcelable.Creator<LiveMatch> CREATOR = new Parcelable.Creator<LiveMatch>() {

        @Override
        public LiveMatch createFromParcel(Parcel source) {
            return new LiveMatch(source);
        }

        @Override
        public LiveMatch[] newArray(int size) {
            return new LiveMatch[size];
        }
    };
}
