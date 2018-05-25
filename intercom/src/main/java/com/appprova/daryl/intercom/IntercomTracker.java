package com.appprova.daryl.intercom;


import android.os.Bundle;

import com.appprova.daryl.Constants;
import com.appprova.daryl.TrackerAdapter;

import java.util.HashMap;
import java.util.Map;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UserAttributes;
import io.intercom.android.sdk.identity.Registration;

public class IntercomTracker implements TrackerAdapter {

    private final Intercom client;

    public IntercomTracker(Intercom client) {
        this.client = client;
    }

    @Override
    public void logPageView(String name) {
        final Map<String, String> bundle = new HashMap<>();
        bundle.put("pageName", name);
        this.client.logEvent("pageView", bundle);
    }

    @Override
    public void logEvent(final Map<String, Object> eventData) {
        final Map<String, Object> eventDataCopy = new HashMap<>(eventData);
        String eventName = (String) eventDataCopy.get(Constants.EVENT_NAME);
        eventDataCopy.remove(Constants.EVENT_NAME);

        this.client.logEvent(eventName, eventDataCopy);
    }

    @Override
    public void setUserProperty(String key, Object value) {
        UserAttributes.Builder builder = new UserAttributes.Builder();
        switch (key) {
            case Constants.USER_PROPERTY_ID:
                Registration registration = Registration.create().withUserId(value.toString());
                Intercom.client().registerIdentifiedUser(registration);
                return;
            case Constants.USER_PROPERTY_EMAIL:
                builder.withEmail(value.toString());
                break;
            case Constants.USER_PROPERTY_NAME:
                builder.withName(value.toString());
                break;
            case Constants.USER_PROPERTY_PHONE:
                builder.withPhone(value.toString());
                break;
            default:
                builder.withCustomAttribute(key, value.toString());
                break;
        }
        this.client.updateUser(builder.build());
    }

    @Override
    public void logException(Throwable e) {
    }
}
