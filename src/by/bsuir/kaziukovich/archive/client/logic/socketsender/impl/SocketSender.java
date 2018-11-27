package by.bsuir.kaziukovich.archive.client.logic.socketsender.impl;

import by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSenderException;
import by.bsuir.kaziukovich.archive.domain.request.Request;
import by.bsuir.kaziukovich.archive.domain.response.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketSender implements by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSender {
    private final InetAddress destinationAddress;

    private final int destinationPort;

    @Override
    public Response send(Request request) throws SocketSenderException {
        try {
            Socket destinationSocket = new Socket(destinationAddress, destinationPort);

            new ObjectOutputStream(destinationSocket.getOutputStream()).writeObject(request);
            return (Response) new ObjectInputStream(destinationSocket.getInputStream()).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SocketSenderException("Error sending request to destination", e);
        }
    }

    public SocketSender(InetAddress destinationAddress, int destinationPort) {
        this.destinationAddress = destinationAddress;
        this.destinationPort = destinationPort;
    }
}
