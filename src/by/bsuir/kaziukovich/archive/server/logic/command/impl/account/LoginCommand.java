package by.bsuir.kaziukovich.archive.server.logic.command.impl.account;

import by.bsuir.kaziukovich.archive.server.logic.digest.PasswordDigestException;
import by.bsuir.kaziukovich.archive.server.logic.digest.PasswordDigestGeneratorFactory;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.account.AccountDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class LoginCommand implements Command {
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
            return new String[] {Boolean.toString(AccountDaoFactory.getDao().get(request[0]).getPasswordHash()
                            .equals(PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(request[1])))};
        } catch (DaoException e) {
            throw new CommandException("Error adding new account " + request[0], e);
        } catch (PasswordDigestException e) {
            throw new CommandException("Error generating password digest", e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
