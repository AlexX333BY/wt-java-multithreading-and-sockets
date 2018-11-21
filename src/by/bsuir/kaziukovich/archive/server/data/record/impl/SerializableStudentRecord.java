package by.bsuir.kaziukovich.archive.server.data.record.impl;

import by.bsuir.kaziukovich.archive.domain.record.StudentRecord;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class SerializableStudentRecord implements StudentRecord, Serializable {
    private int group;

    private String name;

    private String surname;

    private String address;

    private Date dateOfBirth;

    public void setGroup(int group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

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
        SerializableStudentRecord toCompare = (SerializableStudentRecord) o;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

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

    SerializableStudentRecord(int group, String name, String surname, String address, Date dateOfBirth) {
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

    SerializableStudentRecord(StudentRecord record) {
        this(record.getGroup(), record.getName(), record.getSurname(), record.getAddress(), record.getDateOfBirth());
    }

    public SerializableStudentRecord() { }
}
