package lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception;

public class DatabaseNotInitializedException extends Exception{
    public DatabaseNotInitializedException(String detailMessage) {
        super(detailMessage);
    }

    public DatabaseNotInitializedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}