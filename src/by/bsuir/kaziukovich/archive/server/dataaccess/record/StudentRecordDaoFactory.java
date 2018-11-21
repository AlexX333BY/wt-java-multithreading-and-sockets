package by.bsuir.kaziukovich.archive.server.dataaccess.record;

public class StudentRecordDaoFactory {
    private static final StudentRecordDao dao
            = new by.bsuir.kaziukovich.archive.server.dataaccess.record.impl.StudentRecordDao();

    public static StudentRecordDao getDao() {
        return dao;
    }

    private StudentRecordDaoFactory() { }
}
