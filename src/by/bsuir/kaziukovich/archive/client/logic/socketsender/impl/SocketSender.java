package by.bsuir.kaziukovich.archive.client.logic.socketsender.impl;

import by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSenderException;
import by.bsuir.kaziukovich.archive.domain.request.Request;
import by.bsuir.kaziukovich.archive.domain.response.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketSender implements by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSender {
    private final Socket destinationSocket;

    @Override
    public Response send(Request request) throws SocketSenderException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(destinationSocket.getOutputStream())) {
            outputStream.writeObject(request);
            try (ObjectInputStream inputStream = new ObjectInputStream(destinationSocket.getInputStream())) {
                return (Response) inputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new SocketSenderException("Error sending request to destination", e);
        }
    }

    public SocketSender(Socket destination) {
        destinationSocket = destination;
    }
}
