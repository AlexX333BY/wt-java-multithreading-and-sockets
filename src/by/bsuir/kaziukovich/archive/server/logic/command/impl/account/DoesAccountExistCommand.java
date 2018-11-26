package by.bsuir.kaziukovich.archive.server.logic.command.impl.account;

import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class DoesAccountExistCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 1;

    @Override
    public String[] execute(String[] request) throws CommandException {
        boolean result;

        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }
        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            AccountDaoFactory.getDao().get(request[0]);
            result = true;
        } catch (DaoException e) {
            result = false;
        }
        return new String[] {Boolean.toString(result)};
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
