package jianxuan.li.jllibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String DBNAME = "JLLibrary.db";
    static final int VERSION = 1;

    static final String[] TABLES = {"User", "Stock", "Issue", "Return", "Publisher"};
    static final String[] COLS_USER = {
            "userId INTEGER PRIMARY KEY AUTOINCREMENT",
            "username TEXT NOT NULL",
            "emailId TEXT NOT NULL",
            "password TEXT NOT NULL"};
    static final String[] COLS_STOCK = {
            "bookId INTEGER PRIMARY KEY AUTOINCREMENT",
            "isbn TEXT",
            "bookTitle TEXT",
            "publisher TEXT",
            "qtyStock INTEGER",
            "price REAL"};
    static final String[] COLS_ISSUE = {
            "issueId INTEGER PRIMARY KEY AUTOINCREMENT",
            "bookId INTEGER",
            "customerName Text",
            "customerEmail TEXT",
            "qtyIssued INTEGER",
            "dateofIssue TEXT"};
    static final String[] COLS_RETURN = {
            "returnId INTEGER PRIMARY KEY AUTOINCREMENT",
            "issueId INTEGER",
            "bookId INTEGER",
            "qtyReturned INTEGER",
            "dateofReturn TEXT"};
    static final String[] COLS_PUBLISHER = {
            "publisherId INTEGER PRIMARY KEY AUTOINCREMENT",
            "publisher TEXT"};

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    // get create table statement
    public String getTableCreateStatement(String tableName, String[] columns) {
        String statement = "create table " + tableName + " (";
        for (int i = 0; i < columns.length; i++) {
            statement += columns[i];
            if (i < columns.length - 1) {
                statement += ", ";
            }
        }
        statement += ");";
        return statement;
    }

    // create tables in database
    public void createTables(SQLiteDatabase db, String[] tableNames, String[][] columns) {
        for (int i = 0; i < tableNames.length; i++) {
            db.execSQL(getTableCreateStatement(tableNames[i], columns[i]));
        }
    }

    // drop tables in database
    public void dropTables(SQLiteDatabase db, String[] tableNames) {
        for (int i = 0; i < tableNames.length; i++) {
            db.execSQL("DROP TABLE IF EXISTS " + tableNames[i]);
        }
    }

    // write default data to publisher table
    public void writeDefaultData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Publisher (publisher) VALUES ('MacMillan');");
        db.execSQL("INSERT INTO Publisher (publisher) VALUES ('Penguin Random House');");
        db.execSQL("INSERT INTO Publisher (publisher) VALUES ('Pearson Education');");
        db.execSQL("INSERT INTO Publisher (publisher) VALUES ('McGraw Hill Education');");
        db.execSQL("INSERT INTO Publisher (publisher) VALUES ('Wiley');");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase, TABLES,
                new String[][]{COLS_USER, COLS_STOCK, COLS_ISSUE, COLS_RETURN, COLS_PUBLISHER});
        writeDefaultData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropTables(sqLiteDatabase, TABLES);
        onCreate(sqLiteDatabase);
    }
}
