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

    private ConsoleScanner() {}
}
