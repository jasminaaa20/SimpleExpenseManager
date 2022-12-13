package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.DatabaseNotInitializedException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentAccountDAO implements AccountDAO {
    // instance of the database
    SQLiteDatabase db;

    public PersistentAccountDAO() {
        try {
            db = SQLiteContext.getDatabase();
        } catch (DatabaseNotInitializedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> getAccountNumbersList() {
        List<String> accountNumberList = new ArrayList<>();
        Cursor mCursor = db.rawQuery("select accountNo from Account", null);
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            accountNumberList.add(mCursor.getString(mCursor.getColumnIndex("accountNo")));
        }
        mCursor.close();
        return accountNumberList;
    }

    @Override
    public List<Account> getAccountsList() {
        List<Account> accountNumberList = new ArrayList<>();
        Cursor mCursor = db.rawQuery("select * from Account", null);
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            Account account = new Account(
                    mCursor.getString(mCursor.getColumnIndex("accountNo")),
                    mCursor.getString(mCursor.getColumnIndex("bankName")),
                    mCursor.getString(mCursor.getColumnIndex("accountHolderName")),
                    mCursor.getDouble(mCursor.getColumnIndex("balance")));
            accountNumberList.add(account);
        }
        mCursor.close();
        return accountNumberList;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        String[] args = {accountNo};
        Account account = null;
        Cursor mCursor = db.rawQuery("select * from Account where accountNo = ?", args);
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            account = new Account(
                    mCursor.getString(mCursor.getColumnIndex("accountNo")),
                    mCursor.getString(mCursor.getColumnIndex("bankName")),
                    mCursor.getString(mCursor.getColumnIndex("accountHolderName")),
                    mCursor.getDouble(mCursor.getColumnIndex("balance")));
            break;
        }
        mCursor.close();
        if (account == null) {
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        return account;
    }

    @Override
    public void addAccount(Account account) {
        try {
            Object[] args = {account.getAccountNo(), account.getBankName(), account.getAccountHolderName(), account.getBalance()};
            db.execSQL("insert into Account values(?, ?, ?, ?)", args);
        } catch (Exception e) {
            Log.e("exception handled", e.getMessage());
        }
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        /*
         *   throws invalid account exception if the account no is invalid
         */
        Account account = getAccount(accountNo);

        try {
            Object[] args = {account.getAccountNo()};
            db.execSQL("Delete from Account where accountNo = ?", args);
        } catch (Exception e) {
            Log.e("exception handled", e.getMessage());
        }
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        /*
         *   throws invalid account exception if the account no is invalid
         */
        Account account = getAccount(accountNo);

        // specific implementation based on the transaction type
        switch (expenseType) {
            case EXPENSE:
                account.setBalance(account.getBalance() - amount);
                break;
            case INCOME:
                account.setBalance(account.getBalance() + amount);
                break;
        }

        try {
            String query = "Update Account set balance = " + account.getBalance() + " where accountNo = \'" + accountNo + "\'";
            db.execSQL(query);
        } catch (Exception e) {
            Log.e("exception handled", e.getMessage());
        }
    }
}
