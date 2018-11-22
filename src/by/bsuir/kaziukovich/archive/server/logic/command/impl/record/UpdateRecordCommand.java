package by.bsuir.kaziukovich.archive.server.logic.command.impl.record;

import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.dateconverter.DateConverterFactory;
import by.bsuir.kaziukovich.archive.server.logic.dateconverter.DateConvertionException;

public class UpdateRecordCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 5;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length < REQUIRED_ARGUMENTS_COUNT)) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            StudentRecordDaoFactory.getDao().update(Integer.parseInt(request[3]), request[1], request[0], request[4],
                    DateConverterFactory.getConverter().convert(request[2]));
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error while updating record " + request[3] + ' ' + request[1], e);
        } catch (DateConvertionException e) {
            throw new IllegalArgumentException("Error converting date " + request[2]);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
