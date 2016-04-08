package meomobile.it.ilgruppatore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import meomobile.it.ilgruppatore.database.DatabaseContract;
import meomobile.it.ilgruppatore.database.DatabaseHelper;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        // Carico la lista dei gruppi dal database
        // Salvo anche su db
        DatabaseHelper gdh = new DatabaseHelper(getBaseContext());
        // Gets the data repository in write mode
        SQLiteDatabase db = gdh.getReadableDatabase();

        Cursor c = db.query(
                DatabaseContract.TeamEntry.TABLE_NAME,  // The table to query
                new String[]{"name"},                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()) {
            System.out.println(c.getString(0));
        }
    }
}
