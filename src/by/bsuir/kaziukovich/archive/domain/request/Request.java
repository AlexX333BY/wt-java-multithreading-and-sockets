package by.bsuir.kaziukovich.archive.domain.request;

import java.util.Arrays;
import java.util.Objects;

public class Request {
    private final RequestCode requestCode;

    private final String[] requestContent;

    private final String requester;

    public RequestCode getRequestCode() {
        return requestCode;
    }

    public String[] getRequestContent() {
        return requestContent;
    }

    public String getRequester() {
        return requester;
    }

    @Override
    public boolean equals(Object o) {
        Request toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (Request) o;
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

    public Request(RequestCode requestCode, String[] content, String requester) {
        if (requestCode == null) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.requestCode = requestCode;
        this.requester = requester;
        if (content == null) {
            requestContent = null;
        } else {
            requestContent = content.clone();
        }
    }
}
