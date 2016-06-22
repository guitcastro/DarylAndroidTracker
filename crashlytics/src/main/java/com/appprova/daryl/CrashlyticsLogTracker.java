package com.appprova.daryl;


import com.crashlytics.android.Crashlytics;

import java.util.Map;

public class CrashlyticsLogTracker implements TrackerAdapter {
    @Override
    public void logPageView(String name) {
        Crashlytics.log("pageView: " + name);
    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        StringBuilder builder = new StringBuilder("event: ");
        for (Map.Entry<String, Object> entry : eventData.entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        Crashlytics.log(builder.toString());
    }

    @Override
    public void setUserProperty(String key, Object value) {
        switch (key) {
            case Constants.USER_PROPERTY_EMAIL:
                Crashlytics.setUserEmail(value.toString());
            case Constants.USER_PROPERTY_ID:
                Crashlytics.setUserIdentifier(value.toString());
            case Constants.USER_PROPERTY_NAME:
                Crashlytics.setUserName(value.toString());
        }
    }
}
