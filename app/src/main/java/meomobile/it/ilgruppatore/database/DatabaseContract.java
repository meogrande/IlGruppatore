package meomobile.it.ilgruppatore.database;

import android.provider.BaseColumns;

/**
 * Creato da fabio on 29/02/2016.
 */
public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor. Consigliano il costruttore vuoto
    public DatabaseContract() {
    }

    /* Definisce il contentuto della classe gruppo*/
    public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "list";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LISTA = "list";
        public static final String COLUMN_NAME_CLASSE = "class";
        public static final String COLUMN_NAME_DATA = "data";
    }

    /* Tabella classi */
    public static abstract class ClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "class";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
