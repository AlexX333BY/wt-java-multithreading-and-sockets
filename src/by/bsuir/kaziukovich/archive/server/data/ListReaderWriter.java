package by.bsuir.kaziukovich.archive.server.data;

import java.util.List;

public interface ListReaderWriter<T> {
    List<T> readFrom(String path) throws ReadWriteException;

    void writeTo(List<T> infoList, String path) throws ReadWriteException;
}
