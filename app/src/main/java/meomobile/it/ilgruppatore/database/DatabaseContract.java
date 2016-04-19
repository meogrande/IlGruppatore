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
    public static abstract class ListEntry implements BaseColumns {
        public static final String TABLE_NAME = "list";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LISTA = "list";
        public static final String COLUMN_NAME_CLASSE = "class";
        public static final String COLUMN_NAME_DATA = "data";

        // SQL per creare la tabella
        static final String SQL_CREATE =
                "CREATE TABLE list (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, class TEXT, list TEXT, data DATE DEFAULT (datetime('now')) )";
        // SQL per cancellare la tabella
        static final String SQL_DROPTABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    }

    /* Tabella classi */
    public static abstract class TeamEntry implements BaseColumns {
        public static final String TABLE_NAME = "team";
        public static final String COLUMN_NAME_NAME = "name";

        // SQL per creare la tabella
        static final String SQL_CREATE =
                "CREATE TABLE team (name TEXT PRIMARY KEY NOT NULL)";
        // SQL per cancellare la tabella
        static final String SQL_DROPTABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    }

    /* Tabella task */
    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_ID = "team";
        public static final String COLUMN_NAME_TEAM = "team";
        public static final String COLUMN_NAME_DATA = "data";
        // SQL per creare la tabella
        static final String SQL_CREATE =
                "CREATE TABLE task (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, team TEXT, data DATE DEFAULT (datetime('now')))";
        // SQL per cancellare la tabella
        static final String SQL_DROPTABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }

    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        // SQL per creare la tabella
        static final String SQL_CREATE =
                "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, team TEXT)";
        // SQL per cancellare la tabella
        static final String SQL_DROPTABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
