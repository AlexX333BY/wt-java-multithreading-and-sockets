package by.bsuir.kaziukovich.archive.client.logic.splitter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static class to split request into parts
 */
public class RequestSplitter {
    /**
     * String representing pattern of users delimiting of request parts
     */
    private static final Pattern requestPartPattern = Pattern.compile("(?:([^\\s\"]+)|(?:\"([^\"]+)\"))");

    /**
     * Splits request as it is command line arguments
     * @param request User request
     * @return Splitted request
     */
    public static String[] split(String request) {
        ArrayList<String> parts = new ArrayList<>();
        Matcher requestMatcher;
        int groupNumber;
        String group;

        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }

        requestMatcher = requestPartPattern.matcher(request);
        while (requestMatcher.find()) {
            for (groupNumber = 1; groupNumber <= requestMatcher.groupCount(); ++groupNumber) {
                group = requestMatcher.group(groupNumber);
                if (group != null) {
                    parts.add(group);
                }
            }
        }

        return parts.toArray(new String[0]);
    }

    /**
     * Private constructor to avoid object creation
     */
    private RequestSplitter() { }
}
