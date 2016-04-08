package meomobile.it.ilgruppatore;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import meomobile.it.ilgruppatore.database.DatabaseContract;
import meomobile.it.ilgruppatore.database.DatabaseHelper;
import meomobile.it.ilgruppatore.database.model.Result;
import meomobile.it.ilgruppatore.database.model.ResultsAdapter;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> elencoStudenti;
    private ArrayList<Result> studenti4B;
    private ArrayList<Result> studenti4A;
    private ArrayList<Result> studenti1A;
    private ArrayList<Result> studenti2A;

    private ArrayList<Result> studenti3F;
    private ArrayList<Result> studenti3E;

    private ArrayList<Result> base; //usato come base

    private ResultsAdapter gruppiAdapter;
    private String classeSelezionata;

    private Intent mShareIntent;
    private ShareActionProvider mShareActionProvider;

    private Context context = this;

    private void setupGroups() {
        studenti4B = new ArrayList<Result>();

        // Carico gli studenti di 4B
        studenti4B.add(new Result("Basso Andrea"));
        studenti4B.add(new Result("Basso Matteo"));
        studenti4B.add(new Result("Ben Khalifa Youssef "));
        studenti4B.add(new Result("Bertazzon Alice "));
        studenti4B.add(new Result("Bianchin Alessandro "));
        studenti4B.add(new Result("Bordin Gianpaolo "));
        studenti4B.add(new Result("Bortolamiol Alessandro "));
        studenti4B.add(new Result("Crema Luca "));
        studenti4B.add(new Result("Demian Oleksandr "));
        studenti4B.add(new Result("Fedato Davide "));
        studenti4B.add(new Result("Franceschin Nicolò"));
        studenti4B.add(new Result("Gallina Greta "));
        studenti4B.add(new Result("Gastaldo Samuele"));
        studenti4B.add(new Result("Grando Giacomo "));
        studenti4B.add(new Result("Kawkab Wassim "));
        studenti4B.add(new Result("Menegon Diego "));
        studenti4B.add(new Result("Nardi Davide "));
        studenti4B.add(new Result("Pellizzari Giacomo"));
        studenti4B.add(new Result("Rotaru Roxana "));
        studenti4B.add(new Result("Torresan Federico "));
        studenti4B.add(new Result("Zandonà nicola"));
        studenti4B.add(new Result("Zanella Igor "));
        studenti4B.add(new Result("Zhiqiang Zhou"));
    }

    private void random(ArrayList<Result> lista) {
        Collections.shuffle(lista);
        // scelgo casuali
        gruppiAdapter.clear();
        elencoStudenti.addAll(lista);
        //gruppiAdapter.addAll(elencoStudenti);
        System.out.println(classeSelezionata + ": " + lista);
        System.out.println("Elenco: " + elencoStudenti);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupGroups();

        // Metto un array di base
        base = studenti4B;
        elencoStudenti = new ArrayList<Result>();
        elencoStudenti.addAll(base);

        // Imposto l'adapter della lista
        gruppiAdapter = new ResultsAdapter(this, R.layout.rowgruppo, elencoStudenti);
        ListView lv = (ListView) findViewById(R.id.listaGruppi);
        if (lv != null) {
            lv.setAdapter(gruppiAdapter);
        }

        // Riordina a caso
        random(base);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(context).create();
                dialog.setCancelable(true);
                dialog.show();

                Snackbar.make(view, "Salvo il gruppo! Un giorno potrai recuperare il salvataggio!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Provo a salvare sul database
                // Salvo anche su db
                DatabaseHelper gdh = new DatabaseHelper(getBaseContext());
                // Gets the data repository in write mode
                SQLiteDatabase db = gdh.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.ListEntry.COLUMN_NAME_CLASSE, classeSelezionata);
                values.put(DatabaseContract.ListEntry.COLUMN_NAME_LISTA, elencoStudenti.toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(
                        DatabaseContract.ListEntry.TABLE_NAME,
                        null,
                        values);


                // Legge i dati dal db
                String[] projection = {
                        DatabaseContract.ListEntry.COLUMN_NAME_ID,
                        DatabaseContract.ListEntry.COLUMN_NAME_CLASSE,
                        DatabaseContract.ListEntry.COLUMN_NAME_LISTA,
                        DatabaseContract.ListEntry.COLUMN_NAME_DATA
                };

                // How you want the results sorted in the resulting Cursor
                String sortOrder =
                        DatabaseContract.ListEntry.COLUMN_NAME_DATA + " DESC";

                Cursor c = db.query(
                        DatabaseContract.ListEntry.TABLE_NAME,  // The table to query
                        projection,                               // The columns to return
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        sortOrder                                 // The sort order
                );

                while (c.moveToNext()) {
                    System.out.println(c.getString(0));
                    System.out.println(c.getString(1));
                    System.out.println(c.getString(2));
                    System.out.println(c.getString(3));
                }


                System.out.println(c.getCount());
                System.out.println(c.moveToNext());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Locat MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        // Store Action Provider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, "Select something");

        mShareActionProvider.setShareIntent(mShareIntent);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.out.println(id);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.random4B) {
            classeSelezionata = "4B";
            base = studenti4B;
            // Mescolo B
            // Riordina a caso studenti4B
            random(base);
        } else if (id == R.id.random4A) {
            classeSelezionata = "4A";
            base = studenti4A;
            random(base);
        }

        mShareIntent.putExtra(Intent.EXTRA_TEXT, elencoStudenti.toString());

        return super.onOptionsItemSelected(item);
    }
}
