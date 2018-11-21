package by.bsuir.kaziukovich.archive.server.data;

import by.bsuir.kaziukovich.archive.server.data.record.RecordReadWriteException;

import java.util.List;

public interface ListReaderWriter<T> {
    List<T> readFrom(String path) throws RecordReadWriteException;

    void writeTo(List<T> infoList, String path) throws RecordReadWriteException;
}
