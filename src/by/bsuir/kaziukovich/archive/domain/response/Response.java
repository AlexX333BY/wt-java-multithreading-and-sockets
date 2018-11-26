package by.bsuir.kaziukovich.archive.domain.response;

import java.util.Arrays;
import java.util.Objects;

public class Response {
    private final String responseCode;

    private final String[] responseContent;

    public String getResponseCode() {
        return responseCode;
    }

    public String[] getResponseContent() {
        return responseContent;
    }

    @Override
    public boolean equals(Object o) {
        Response toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (Response) o;
        return Objects.equals(responseCode, toCompare.responseCode) && Arrays.equals(responseContent, toCompare.responseContent);
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

    public Response(String responseCode, String[] content) {
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
}
