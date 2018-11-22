package by.bsuir.kaziukovich.archive.server.logic.command.impl.record;

import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class DeleteRecordCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 2;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length < REQUIRED_ARGUMENTS_COUNT)) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            StudentRecordDaoFactory.getDao().delete(request[0], request[1]);
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error deleting record " + request[0] + ' ' + request[1], e);
        }
    }
}
