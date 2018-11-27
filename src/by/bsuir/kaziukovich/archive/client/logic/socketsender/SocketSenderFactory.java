package by.bsuir.kaziukovich.archive.client.logic.socketsender;

import java.net.InetAddress;

public class SocketSenderFactory {
    public static SocketSender getSocketSender(InetAddress address, int port) {
        return new by.bsuir.kaziukovich.archive.client.logic.socketsender.impl.SocketSender(address, port);
    }

    private SocketSenderFactory() { }
}
