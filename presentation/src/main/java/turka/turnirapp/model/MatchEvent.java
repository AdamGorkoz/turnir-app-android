package turka.turnirapp.model;

/**
 * Created by turka on 5/25/2017.
 */

public class MatchEvent {

    private String PlayerName;
    private String Minute;
    private int Position;
    private int Type;
    private int Subtype;

    public MatchEvent(String playerName, String minute, int position, int type, int subtype) {
        PlayerName = playerName;
        Minute = minute;
        Position = position;
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

    public int getPosition() {
        return Position;
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

    public void setPosition(int position) {
        Position = position;

    }


}
