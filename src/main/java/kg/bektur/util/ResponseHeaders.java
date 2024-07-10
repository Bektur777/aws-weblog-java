package kg.bektur.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseHeaders {

    public static final Map<String, String> DEFAULT_GET_HEADERS;

    public static final Map<String, String> DEFAULT_POST_HEADERS;

    static {
        DEFAULT_GET_HEADERS = new HashMap<>();
        DEFAULT_GET_HEADERS.put("Content-Type", "application/json");
        DEFAULT_GET_HEADERS.put("Access-Control-Allow-Origin", "*");
        DEFAULT_GET_HEADERS.put("Access-Control-Allow-Methods", "GET");

        DEFAULT_POST_HEADERS = new HashMap<>();
        DEFAULT_GET_HEADERS.put("Content-Type", "application/json");
        DEFAULT_GET_HEADERS.put("Access-Control-Allow-Origin", "*");
        DEFAULT_GET_HEADERS.put("Access-Control-Allow-Methods", "POST");
    }
}
