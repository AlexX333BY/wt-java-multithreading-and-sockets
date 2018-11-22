package by.bsuir.kaziukovich.archive.logic.command.impl.record;

import by.bsuir.kaziukovich.archive.domain.record.StudentRecord;
import by.bsuir.kaziukovich.archive.logic.command.Command;
import by.bsuir.kaziukovich.archive.logic.command.CommandException;
import by.bsuir.kaziukovich.archive.logic.command.request.CommandRequest;
import by.bsuir.kaziukovich.archive.logic.command.response.CommandResponse;
import by.bsuir.kaziukovich.archive.logic.command.response.CommandResponseCode;
import by.bsuir.kaziukovich.archive.server.dataaccess.record.StudentRecordDaoFactory;
import java.util.ArrayList;

public class GetAllRecordsCommand implements Command {
    private final String splitter;

    private String createRecordStringRepresentation(StudentRecord studentRecord) {
        return studentRecord.getSurname() + splitter + studentRecord.getName() + splitter
                + studentRecord.getDateOfBirth() + splitter + studentRecord.getGroup() + splitter
                + studentRecord.getAddress();
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        ArrayList<String> result = new ArrayList<>();

        for (StudentRecord student : StudentRecordDaoFactory.getDao().getAll()) {
            result.add(createRecordStringRepresentation(student));
        }

        return new CommandResponse(CommandResponseCode.SUCCESS, result.toArray(new String[0]));
    }

    public GetAllRecordsCommand() {
        splitter = "\n";
    }
}
