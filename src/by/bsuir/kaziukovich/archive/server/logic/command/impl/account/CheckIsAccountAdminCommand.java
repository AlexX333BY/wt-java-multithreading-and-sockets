package by.bsuir.kaziukovich.archive.server.logic.command.impl.account;

import by.bsuir.kaziukovich.archive.domain.account.UserRole;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class CheckIsAccountAdminCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 1;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            return new String[]
                    {Boolean.toString(AccountDaoFactory.getDao().get(request[0]).getRole() == UserRole.ADMIN)};
        } catch (DaoException e) {
            throw new CommandException("Error acquiring admin info of " + request[0], e);
        }
    }
}
