package meomobile.it.ilgruppatore;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import meomobile.it.ilgruppatore.database.DatabaseContract;
import meomobile.it.ilgruppatore.database.DatabaseHelper;

public class TaskListActivity extends AppCompatActivity {

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

        // Aggiungo un foating button

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_tasklist);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apro una dialog per inserire il nome del nuovo task
                Log.d("Debug", "Clicco sul fab");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
                LayoutInflater inflater = ((Activity) v.getContext()).getLayoutInflater();
                alertDialogBuilder.setView(inflater.inflate(R.layout.dialog_newtask, null));
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("fab_ok", "Premo Ok");
                    }
                });
                AlertDialog ad = alertDialogBuilder.create();
                ad.show();
            }
        });

    }
}