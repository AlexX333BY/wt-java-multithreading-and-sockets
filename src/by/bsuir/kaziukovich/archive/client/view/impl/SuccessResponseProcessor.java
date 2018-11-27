package by.bsuir.kaziukovich.archive.client.view.impl;

import by.bsuir.kaziukovich.archive.client.view.ResponseProcessor;
import java.util.Objects;

public class SuccessResponseProcessor implements ResponseProcessor {
    private final String delimiter;

    private final String successMessage;

    @Override
    public String getResponseMessage(String[] responseContent) {
        if (responseContent == null) {
            return successMessage;
        } else {
            return String.join(delimiter, responseContent);
        }
    }

    @Override
    public boolean equals(Object o) {
        SuccessResponseProcessor toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SuccessResponseProcessor) o;
        return Objects.equals(delimiter, toCompare.delimiter)
                && Objects.equals(successMessage, toCompare.successMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delimiter, successMessage);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@delimiter: " + delimiter + ", successMessage: " + successMessage;
    }

    public SuccessResponseProcessor() {
        delimiter = "\n\n";
        successMessage = "[success]";
    }
}
