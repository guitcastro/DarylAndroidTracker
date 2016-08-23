package com.appprova.daryl;

import java.util.Map;

public interface TrackerAdapter {
    void logPageView(String name);

    void logEvent(Map<String, Object> eventData);

    void setUserProperty(String key, Object value);

    void logException(Throwable e);
}
