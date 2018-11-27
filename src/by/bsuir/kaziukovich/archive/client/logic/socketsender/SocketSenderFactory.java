package by.bsuir.kaziukovich.archive.client.logic.socketsender;

import java.net.Socket;

public class SocketSenderFactory {
    public static SocketSender getSocketSender(Socket socket) {
        return new by.bsuir.kaziukovich.archive.client.logic.socketsender.impl.SocketSender(socket);
    }

    private SocketSenderFactory() { }
}
