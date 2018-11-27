package by.bsuir.kaziukovich.archive.domain.request;

public interface Request {
    String getRequestCode();

    String[] getRequestContent();

    String getRequester();
}
