package by.bsuir.kaziukovich.archive.server.data;

public class ReadWriteException extends Exception {
    public ReadWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadWriteException(String message) {
        super(message);
    }
}
