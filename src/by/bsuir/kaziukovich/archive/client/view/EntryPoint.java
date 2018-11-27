package by.bsuir.kaziukovich.archive.client.view;

import by.bsuir.kaziukovich.archive.client.logic.digest.PasswordDigestException;
import by.bsuir.kaziukovich.archive.client.logic.digest.PasswordDigestGeneratorFactory;
import by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSender;
import by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSenderException;
import by.bsuir.kaziukovich.archive.client.logic.socketsender.SocketSenderFactory;
import by.bsuir.kaziukovich.archive.client.logic.splitter.RequestSplitter;
import by.bsuir.kaziukovich.archive.client.view.impl.SerializableRequest;
import by.bsuir.kaziukovich.archive.domain.console.ConsoleScanner;
import by.bsuir.kaziukovich.archive.domain.request.RequestCode;
import by.bsuir.kaziukovich.archive.domain.response.Response;
import by.bsuir.kaziukovich.archive.domain.response.ResponseCode;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

public class EntryPoint {
    private static SocketSender socketSender;

    private static String username;

    private static String passwordHash;

    private static Map<ResponseCode, ResponseProcessor> responseProcessors;

    private static final String UNKNOWN_RESPONSE_MESSAGE = "Unknown response";

    private static final String EXIT_COMMAND = "exit";

    private static String getResponseMessage(Response response) {
        if (responseProcessors == null) {
            responseProcessors = ResponseProcessorFactory.getResponseProcessors();
        }

        try {
            ResponseProcessor processor = responseProcessors.get(ResponseCode.valueOf(response.getResponseCode()));

            if (processor == null) {
                return UNKNOWN_RESPONSE_MESSAGE;
            } else {
                return processor.getResponseMessage(response.getResponseContent());
            }
        } catch (IllegalArgumentException e) {
            return UNKNOWN_RESPONSE_MESSAGE;
        }
    }

    private static void setUp(String[] args) {
        InetAddress serverAddress = null;
        int port = 0;

        if (args.length >= 1) {
            try {
                serverAddress = InetAddress.getByName(args[0]);
            } catch (UnknownHostException ignored) { }
        }

        while (serverAddress == null) {
            System.out.print("Enter server host: ");
            try {
                serverAddress = InetAddress.getByName(ConsoleScanner.getNonEmptyString());
            } catch (UnknownHostException ignored) { }
        }

        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }

        while ((port < 1024) || (port > 65535)) {
            System.out.println("Enter port number between 1024 and 65535");
            port = ConsoleScanner.getPositiveInt();
        }

        socketSender = SocketSenderFactory.getSocketSender(serverAddress, port);

        if (args.length >= 3) {
            username = args[2].trim();
        } else {
            System.out.print("Enter username: ");
            username = ConsoleScanner.getNonEmptyString();
        }

        if (args.length >= 4) {
            try {
                passwordHash = PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(args[3].trim());
            } catch (PasswordDigestException ignored) { }
        }

        while (passwordHash == null) {
            System.out.print("Enter password: ");
            try {
                passwordHash = PasswordDigestGeneratorFactory.getPasswordDigestGenerator()
                        .generate(ConsoleScanner.getNonEmptyString());
            } catch (PasswordDigestException ignored) { }
        }
    }

    private static Response sendRequest(String userRequest, boolean sendAsUnauthorized) throws SocketSenderException {
        String[] splittedRequest = RequestSplitter.split(userRequest.trim());

        return socketSender.send(new SerializableRequest(splittedRequest[0], Arrays.copyOfRange(splittedRequest, 1,
                splittedRequest.length), sendAsUnauthorized ? null : username));
    }

    private static boolean tryLogin() {
        try {
            Response response = sendRequest(RequestCode.DOES_ACCOUNT_EXIST.toString() + ' ' + username,
                    true);
            if ((ResponseCode.valueOf(response.getResponseCode()) == ResponseCode.SUCCESS)
                    && (response.getResponseContent()[0].equals(Boolean.toString(true)))) {
                response = sendRequest(RequestCode.LOGIN.toString() + ' ' + username + ' ' + passwordHash,
                        true);
                return (ResponseCode.valueOf(response.getResponseCode()) == ResponseCode.SUCCESS)
                        && (response.getResponseContent()[0].equals(Boolean.toString(true)));
            } else {
                response = sendRequest(RequestCode.ADD_ACCOUNT.toString() + ' ' + username + ' ' + passwordHash,
                        true);
                return ResponseCode.valueOf(response.getResponseCode()) == ResponseCode.SUCCESS;
            }
        } catch (SocketSenderException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String request;

        setUp(args);

        if (!tryLogin()) {
            System.out.println("Error while login");
            return;
        }

        do {
            System.out.print("Print command: ");
            request = ConsoleScanner.getNonEmptyString();
            if (!request.equals(EXIT_COMMAND)) {
                try {
                    System.out.println(getResponseMessage(sendRequest(request, false)));
                } catch (SocketSenderException e) {
                    System.err.println("Error sending command");
                }
            }
        } while (!request.equals(EXIT_COMMAND));
    }
}
