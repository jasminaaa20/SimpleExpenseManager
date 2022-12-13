package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;

import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentTransactionDAO implements TransactionDAO {
    SQLiteHelper databasehelper;

    public PersistentTransactionDAO (Context context) {
        databasehelper = new SQLiteHelper(context);
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        databasehelper.logTransaction(date, accountNo, expenseType, amount);
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return databasehelper.getAllTransactionLogs();
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return databasehelper.getPaginatedTransactionLogs(limit);
    }
}
