package storage.orm;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.models.MessageModel;
import com.repository.MessagesRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import storage.DbHelper;
import utils.RxUtil;

/**
 * Created by turka on 11/1/2016.
 */

public class MessageORM {
    private static final String TAG = "MessageORM";

    private static final String TABLE_NAME = "message";

    private static final String COMMA_SEP = ", ";

    private static final String COLUMN_ID_TYPE = "INTEGER PRIMARY KEY";
    private static final String COLUMN_ID = "ID";

    private static final String COLUMN_TITLE_TYPE = "TEXT";
    private static final String COLUMN_TITLE = "Title";

    private static final String COLUMN_BODY_TYPE = "TEXT";
    private static final String COLUMN_BODY = "Body";

    private static final String COLUMN_DATE_TYPE = "TEXT";
    private static final String COLUMN_DATE = "CreationDate";


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEP +
                    COLUMN_TITLE  + " " + COLUMN_TITLE_TYPE + COMMA_SEP +
                    COLUMN_BODY + " " + COLUMN_BODY_TYPE + COMMA_SEP +
                    COLUMN_DATE + " " + COLUMN_DATE_TYPE +
                    ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void InsertMessages(Context context, List<MessageModel> messages){
        DbHelper databaseWrapper = new DbHelper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        for (MessageModel message: messages) {
            ContentValues values = messageToContentValues(message);
            database.insert(MessageORM.TABLE_NAME, "null", values);
        }
        database.close();
    }

    public static void ClearCache(Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + MessageORM.TABLE_NAME);
        db.execSQL(MessageORM.SQL_CREATE_TABLE);
        db.close();
    }

    public static List<MessageModel> GetMessages(Context context) throws ParseException {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                MessageORM.COLUMN_ID,
                MessageORM.COLUMN_TITLE,
                MessageORM.COLUMN_BODY,
                MessageORM.COLUMN_DATE
        };

        Cursor messagesCursor = db.query(
                MessageORM.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        final List<MessageModel> messages = new ArrayList<MessageModel>();
        if(messagesCursor != null){
            try {

                while (messagesCursor.moveToNext()) {
                    MessageModel m = new MessageModel();
                    m.setID(messagesCursor.getInt(messagesCursor.getColumnIndex(MessageORM.COLUMN_ID)));
                    m.setTitle(messagesCursor.getString(messagesCursor.getColumnIndex(MessageORM.COLUMN_TITLE)));
                    m.setBody(messagesCursor.getString(messagesCursor.getColumnIndex(MessageORM.COLUMN_BODY)));
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = format.parse(messagesCursor.getString(messagesCursor.getColumnIndex(MessageORM.COLUMN_DATE)));
                    m.setCreationDate(date);
                    messages.add(m);
                }
            } finally {
                messagesCursor.close();
            }
        }
        db.close();
        return messages;
    }

    private static ContentValues messageToContentValues(MessageModel message){
        ContentValues values = new ContentValues();
        values.put(MessageORM.COLUMN_ID, message.getId());
        values.put(MessageORM.COLUMN_TITLE, message.getTitle());
        values.put(MessageORM.COLUMN_BODY, message.getBody());
        values.put(MessageORM.COLUMN_DATE, new SimpleDateFormat("dd/MM/yyyy").format(message.getDate()));

        return values;
    }
}
