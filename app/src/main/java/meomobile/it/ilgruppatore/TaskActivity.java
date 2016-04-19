package meomobile.it.ilgruppatore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import meomobile.it.ilgruppatore.database.DatabaseContract;
import meomobile.it.ilgruppatore.database.DatabaseHelper;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        Intent i = getIntent();
        String team = i.getExtras().getString("team");

        // Carico la lista dei gruppi dal database
        // Salvo anche su db
        DatabaseHelper gdh = new DatabaseHelper(getBaseContext());
        // Gets the data repository in write mode
        SQLiteDatabase db = gdh.getReadableDatabase();

        Cursor c = db.query(
                DatabaseContract.TaskEntry.TABLE_NAME,  // The table to query
                new String[]{DatabaseContract.TaskEntry.COLUMN_NAME_ID,
                        DatabaseContract.TaskEntry.COLUMN_NAME_TEAM, DatabaseContract.TaskEntry.COLUMN_NAME_DATA}, // The columns to return
                (DatabaseContract.TaskEntry.COLUMN_NAME_TEAM + "='" + team + "'"),                                // The columns for the WHERE clause
                null,    // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ListView lv = (ListView) findViewById(R.id.listView_tasks);
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.rowtask, R.id.list_item_task);
        lv.setAdapter(itemsAdapter);

        /*Carico la lista con le classi. Se premo una classe vedo tutti i compiti salvati*/
        while (c.moveToNext()) {
            itemsAdapter.add(c.getString(2));
            System.out.println(c.getString(0) + " " + c.getString(1) + " " + c.getString(2));
        }

    }
}
