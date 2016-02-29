package meomobile.it.ilgruppatore.database;

import android.provider.BaseColumns;

/**
 * Creato da fabio on 29/02/2016.
 */
public final class GroupContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor. Consigliano il costruttore vuoto
    public GroupContract() {
    }

    /* Definisce il contentuto della classe */
    public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "gruppo";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LISTA = "lista";
        public static final String COLUMN_NAME_CLASSE = "classe";
        public static final String COLUMN_NAME_DATA = "data";
    }
}
