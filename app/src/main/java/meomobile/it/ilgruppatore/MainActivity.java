package meomobile.it.ilgruppatore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Random;

import meomobile.it.ilgruppatore.database.GroupContract;
import meomobile.it.ilgruppatore.database.GroupDbHelper;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> elencoStudenti;
    private ArrayList<String> studenti4B;
    private ArrayList<String> studenti4A;
    private ArrayList<String> studenti1A;
    private ArrayList<String> studenti2A;
    private ArrayAdapter gruppiAdapter;
    private String classeSelezionata;

    private Intent mShareIntent;
    private ShareActionProvider mShareActionProvider;

    private void setupGroups() {
        studenti4B = new ArrayList<String>();

        // Carico gli studenti di 4B
        studenti4B.add("Basso Andrea");
        studenti4B.add("Basso Matteo");
        studenti4B.add("Ben Khalifa Youssef ");
        studenti4B.add("Bertazzon Alice ");
        studenti4B.add("Bianchin Alessandro ");
        studenti4B.add("Bordin Gianpaolo ");
        studenti4B.add("Bortolamiol Alessandro ");
        studenti4B.add("Crema Luca ");
        studenti4B.add("Demian Oleksandr ");
        studenti4B.add("Fedato Davide ");
        studenti4B.add("Franceschin Nicolò");
        studenti4B.add("Gallina Greta ");
        studenti4B.add("Gastaldo Samuele");
        studenti4B.add("Grando Giacomo ");
        studenti4B.add("Kawkab Wassim ");
        studenti4B.add("Menegon Diego ");
        studenti4B.add("Nardi Davide ");
        studenti4B.add("Pellizzari Giacomo");
        studenti4B.add("Rotaru Roxana ");
        studenti4B.add("Torresan Federico ");
        studenti4B.add("Zandonà nicola");
        studenti4B.add("Zanella Igor ");
        studenti4B.add("Zhiqiang Zhou");

        studenti4A = new ArrayList<String>();

        studenti4A.add("Adami Alessandro");
        studenti4A.add("Bachmatchi George Daniel");
        studenti4A.add("Bernardi Deyan");
        studenti4A.add("Bernardi Giovanni");
        studenti4A.add("Bortolon Matteo");
        studenti4A.add("Bressan Elia");
        studenti4A.add("Francescato Filippo");
        studenti4A.add("Fregolent Riccardo");
        studenti4A.add("Gallina Giorgio");
        studenti4A.add("Garbujo Luca");
        studenti4A.add("Giacometti Luca");
        studenti4A.add("Innocente Simone");
        studenti4A.add("Morandin Manuel");
        studenti4A.add("Nardellotto Marco");
        studenti4A.add("Pedrali Michael");
        studenti4A.add("Positello Marco");
        studenti4A.add("Quagliotto Simone");
        studenti4A.add("Savino Ronald");
        studenti4A.add("Simonetto Patrick");
        studenti4A.add("Torresan Andrea");
        studenti4A.add("Volpe Riccardo");
        studenti4A.add("Yoada Abdoulazize");

        studenti1A = new ArrayList<String>();

        studenti1A.add("AHMADI ADAM");
        studenti1A.add("ARDISSONO TOMMASO");
        studenti1A.add("BACCIN GIORGIA");
        studenti1A.add("BALDISSERA PAOLO");
        studenti1A.add("COLLA TOMMASO");
        studenti1A.add("GAFFO VALENTINO");
        studenti1A.add("GAZZOLA GIACOMO");
        studenti1A.add("GAZZOLA MARTA CESARINA");
        studenti1A.add("GERLIN FRANCESCO");
        studenti1A.add("GERONAZZO LUDOVICO");
        studenti1A.add("GHELLER DENISE");
        studenti1A.add("GOTTARDO GIOVANNI");
        studenti1A.add("GRIGOLETTO LUCA");
        studenti1A.add("LALA DEBORA");
        studenti1A.add("LO PORTO LUCA");
        studenti1A.add("MELARA CHIARA");
        studenti1A.add("MILANESE GAIA");
        studenti1A.add("NOAL ELENA");
        studenti1A.add("PICCOLOTTO MICHAEL");
        studenti1A.add("TRENTIN EDOARDO");
        studenti1A.add("VAGLICA ANDREA");
        studenti1A.add("VILLANOVA LORENZO");
        studenti1A.add("ZANATTA MARZIA");
        studenti1A.add("ZANESCO GIORGIA");

        studenti2A = new ArrayList<>();

        studenti2A.add("CANELLO GABRIELE");
        studenti2A.add("CECCACCI MARCO");
        studenti2A.add("DA RIVA SABRINA");
        studenti2A.add("EL AIDI YASSIN");
        studenti2A.add("EL BASRAOUI YOUSSEF");
        studenti2A.add("FACIN EMILIANO");
        studenti2A.add("KURTOVIC ELVIS");
        studenti2A.add("LAANAOUI HAMZA");
        studenti2A.add("MAZZERO LORENZO");
        studenti2A.add("MENIN GIANLUCA");
        studenti2A.add("PELLIZZARI NICOLAS");
        studenti2A.add("PRECOMA ROBERTO");
        studenti2A.add("RIMENSI ALESSIO");
        studenti2A.add("SOLIGO RAFFAELE");
        studenti2A.add("TONIN GIOVANNI");
        studenti2A.add("ZORZI FILIPPO");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupGroups();

        // Riordina a caso studenti4B
        classeSelezionata = "4A";
        int a, b;
        Random rn = new Random();
        System.out.println("4A: " + studenti4B);
        for (int i = 0; i < studenti4A.size(); i++) {
            a = rn.nextInt(studenti4A.size());
            b = rn.nextInt(studenti4A.size());
            //System.out.println(a + " " + b+ " " + studenti4B.size());
            String temp = studenti4A.get(a);
            studenti4A.set(a, studenti4A.get(b));
            studenti4A.set(b, temp);
        }

        elencoStudenti = new ArrayList<String>(studenti4A);

        System.out.println("4A: " + studenti4A);
        System.out.println("Elenco: " + elencoStudenti);

        gruppiAdapter = new ArrayAdapter<String>(this, R.layout.rowgruppo, R.id.list_item_gruppo, elencoStudenti);
        ListView lv = (ListView) findViewById(R.id.listaGruppi);
        lv.setAdapter(gruppiAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Un giorno salverà... ma non oggi!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Provo a salvare sul database
                // Salvo anche su db
                GroupDbHelper gdh = new GroupDbHelper(getBaseContext());
                // Gets the data repository in write mode
                SQLiteDatabase db = gdh.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(GroupContract.GroupEntry.COLUMN_NAME_CLASSE, classeSelezionata);
                values.put(GroupContract.GroupEntry.COLUMN_NAME_LISTA, elencoStudenti.toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(
                        GroupContract.GroupEntry.TABLE_NAME,
                        null,
                        values);


                // Legge i dati dal db
                String[] projection = {
                        GroupContract.GroupEntry.COLUMN_NAME_ID,
                        GroupContract.GroupEntry.COLUMN_NAME_CLASSE,
                        GroupContract.GroupEntry.COLUMN_NAME_LISTA,
                        GroupContract.GroupEntry.COLUMN_NAME_DATA
                };

                // How you want the results sorted in the resulting Cursor
                String sortOrder =
                        GroupContract.GroupEntry.COLUMN_NAME_DATA + " DESC";

                Cursor c = db.query(
                        GroupContract.GroupEntry.TABLE_NAME,  // The table to query
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
            // Mescolo B
            // Riordina a caso studenti4B
            int a, b;
            Random rn = new Random();
            for (int i = 0; i < studenti4B.size(); i++) {
                a = rn.nextInt(studenti4B.size());
                b = rn.nextInt(studenti4B.size());
                //System.out.println(a + " " + b+ " " + studenti4B.size());
                String temp = studenti4B.get(a);
                studenti4B.set(a, studenti4B.get(b));
                studenti4B.set(b, temp);
            }

            // scelgo casuali
            gruppiAdapter.clear();
            elencoStudenti.addAll(studenti4B);
            //gruppiAdapter.addAll(elencoStudenti);
            System.out.println("4B: " + studenti4B);
            System.out.println("Elenco: " + elencoStudenti);
        } else if (id == R.id.random4A) {
            classeSelezionata = "4A";
            // Mescolo A
            // Riordina a caso studenti4B
            int a, b;
            Random rn = new Random();
            for (int i = 0; i < studenti4A.size(); i++) {
                a = rn.nextInt(studenti4A.size());
                b = rn.nextInt(studenti4A.size());
                //System.out.println(a + " " + b+ " " + studenti4B.size());
                String temp = studenti4A.get(a);
                studenti4A.set(a, studenti4A.get(b));
                studenti4A.set(b, temp);
            }
            // scelgo casuali
            gruppiAdapter.clear();
            elencoStudenti.addAll(studenti4A);
            //gruppiAdapter.addAll(elencoStudenti);
            System.out.println("4A: " + studenti4A);
            System.out.println("Elenco: " + elencoStudenti);

        } else if (id == R.id.random1A) {
            classeSelezionata = "1A";
            // Mescolo A
            // Riordina a caso studenti4B
            int a, b;
            Random rn = new Random();
            for (int i = 0; i < studenti1A.size(); i++) {
                a = rn.nextInt(studenti1A.size());
                b = rn.nextInt(studenti1A.size());
                //System.out.println(a + " " + b+ " " + studenti4B.size());
                String temp = studenti1A.get(a);
                studenti1A.set(a, studenti1A.get(b));
                studenti1A.set(b, temp);
            }

            // scelgo casuali
            gruppiAdapter.clear();
            elencoStudenti.addAll(studenti1A);
            //gruppiAdapter.addAll(elencoStudenti);
            System.out.println("1A: " + studenti1A);
            System.out.println("Elenco: " + elencoStudenti);
        } else if (id == R.id.random2A) {
            classeSelezionata = "2A";
            // Mescolo A
            // Riordina a caso studenti4B
            int a, b;
            Random rn = new Random();
            for (int i = 0; i < studenti2A.size(); i++) {
                a = rn.nextInt(studenti2A.size());
                b = rn.nextInt(studenti2A.size());
                //System.out.println(a + " " + b+ " " + studenti4B.size());
                String temp = studenti2A.get(a);
                studenti2A.set(a, studenti2A.get(b));
                studenti2A.set(b, temp);
            }

            // scelgo casuali
            gruppiAdapter.clear();
            elencoStudenti.addAll(studenti2A);
            //gruppiAdapter.addAll(elencoStudenti);
            System.out.println("1A: " + studenti2A);
            System.out.println("Elenco: " + elencoStudenti);
        }

        mShareIntent.putExtra(Intent.EXTRA_TEXT, elencoStudenti.toString());

        return super.onOptionsItemSelected(item);
    }
}
