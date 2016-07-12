package com.appprova.daryl;

import java.util.Map;

public class EventToStringConverter {

    public static String toString(Map<String, ?> eventData) {
        StringBuilder builder = new StringBuilder("event: ");
        for (Map.Entry<String, ?> entry : eventData.entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }

}
