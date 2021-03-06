package by.bsuir.kaziukovich.archive.server.logic.command.impl.record;

import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

public class UpdateRecordStorageCommand implements Command {
    @Override
    public String[] execute(String[] request) throws CommandException {
        try {
            StudentRecordDaoFactory.getDao().updateInStorage();
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error updating in storage", e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
