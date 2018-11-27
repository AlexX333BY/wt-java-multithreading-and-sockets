package by.bsuir.kaziukovich.archive.server.controller.impl;

import by.bsuir.kaziukovich.archive.domain.response.Response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class SerializableResponse implements Serializable, Response {
    private String responseCode;

    private String[] responseContent;

    @Override
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String[] getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String[] responseContent) {
        this.responseContent = responseContent;
    }

    @Override
    public boolean equals(Object o) {
        SerializableResponse toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SerializableResponse) o;
        return Objects.equals(responseCode, toCompare.responseCode)
                && Arrays.equals(responseContent, toCompare.responseContent);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(responseCode) + Arrays.hashCode(responseContent);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@responseCode: " + responseCode + ", responseContent: "
                + Arrays.toString(responseContent);
    }

    public SerializableResponse(String responseCode, String[] content) {
        if (responseCode == null) {
            throw new IllegalArgumentException("Response code shouldn't be null");
        }

        this.responseCode = responseCode;
        if (content == null) {
            responseContent = null;
        } else {
            responseContent = content.clone();
        }
    }

    public SerializableResponse() { }
}
