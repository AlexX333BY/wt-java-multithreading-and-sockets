package by.bsuir.kaziukovich.archive.server.logic.dateconverter;

import java.util.Date;

public interface DateConverter {
    Date convert(String stringDate) throws DateConvertionException;
}
