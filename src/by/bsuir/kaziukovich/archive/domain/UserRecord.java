package by.bsuir.kaziukovich.archive.domain;

import java.util.Calendar;
import java.util.Date;

public class UserRecord {
    private final String name;

    private final String surname;

    private final String address;

    private final Date dateOfBirth;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public UserRecord(String name, String surname, String address, Date dateOfBirth) {
        if ((name == null) || (surname == null) || (address == null) || (dateOfBirth == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }
        if (dateOfBirth.after(Calendar.getInstance().getTime())) {
            throw new IllegalArgumentException("Date of birth cannot be after current");
        }

        this.name = name.trim();
        this.surname = surname.trim();
        this.address = address.trim();
        this.dateOfBirth = dateOfBirth;

        if (this.name.isEmpty() || this.surname.isEmpty() || this.address.isEmpty()) {
            throw new IllegalArgumentException("Arguments cannot be empty");
        }
    }
}
