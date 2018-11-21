package by.bsuir.kaziukovich.archive.server.data;

import by.bsuir.kaziukovich.archive.domain.UserRecord;
import java.util.List;

public interface UserRecordReaderWriter<T extends UserRecord> {
    List<T> readFrom(String path) throws RecordReadWriteException;

    void writeTo(List<T> infoList, String path) throws RecordReadWriteException;
}
