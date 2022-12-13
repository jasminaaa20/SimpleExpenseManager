package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class PersistentExpenseManager extends ExpenseManager{

    public PersistentExpenseManager() {
        setup();
    }

    @Override
    public void setup() {

        TransactionDAO persistentTransactionDAO = new PersistentTransactionDAO();
        setTransactionsDAO(persistentTransactionDAO);

        AccountDAO persistentAccountDAO = new PersistentAccountDAO();
        setAccountsDAO(persistentAccountDAO);

        // dummy data
        Account dummyAccount1 = new Account("10929C", "Forward Bank", "Tony Stark", 10000.0);
        Account dummyAccount2 = new Account("93845P", "Backward Bank", "Bruce Banner", 80000.0);
        getAccountsDAO().addAccount(dummyAccount1);
        getAccountsDAO().addAccount(dummyAccount2);

    }
}