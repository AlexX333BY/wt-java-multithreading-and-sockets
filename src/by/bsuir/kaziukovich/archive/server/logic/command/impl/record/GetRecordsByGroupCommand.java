package by.bsuir.kaziukovich.archive.server.logic.command.impl.record;

import by.bsuir.kaziukovich.archive.domain.record.StudentRecord;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.kaziukovich.archive.server.logic.command.Command;
import by.bsuir.kaziukovich.archive.server.logic.command.CommandException;

import java.util.ArrayList;
import java.util.Objects;

public class GetRecordsByGroupCommand implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 1;

    private final String splitter;

    private String createRecordStringRepresentation(StudentRecord studentRecord) {
        return studentRecord.getSurname() + splitter + studentRecord.getName() + splitter
                + studentRecord.getDateOfBirth() + splitter + studentRecord.getGroup() + splitter
                + studentRecord.getAddress();
    }

    @Override
    public String[] execute(String[] request) throws CommandException {
        ArrayList<String> result = new ArrayList<>();

        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        for (StudentRecord student : StudentRecordDaoFactory.getDao().get(Integer.parseInt(request[0]))) {
            result.add(createRecordStringRepresentation(student));
        }

        return result.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return Objects.equals(splitter, ((GetRecordsByGroupCommand) o).splitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(splitter);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@splitter: " + splitter;
    }

    public GetRecordsByGroupCommand() {
        splitter = "\n";
    }
}
