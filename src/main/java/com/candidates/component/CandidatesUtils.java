package com.candidates.component;

import org.springframework.stereotype.Component;

@Component("candidatesUtils")
public class CandidatesUtils {

    private static final int LAST_POSITIONS_TO_FIRST_VARIABLE_OF_JSON = 10;

    private static final String OPEN_CURLY_BRACES = "{";

    /**
     * Used to remove special characters from json response body
     */
    public static String removeSpecialCharactersFromJson(String responseBody) {
        responseBody = responseBody.replaceAll("\\b", "");
        int begin = responseBody.indexOf("{\"name");
        if (begin < 0) {
            begin = responseBody.indexOf(LAST_POSITIONS_TO_FIRST_VARIABLE_OF_JSON);
        }
        responseBody = responseBody.substring(begin);
        int end = responseBody.lastIndexOf("}");
        return OPEN_CURLY_BRACES + responseBody.substring(0, end);
    }
}
