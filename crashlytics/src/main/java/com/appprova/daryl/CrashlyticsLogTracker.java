package com.appprova.daryl;


import com.crashlytics.android.Crashlytics;

import java.util.Map;

public class CrashlyticsLogTracker implements TrackerAdapter {
    @Override
    public void logPageView(String name) {
        Crashlytics.log("pageView: " + name);
    }

    @Override
    public void logEvent(Map<String, ?> eventData) {
        Crashlytics.log(EventToStringConverter.toString(eventData));
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

    @Override
    public void logException(Throwable e) {
        Crashlytics.logException(e);
    }
}
