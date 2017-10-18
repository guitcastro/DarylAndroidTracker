package com.appprova.daryl.facebook;

import android.os.Bundle;

import com.appprova.daryl.Constants;
import com.appprova.daryl.TrackerAdapter;
import com.facebook.appevents.AppEventsLogger;

import java.util.HashMap;
import java.util.Map;

public class FacebookTracker implements TrackerAdapter {

    private final AppEventsLogger logger;

    public FacebookTracker(AppEventsLogger logger) {
        this.logger = logger;
    }

    @Override
    public void logPageView(String name) {
        logger.logEvent(name);
    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        final Map<String, Object> eventDataCopy = new HashMap<>(eventData);
        String eventName = (String) eventDataCopy.get(Constants.EVENT_NAME);
        eventDataCopy.remove(Constants.EVENT_NAME);

        Bundle bundle = new Bundle();
        for (Map.Entry<String, ?> entry : eventDataCopy.entrySet()) {
            if (entry.getValue() != null) {
                bundle.putString(entry.getKey(), entry.getValue().toString());
            }
        }

        this.logger.logEvent(eventName, bundle);
    }

    @Override
    public void setUserProperty(String key, Object value) {

    }

    @Override
    public void logException(Throwable e) {

    }
}
