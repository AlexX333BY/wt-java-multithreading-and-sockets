package by.bsuir.kaziukovich.archive.server.view.impl;

import by.bsuir.kaziukovich.archive.domain.request.Request;
import java.net.Socket;

public class SocketRequest {
    private final Socket socket;

    private final Request request;

    public Socket getSocket() {
        return socket;
    }

    public Request getRequest() {
        return request;
    }

    public SocketRequest(Socket socket, Request request) {
        this.request = request;
        this.socket = socket;
    }
}
