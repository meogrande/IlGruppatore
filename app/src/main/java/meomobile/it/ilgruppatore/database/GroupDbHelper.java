package meomobile.it.ilgruppatore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import meomobile.it.ilgruppatore.database.GroupContract.GroupEntry;

/**
 * Creato da fabio on 29/02/2016.
 */
public class GroupDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Group.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE gruppo (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,classe TEXT, lista TEXT, data DATE DEFAULT (datetime('now')) )";
            /*
            "CREATE TABLE " + GroupEntry.TABLE_NAME + " (" +
                    GroupEntry.COLUMN_NAME_ID + " INTEGER AUTOINCREMENT PRIMARY_KEY " + COMMA_SEP +
                    GroupEntry.COLUMN_NAME_CLASSE + TEXT_TYPE + COMMA_SEP +
                    GroupEntry.COLUMN_NAME_LISTA + TEXT_TYPE + COMMA_SEP +
                    GroupEntry.COLUMN_NAME_DATA + " DATE DEFAULT (datetime('now','localtime')) )";*/

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GroupEntry.TABLE_NAME;

    public GroupDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}