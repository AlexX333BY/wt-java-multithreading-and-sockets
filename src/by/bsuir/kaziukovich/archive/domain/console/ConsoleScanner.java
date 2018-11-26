package by.bsuir.kaziukovich.archive.domain.console;

import java.util.Scanner;

public class ConsoleScanner {
    private static final Scanner consoleScanner = new Scanner(System.in);

    public static String getString() {
        return consoleScanner.nextLine();
    }

    public static String getNonEmptyString() {
        String result;

        do {
            result = getString();
        } while (result.isEmpty());
        return result;
    }

    public static int getInt() {
        while (!consoleScanner.hasNextInt()) {
            consoleScanner.next();
        }
        return consoleScanner.nextInt();
    }

    public static int getPositiveInt() {
        int result;

        do {
            result = getInt();
        } while (result <= 0);
        return result;
    }

    private ConsoleScanner() {}
}
