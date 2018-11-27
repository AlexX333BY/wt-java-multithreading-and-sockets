package by.bsuir.kaziukovich.archive.client.view;

import by.bsuir.kaziukovich.archive.client.logic.digest.PasswordDigestException;
import by.bsuir.kaziukovich.archive.client.logic.digest.PasswordDigestGeneratorFactory;
import by.bsuir.kaziukovich.archive.domain.console.ConsoleScanner;
import by.bsuir.kaziukovich.archive.domain.response.Response;
import by.bsuir.kaziukovich.archive.domain.response.ResponseCode;
import java.util.Map;

public class EntryPoint {
    private static int port;

    private static String username;

    private static String passwordHash;

    private static Map<ResponseCode, ResponseProcessor> responseProcessors;

    private static final String UNKNOWN_RESPONSE_MESSAGE = "Unknown response";

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
        boolean isPasswordDigestCorrect = false;

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        while ((port < 1024) || (port > 65535)) {
            System.out.println("Enter port number between 1024 and 65535");
            port = ConsoleScanner.getPositiveInt();
        }

        if (args.length >= 2) {
            username = args[1].trim();
        } else {
            System.out.print("Enter username: ");
            username = ConsoleScanner.getNonEmptyString();
        }

        if (args.length >= 3) {
            try {
                passwordHash = PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(args[2].trim());
                isPasswordDigestCorrect = true;
            } catch (PasswordDigestException ignored) { }
        }

        while (!isPasswordDigestCorrect) {
            System.out.print("Enter password: ");
            try {
                passwordHash = PasswordDigestGeneratorFactory.getPasswordDigestGenerator()
                        .generate(ConsoleScanner.getNonEmptyString());
                isPasswordDigestCorrect = true;
            } catch (PasswordDigestException ignored) { }
        }
    }

    public static void main(String[] args) {
        setUp(args);
    }
}
