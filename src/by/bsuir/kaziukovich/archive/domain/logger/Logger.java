package by.bsuir.kaziukovich.archive.domain.logger;

import java.io.PrintStream;

public class Logger {
    private static PrintStream errorStream = System.err;

    public static void log(Throwable throwable) {
        throwable.printStackTrace(errorStream);
    }

    public static void setErrorStream(PrintStream stream) {
        errorStream = stream;
    }

    private Logger() { }
}
