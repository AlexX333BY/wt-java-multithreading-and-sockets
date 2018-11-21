package by.bsuir.kaziukovich.archive.server.dataaccess.record;

import by.bsuir.kaziukovich.archive.domain.record.StudentRecord;
import by.bsuir.kaziukovich.archive.server.dataaccess.Dao;
import by.bsuir.kaziukovich.archive.server.dataaccess.DaoException;
import java.util.Date;
import java.util.List;

public interface StudentRecordDao extends Dao<StudentRecord> {
    StudentRecord get(String surname, String name) throws DaoException;

    List<StudentRecord> get(int group);

    void delete(String surname, String name) throws DaoException;

    void update(int group, String name, String surname, String address, Date dateOfBirth) throws DaoException;

    void add(int group, String name, String surname, String address, Date dateOfBirth) throws DaoException;
}
