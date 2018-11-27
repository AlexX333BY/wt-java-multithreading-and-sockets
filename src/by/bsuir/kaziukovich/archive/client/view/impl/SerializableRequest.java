package by.bsuir.kaziukovich.archive.client.view.impl;

import by.bsuir.kaziukovich.archive.domain.request.Request;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class SerializableRequest implements Serializable, Request {
    private String requestCode;

    private String[] requestContent;

    private String requester;

    @Override
    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public String[] getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String[] requestContent) {
        this.requestContent = requestContent;
    }

    @Override
    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    @Override
    public boolean equals(Object o) {
        SerializableRequest toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SerializableRequest) o;
        return Objects.equals(requestCode, toCompare.requestCode)
                && Arrays.equals(requestContent, toCompare.requestContent)
                && Objects.equals(requester, toCompare.requester);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(requestCode, requester) + Arrays.hashCode(requestContent);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@responseCode: " + requestCode + ", responseContent: "
                + Arrays.toString(requestContent) + ", requester: " + requester;
    }

    public SerializableRequest(String requestCode, String[] content, String requester) {
        if (requestCode == null) {
            throw new IllegalArgumentException("Request code shouldn't be null");
        }

        this.requestCode = requestCode;
        this.requester = requester;
        if (content == null) {
            requestContent = null;
        } else {
            requestContent = content.clone();
        }
    }

    public SerializableRequest() { }
}
