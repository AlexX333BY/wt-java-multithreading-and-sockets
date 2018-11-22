package by.bsuir.kaziukovich.archive.logic.command.request;

import java.util.Arrays;
import java.util.Objects;

public class CommandRequest {
    private final CommandRequestCode requestCode;

    private final String[] requestContent;

    public CommandRequestCode getRequestCode() {
        return requestCode;
    }

    public String[] getRequestContent() {
        return requestContent;
    }

    @Override
    public boolean equals(Object o) {
        CommandRequest toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (CommandRequest) o;
        return (requestCode == toCompare.requestCode) && Arrays.equals(requestContent, toCompare.requestContent);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(requestCode) + Arrays.hashCode(requestContent);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@responseCode: " + requestCode + ", responseContent: "
                + Arrays.toString(requestContent);
    }

    public CommandRequest(CommandRequestCode requestCode, String[] content) {
        if ((requestCode == null) || (content == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.requestCode = requestCode;
        requestContent = content.clone();
    }
}
