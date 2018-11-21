package by.bsuir.kaziukovich.archive.server.data.record;

public class RecordReadWriteException extends Exception {
    public RecordReadWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordReadWriteException(String message) {
        super(message);
    }
}
