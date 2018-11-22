package by.bsuir.kaziukovich.archive.logic;

import java.util.Arrays;
import java.util.Objects;

public class CommandResponse {
    private final CommandResponseCode responseCode;

    private final String[] responseContent;

    public CommandResponseCode getResponseCode() {
        return responseCode;
    }

    public String[] getResponseContent() {
        return responseContent;
    }

    @Override
    public boolean equals(Object o) {
        CommandResponse toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (CommandResponse) o;
        return (responseCode == toCompare.responseCode) && Arrays.equals(responseContent, toCompare.responseContent);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(responseCode);

        result = 31 * result + Arrays.hashCode(responseContent);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@responseCode: " + responseCode + ", responseContent: "
                + Arrays.toString(responseContent);
    }

    public CommandResponse(CommandResponseCode responseCode, String[] content) {
        if ((responseCode == null) || (content == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.responseCode = responseCode;
        responseContent = content.clone();
    }
}
