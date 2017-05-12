package storage.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.models.LeagueTeamModel;
import com.models.MessageModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import storage.DbHelper;

/**
 * Created by turka on 11/4/2016.
 */

public class LeagueORM {
    private static final String TAG = "LeagueORM";

    private static final String TABLE_NAME = "league";

    private static final String COMMA_SEP = ", ";

    private static final String COLUMN_ID_TYPE = "INTEGER PRIMARY KEY";
    private static final String COLUMN_ID = "ID";

    private static final String COLUMN_NAME_TYPE = "TEXT";
    private static final String COLUMN_NAME = "Name";

    private static final String COLUMN_POINTS_TYPE = "INTEGER";
    private static final String COLUMN_POINTS = "Points";

    private static final String COLUMN_MATCHES_TYPE = "INTEGER";
    private static final String COLUMN_MATCHES = "Matches";

    private static final String COLUMN_WINS_TYPE = "INTEGER";
    private static final String COLUMN_WINS = "Wins";

    private static final String COLUMN_LOSSES_TYPE = "INTEGER";
    private static final String COLUMN_LOSSES = "Losses";

    private static final String COLUMN_DRAWS_TYPE = "INTEGER";
    private static final String COLUMN_DRAWS = "Draws";

    private static final String COLUMN_DIFF_TYPE = "INTEGER";
    private static final String COLUMN_DIFF = "Diff";

    private static final String COLUMN_GOALS_TYPE = "TEXT";
    private static final String COLUMN_GOALS = "Goals";


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEP +
                    COLUMN_NAME  + " " + COLUMN_NAME_TYPE + COMMA_SEP +
                    COLUMN_POINTS + " " + COLUMN_POINTS_TYPE + COMMA_SEP +
                    COLUMN_MATCHES + " " + COLUMN_MATCHES_TYPE + COMMA_SEP +
                    COLUMN_WINS + " " + COLUMN_WINS_TYPE + COMMA_SEP +
                    COLUMN_LOSSES + " " + COLUMN_LOSSES_TYPE + COMMA_SEP +
                    COLUMN_DRAWS + " " + COLUMN_DRAWS_TYPE + COMMA_SEP +
                    COLUMN_DIFF + " " + COLUMN_DIFF_TYPE + COMMA_SEP +
                    COLUMN_GOALS + " " + COLUMN_GOALS_TYPE +
                    ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void InsertLeague(Context context, List<LeagueTeamModel> leagueTeams){
        DbHelper databaseWrapper = new DbHelper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        for (LeagueTeamModel leagueTeam: leagueTeams) {
            ContentValues values = leagueTeamToContentValues(leagueTeam);
            database.insert(LeagueORM.TABLE_NAME, "null", values);
        }
        database.close();
    }

    public static void ClearCache(Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + LeagueORM.TABLE_NAME);
        db.execSQL(LeagueORM.SQL_CREATE_TABLE);
        db.close();
    }

    public static List<LeagueTeamModel> GetLeague(Context context) throws ParseException {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                LeagueORM.COLUMN_ID,
                LeagueORM.COLUMN_NAME,
                LeagueORM.COLUMN_POINTS,
                LeagueORM.COLUMN_MATCHES,
                LeagueORM.COLUMN_WINS,
                LeagueORM.COLUMN_LOSSES,
                LeagueORM.COLUMN_DRAWS,
                LeagueORM.COLUMN_POINTS,
                LeagueORM.COLUMN_DIFF,
                LeagueORM.COLUMN_GOALS,
        };

        Cursor leagueCursor = db.query(
                LeagueORM.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        final List<LeagueTeamModel> league = new ArrayList<LeagueTeamModel>();
        if(leagueCursor != null){
            try {

                while (leagueCursor.moveToNext()) {
                    LeagueTeamModel lt = new LeagueTeamModel();
                    lt.setID(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_ID)));
                    lt.setName(leagueCursor.getString(leagueCursor.getColumnIndex(LeagueORM.COLUMN_NAME)));
                    lt.setPoints(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_POINTS)));
                    lt.setWins(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_WINS)));
                    lt.setDraws(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_DRAWS)));
                    lt.setLosses(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_LOSSES)));
                    lt.setMatches(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_MATCHES)));
                    lt.setDiff(leagueCursor.getInt(leagueCursor.getColumnIndex(LeagueORM.COLUMN_DIFF)));
                    lt.setGoals(leagueCursor.getString(leagueCursor.getColumnIndex(LeagueORM.COLUMN_GOALS)));
                    league.add(lt);
                }
            } finally {
                leagueCursor.close();
            }
        }
        db.close();
        return league;
    }

    private static ContentValues leagueTeamToContentValues(LeagueTeamModel leagueTeam){
        ContentValues values = new ContentValues();
        values.put(LeagueORM.COLUMN_ID, leagueTeam.getID());
        values.put(LeagueORM.COLUMN_NAME, leagueTeam.getName());
        values.put(LeagueORM.COLUMN_POINTS, leagueTeam.getPoints());
        values.put(LeagueORM.COLUMN_WINS, leagueTeam.getWins());
        values.put(LeagueORM.COLUMN_DRAWS, leagueTeam.getDraws());
        values.put(LeagueORM.COLUMN_LOSSES, leagueTeam.getLosses());
        values.put(LeagueORM.COLUMN_MATCHES, leagueTeam.getMatches());
        values.put(LeagueORM.COLUMN_DIFF, leagueTeam.getDiff());
        values.put(LeagueORM.COLUMN_GOALS, leagueTeam.getGoals());

        return values;
    }
}
