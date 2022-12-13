package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;

import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentAccountDAO implements AccountDAO {
    SQLiteHelper dbHelper;

    public PersistentAccountDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    @Override
    public List<String> getAccountNumbersList() {
        return dbHelper.getAccountNumbersList();
    }

    @Override
    public List<Account> getAccountsList() {
        return dbHelper.getAccountsList();
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        return dbHelper.getAccount(accountNo);
    }

    @Override
    public void addAccount(Account account) {
        dbHelper.addAccount(account);
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        dbHelper.removeAccount(accountNo);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        dbHelper.updateBalance(accountNo, expenseType, amount);
    }
}
