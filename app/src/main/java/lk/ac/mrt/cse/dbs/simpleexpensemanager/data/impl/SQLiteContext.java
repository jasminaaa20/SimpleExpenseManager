package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.DatabaseNotInitializedException;

public class SQLiteContext {
    private static SQLiteDatabase database = null;
    public static SQLiteDatabase getDatabase() throws DatabaseNotInitializedException {
        if (database == null)
            throw new DatabaseNotInitializedException("Database Not Initialized");
        return database;
    }
    public static void initDatabase(SQLiteHelper SQLiteHelper) {
        if (database == null) {
            database = SQLiteHelper.getWritableDatabase();
        }
    }
}
