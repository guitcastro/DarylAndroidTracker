package com.appprova.daryl;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import java.util.Map;

public class FirebaseTracker implements TrackerAdapter {

    private final FirebaseAnalytics tracker;

    public FirebaseTracker(FirebaseAnalytics tracker) {
        this.tracker = tracker;
    }

    @Override
    public void logPageView(final String name) {
        final Bundle bundle = new Bundle();
        bundle.putString("pageName", sanitize(name));
        this.tracker.logEvent("pageView", bundle);
        FirebaseCrash.log("pageView: " + sanitize(name));
    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        String eventName = (String) eventData.get(Constants.EVENT_NAME);
        eventData.remove(Constants.EVENT_NAME);

        Bundle bundle = new Bundle();
        for (Map.Entry<String, ?> entry : eventData.entrySet()) {
            if (entry.getValue() != null) {
                bundle.putString(entry.getKey(), this.sanitize(entry.getValue()));
            }
        }

        this.tracker.logEvent(this.sanitize(eventName), bundle);
        FirebaseCrash.log(EventToStringConverter.toString(eventData));
    }

    @Override
    public void setUserProperty(String key, Object value) {
        switch (key) {
            case Constants.USER_PROPERTY_ID:
                this.tracker.setUserId(value.toString());
            default:
                this.tracker.setUserProperty(key, this.sanitize(value));
        }
    }

    private String sanitize(final Object value) {
        return value.toString().replace("-", "_").replace(" ", "_");
    }

    @Override
    public void logException(Throwable e) {
        FirebaseCrash.report(e);
    }
}
