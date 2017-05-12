package turka.turnirapp.model;

/**
 * Created by turka on 5/6/2017.
 */

public class Player {
    private int ID;
    private int Goals;
    private String Name;
    private int Number;
    private int RecordStatus;
    private int Price;
    private int TeamID;
    private String TeamName;

    public Player(int ID, int goals, String name, int number, int recordStatus, int price, int teamID, String teamName) {
        this.ID = ID;
        Goals = goals;
        Name = name;
        Number = number;
        RecordStatus = recordStatus;
        Price = price;
        TeamID = teamID;
        TeamName = teamName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGoals() {
        return Goals;
    }

    public void setGoals(int goals) {
        Goals = goals;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getRecordStatus() {
        return RecordStatus;
    }

    public void setRecordStatus(int recordStatus) {
        RecordStatus = recordStatus;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
}
