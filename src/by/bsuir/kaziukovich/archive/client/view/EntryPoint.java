package by.bsuir.kaziukovich.archive.client.view;

import by.bsuir.kaziukovich.archive.client.logic.digest.PasswordDigestException;
import by.bsuir.kaziukovich.archive.client.logic.digest.PasswordDigestGeneratorFactory;
import by.bsuir.kaziukovich.archive.domain.console.ConsoleScanner;

public class EntryPoint {
    private static int port;

    private static String username;

    private static String passwordHash;

    private static void setUp(String[] args) throws PasswordDigestException {
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        } else {
            do {
                System.out.println("Enter port number between 1024 and 65535");
                port = ConsoleScanner.getPositiveInt();
            } while ((port < 1024) || (port > 65535));
        }

        if (args.length >= 3) {
            username = args[1].trim();
            passwordHash = PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(args[2].trim());
        } else {
            System.out.print("Enter username: ");
            username = ConsoleScanner.getNonEmptyString();
            System.out.print("Enter password: ");
            passwordHash = PasswordDigestGeneratorFactory.getPasswordDigestGenerator()
                    .generate(ConsoleScanner.getNonEmptyString());
        }
    }

    public static void main(String[] args) {
        try {
            setUp(args);
        } catch (PasswordDigestException e) {
            // TODO
        }
    }
}
