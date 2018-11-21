package by.bsuir.kaziukovich.archive.domain.record.impl;

import by.bsuir.kaziukovich.archive.domain.record.StudentRecord;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ImmutableStudentRecord implements StudentRecord {
    private int group;

    private String name;

    private String surname;

    private String address;

    private Date dateOfBirth;

    @Override
    public int getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        ImmutableStudentRecord toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (ImmutableStudentRecord) o;
        return (group == toCompare.group) && Objects.equals(name, toCompare.name)
                && Objects.equals(surname, toCompare.surname) && Objects.equals(address, toCompare.address)
                && Objects.equals(dateOfBirth, toCompare.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, name, surname, address, dateOfBirth);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@group: " + group + ", name: " + name + ", surname: " + surname + ", address: "
                + address + ", dateOfBirth: " + dateOfBirth;
    }

    public ImmutableStudentRecord(int group, String name, String surname, String address, Date dateOfBirth) {
        if ((name == null) || (surname == null) || (address == null) || (dateOfBirth == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }
        if (group < 0) {
            throw new IllegalArgumentException("Group cannot be negative");
        }
        if (dateOfBirth.after(Calendar.getInstance().getTime())) {
            throw new IllegalArgumentException("Date of birth cannot be after current date");
        }

        this.group = group;
        this.name = name.trim();
        this.surname = surname.trim();
        this.address = address.trim();
        this.dateOfBirth = dateOfBirth;

        if (this.address.isEmpty() || this.surname.isEmpty() || this.name.isEmpty()) {
            throw new IllegalArgumentException("String arguments shouldn't be empty");
        }
    }
}
