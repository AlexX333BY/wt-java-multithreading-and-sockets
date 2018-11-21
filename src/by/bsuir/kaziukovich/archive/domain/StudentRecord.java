package by.bsuir.kaziukovich.archive.domain;

import java.util.Date;

public class StudentRecord extends UserRecord {
    private final int group;

    public int getGroup() {
        return group;
    }

    public StudentRecord(String name, String surname, String address, Date dateOfBirth, int group) {
        super(name, surname, address, dateOfBirth);

        if (group < 0) {
            throw new IllegalArgumentException("Group cannot be negative");
        }

        this.group = group;
    }
}
