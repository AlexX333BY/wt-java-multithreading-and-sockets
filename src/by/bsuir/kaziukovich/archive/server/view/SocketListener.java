package by.bsuir.kaziukovich.archive.server.view;

public interface SocketListener {
    void startListen();

    void stopListen();

    void waitForStop();
}
