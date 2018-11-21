package by.bsuir.kaziukovich.archive.server.data.record;

import by.bsuir.kaziukovich.archive.server.data.record.impl.StudentRecordXmlFileReaderWriter;

public class StudentRecordReaderWriterFactory {
    private static final StudentRecordReaderWriter readerWriter = new StudentRecordXmlFileReaderWriter();

    public static StudentRecordReaderWriter getReaderWriter() {
        return readerWriter;
    }

    private StudentRecordReaderWriterFactory() { }
}
