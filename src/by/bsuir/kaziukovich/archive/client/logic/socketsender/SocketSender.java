package by.bsuir.kaziukovich.archive.client.logic.socketsender;

import by.bsuir.kaziukovich.archive.domain.request.Request;
import by.bsuir.kaziukovich.archive.domain.response.Response;

public interface SocketSender {
    Response send(Request request) throws SocketSenderException;
}
