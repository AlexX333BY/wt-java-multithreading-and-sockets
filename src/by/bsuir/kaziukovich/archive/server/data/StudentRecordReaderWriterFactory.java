package by.bsuir.kaziukovich.archive.server.data;

import by.bsuir.kaziukovich.archive.server.data.impl.StudentRecordXmlFileReaderWriter;

public class StudentRecordReaderWriterFactory {
    private static final StudentRecordReaderWriter readerWriter = new StudentRecordXmlFileReaderWriter();

    public static StudentRecordReaderWriter getReaderWriter() {
        return readerWriter;
    }

    private StudentRecordReaderWriterFactory() { }
}
