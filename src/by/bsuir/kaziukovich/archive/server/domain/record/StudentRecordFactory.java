package by.bsuir.kaziukovich.archive.server.domain.record;

import by.bsuir.kaziukovich.archive.server.domain.record.impl.ImmutableStudentRecord;
import java.util.Date;

public class StudentRecordFactory {
    public static StudentRecord createRecord(int group, String name, String surname, String address, Date dateOfBirth) {
        return new ImmutableStudentRecord(group, name, surname, address, dateOfBirth);
    }

    private StudentRecordFactory() { }
}
