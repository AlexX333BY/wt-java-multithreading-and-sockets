package by.bsuir.kaziukovich.archive.server.view.impl;

import by.bsuir.kaziukovich.archive.server.view.SocketListener;

public class MultiThreadSocketListener implements SocketListener {
    private final int port;

    @Override
    public void startListen() {

    }

    public MultiThreadSocketListener(int port) {
        if ((port < 1024) || (port > 65535)) {
            throw new IllegalArgumentException("Port should be between 1024 and 65535");
        }

        this.port = port;
    }
}
