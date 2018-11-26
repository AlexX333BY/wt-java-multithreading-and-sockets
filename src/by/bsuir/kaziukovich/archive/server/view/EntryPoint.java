package by.bsuir.kaziukovich.archive.server.view;

import by.bsuir.kaziukovich.archive.domain.console.ConsoleScanner;
import by.bsuir.kaziukovich.archive.domain.logger.Logger;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.kaziukovich.archive.server.view.impl.MultithreadSocketListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class EntryPoint {
    private static final String SHUTDOWN_KEYWORD = "shutdown";

    public static void main(String[] args) {
        MultithreadSocketListener listener;
        Thread listenerThread;

        if (args.length < 3) {
            Logger.log(new ViewException("At least 3 arguments needed: port, account file path and records file path"));
            return;
        }

        if (args.length > 3) {
            try {
                Logger.setErrorStream(new PrintStream(args[3]));
            } catch (FileNotFoundException e) {
                Logger.log(new ViewException("Error setting error stream", e));
                return;
            }
        }

        try {
            AccountDaoFactory.getDao().loadFrom(args[0]);
            StudentRecordDaoFactory.getDao().loadFrom(args[1]);
        } catch (DaoException e) {
            Logger.log(e);
            return;
        }

        try {
            listener = new MultithreadSocketListener(Integer.parseInt(args[0]));
        } catch (IllegalArgumentException e) {
            Logger.log(e);
            return;
        }

        listenerThread = new Thread(listener);
        listenerThread.run();

        while (!ConsoleScanner.getNonEmptyString().equals(SHUTDOWN_KEYWORD));

        listener.stopListen();
        listener.waitForStop();
        try {
            listenerThread.join();
        } catch (InterruptedException e) {
            Logger.log(new ViewException("Iterrupted while joining listener thread", e));
        }
    }
}
