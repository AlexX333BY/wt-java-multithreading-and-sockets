package by.bsuir.kaziukovich.archive.client.view.impl;

import by.bsuir.kaziukovich.archive.client.view.ResponseProcessor;

import java.util.Objects;

public class IllegalArgumentsResponseProcessor implements ResponseProcessor {
    private final String message;

    @Override
    public String getResponseMessage(String[] responseContent) {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return Objects.equals(message, ((IllegalArgumentsResponseProcessor) o).message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@message: " + message;
    }

    public IllegalArgumentsResponseProcessor() {
        message = "Wrong arguments typed";
    }
}
