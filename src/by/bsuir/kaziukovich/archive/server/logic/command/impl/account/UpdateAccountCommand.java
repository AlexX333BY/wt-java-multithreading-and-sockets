package by.bsuir.kaziukovich.archive.server.logic.command.impl.account;

import by.bsuir.kaziukovich.archive.domain.account.UserRole;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class UpdateAccountCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 3;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            AccountDaoFactory.getDao().update(request[0], request[1], UserRole.valueOf(request[3]));
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error updating account " + request[0], e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
