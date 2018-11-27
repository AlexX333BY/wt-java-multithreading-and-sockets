package by.bsuir.kaziukovich.archive.server.domain.record;

import by.bsuir.kaziukovich.archive.server.domain.record.impl.ImmutableStudentRecord;
import by.bsuir.kaziukovich.archive.server.domain.record.impl.SerializableStudentRecord;

import java.util.Date;

public class StudentRecordFactory {
    public static StudentRecord createRecord(int group, String name, String surname, String address, Date dateOfBirth) {
        return new ImmutableStudentRecord(group, name, surname, address, dateOfBirth);
    }

    public static StudentRecord createSerializableRecord(int group, String name, String surname, String address,
                                                         Date dateOfBirth) {
        return new SerializableStudentRecord(group, name, surname, address, dateOfBirth);
    }

    public static StudentRecord createSerializableRecord(StudentRecord record) {
        return createSerializableRecord(record.getGroup(), record.getName(), record.getSurname(), record.getAddress(),
                record.getDateOfBirth());
    }

    private StudentRecordFactory() { }
}
