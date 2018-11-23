package by.bsuir.kaziukovich.archive.server.view.impl;

import by.bsuir.kaziukovich.archive.domain.logger.Logger;
import by.bsuir.kaziukovich.archive.domain.request.Request;
import by.bsuir.kaziukovich.archive.server.view.SocketListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MultithreadSocketListener implements SocketListener, Runnable {
    private final int port;

    private final ConcurrentLinkedQueue<SocketRequest> requests;

    private volatile boolean shouldRun;

    private Thread processingThread;

    @Override
    public void startListen() {
        ServerSocket serverSocket;
        Socket clientSocket;
        Request request;
        shouldRun = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Logger.log(new SocketException("Error while creating server socket", e));
            return;
        }

        processingThread.start();

        while (shouldRun) {
            try {
                clientSocket = serverSocket.accept();
                request = (Request) new ObjectInputStream(clientSocket.getInputStream()).readObject();
                if (request != null) {
                    synchronized (requests) {
                        requests.add(new SocketRequest(clientSocket, request));
                        requests.notify();
                    }
                }
            } catch (IOException e) {
                Logger.log(new SocketException("Error accepting user socket", e));
            } catch (ClassNotFoundException | ClassCastException e) {
                Logger.log(new SocketException("Illegal object input", e));
            }
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            Logger.log(new SocketException("Error closing server exception", e));
        }
    }

    @Override
    public void stopListen() {
        requests.add(null);
        shouldRun = false;
    }

    @Override
    public void waitForStop() {
        try {
            processingThread.join();
        } catch (InterruptedException e) {
            Logger.log(new SocketException("Thread was interrupted while joining prosessing thread", e));
        }
    }

    public MultithreadSocketListener(int port) {
        if ((port < 1024) || (port > 65535)) {
            throw new IllegalArgumentException("Port should be between 1024 and 65535");
        }

        this.port = port;
        requests = new ConcurrentLinkedQueue<>();
        processingThread = new Thread(new MultithreadSocketRequestProcessor(requests));
    }

    @Override
    public void run() {
        startListen();
    }
}
