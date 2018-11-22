package by.bsuir.kaziukovich.archive.server.command.impl.record;

import by.bsuir.kaziukovich.archive.domain.record.StudentRecord;
import by.bsuir.kaziukovich.archive.server.command.Command;
import by.bsuir.kaziukovich.archive.server.command.CommandException;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import java.util.ArrayList;
import java.util.Objects;

public class GetAllRecordsCommand implements Command {
    private final String splitter;

    private String createRecordStringRepresentation(StudentRecord studentRecord) {
        return studentRecord.getSurname() + splitter + studentRecord.getName() + splitter
                + studentRecord.getDateOfBirth() + splitter + studentRecord.getGroup() + splitter
                + studentRecord.getAddress();
    }

    @Override
    public String[] execute(String[] request) throws CommandException {
        ArrayList<String> result = new ArrayList<>();

        for (StudentRecord student : StudentRecordDaoFactory.getDao().getAll()) {
            result.add(createRecordStringRepresentation(student));
        }

        return result.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object o) {
        GetAllRecordsCommand toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        toCompare = (GetAllRecordsCommand) o;
        return Objects.equals(splitter, toCompare.splitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(splitter);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@splitter: " + splitter;
    }

    public GetAllRecordsCommand() {
        splitter = "\n";
    }
}
