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
    public void logPageView(String name) {
        final Bundle bundle = new Bundle();
        bundle.putString("pageName", name);
        this.tracker.logEvent("pageView", bundle);
        FirebaseCrash.log("pageView: " + name);
    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        String eventName = (String) eventData.get(Constants.EVENT_NAME);
        eventData.remove(Constants.EVENT_NAME);

        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : eventData.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue().toString());
        }

        this.tracker.logEvent(eventName, bundle);
        FirebaseCrash.log(EventToStringConverter.toString(eventData));
    }

    @Override
    public void setUserProperty(String key, Object value) {
        switch (key) {
            case Constants.USER_PROPERTY_ID:
                this.tracker.setUserId(value.toString());
            default:
                this.tracker.setUserProperty(key, value.toString());
        }
    }

    @Override
    public void logException(Throwable e) {
        FirebaseCrash.report(e);
    }
}
