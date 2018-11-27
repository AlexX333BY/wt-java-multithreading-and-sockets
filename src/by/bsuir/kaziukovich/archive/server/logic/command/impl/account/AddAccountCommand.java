package by.bsuir.kaziukovich.archive.server.logic.command.impl.account;

import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDao;
import by.bsuir.kaziukovich.archive.server.domain.account.UserRole;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class AddAccountCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 2;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }
        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            AccountDao dao = AccountDaoFactory.getDao();

            dao.add(request[0], request[1], UserRole.USER);
            dao.updateInStorage();
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error adding new account " + request[0], e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
