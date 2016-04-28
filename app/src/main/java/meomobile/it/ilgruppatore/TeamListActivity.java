package meomobile.it.ilgruppatore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import meomobile.it.ilgruppatore.database.DatabaseContract;
import meomobile.it.ilgruppatore.database.DatabaseHelper;

public class TeamListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);

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

        ListView lv = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.rowteam);
        lv.setAdapter(itemsAdapter);

        /*Carico la lista con le classi. Se premo una classe vedo tutti i compiti salvati*/
        while (c.moveToNext()) {
            itemsAdapter.add(c.getString(0));
            //System.out.println(c.getString(0));
        }

        // Aggiungo l'evento sul click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Qui ho tutto quello che mi serve!
                String team = itemsAdapter.getItem(position);
                // Lancio l'activity con l'elenco dei task e passo la classe
                Intent i = new Intent(getApplicationContext(), TaskListActivity.class);
                i.putExtra("team", team);
                startActivity(i);
            }
        });
    }
}
