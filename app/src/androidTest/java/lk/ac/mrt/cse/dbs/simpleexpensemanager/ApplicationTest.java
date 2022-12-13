/*
 * Copyright 2015 Department of Computer Science and Engineering, University of Moratuwa.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest {
     private ExpenseManager expenseManager;

     @Before
     public void setUpPersistentStorage() throws ExpenseManagerException {
          Context context = ApplicationProvider.getApplicationContext();
          expenseManager = new PersistentExpenseManager(context);
     }

     @Test
     public void testAddAccount () {
          expenseManager.addAccount("0000", "Test Bank", "Test Name", 1000.0);
          assertTrue(expenseManager.getAccountNumbersList().contains("0000"));
     }

     @Test
     public void testAddTransaction() throws InvalidAccountException {
          String accNo = "0000";
          ExpenseType expenseType = ExpenseType.INCOME;

          expenseManager.updateAccountBalance(accNo, 1, 0, 2022, expenseType, "100.0");

          List<Transaction> transactionList = expenseManager.getTransactionLogs();
          Transaction lastTransaction = transactionList.get(transactionList.size() - 1);

          SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
          String dateStr = dateFormat.format(lastTransaction.getDate());

          assertTrue(dateStr.equals("01-01-2022") && lastTransaction.getAccountNo().equals(accNo) && lastTransaction.getExpenseType().equals(expenseType) && lastTransaction.getAmount() == 100.0);
          assertEquals(1100.0, expenseManager.getAccountsDAO().getAccount(accNo).getBalance(), 0.0);
     }

}