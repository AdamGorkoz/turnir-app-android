package storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import storage.orm.LeagueORM;
import storage.orm.MessageORM;

/**
 * Created by turka on 11/1/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "TurnirDB.db";

    @Inject
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MessageORM.SQL_CREATE_TABLE);
        db.execSQL(LeagueORM.SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MessageORM.SQL_DROP_TABLE);
        db.execSQL(LeagueORM.SQL_DROP_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
