package by.bsuir.kaziukovich.archive.server.logic.dateconverter.impl;

import by.bsuir.kaziukovich.archive.server.logic.dateconverter.DateConverter;
import by.bsuir.kaziukovich.archive.server.logic.dateconverter.DateConvertionException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DmyDotDateConverter implements DateConverter {
    private final SimpleDateFormat converter;

    @Override
    public Date convert(String stringDate) throws DateConvertionException {
        if (stringDate == null) {
            throw new IllegalArgumentException("Date shouldn't be null");
        }

        try {
            return converter.parse(stringDate);
        } catch (ParseException e) {
            throw new DateConvertionException("Error converting date " + stringDate, e);
        }
    }

    public DmyDotDateConverter() {
        converter = new SimpleDateFormat("dd.MM.yyyy");
    }
}
