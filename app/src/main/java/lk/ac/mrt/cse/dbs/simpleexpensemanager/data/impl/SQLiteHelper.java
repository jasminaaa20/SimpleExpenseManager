package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "200238N.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE Account(AccountNumber TEXT PRIMARY KEY, Bank TEXT NOT NULL, AccountHolderName TEXT NOT NULL, Balance REAL DEFAULT 0);");
        database.execSQL("CREATE TABLE Transactions(TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, AccountNumber TEXT NOT NULL, ExpenseType TEXT NOT NULL, Amount REAL DEFAULT 0, Date DATE NOT " +
                "NULL,FOREIGN KEY " +
                "(AccountNumber)" +
                " REFERENCES Account(AccountNumber));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int preVersion, int newVersion) {
        int upgradeTo = preVersion;
        while (upgradeTo < newVersion) {
            switch (upgradeTo) {
                case 1:
                    // no incremental change yet
                    break;
            }
            upgradeTo++;
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int preVersion, int newVersion) {
        // no downgrade changes
    }
}
