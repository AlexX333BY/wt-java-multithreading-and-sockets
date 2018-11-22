package by.bsuir.kaziukovich.archive.server.logic.dateconverter;

import by.bsuir.kaziukovich.archive.server.logic.dateconverter.impl.DmyDotDateConverter;

public class DateConverterFactory {
    private static final DateConverter converter = new DmyDotDateConverter();

    public static DateConverter getConverter() {
        return converter;
    }

    private DateConverterFactory() { }
}
