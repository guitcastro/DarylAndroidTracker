package com.appprova.daryl.intercom;


import com.appprova.daryl.Constants;
import com.appprova.daryl.TrackerAdapter;

import java.util.Map;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UserAttributes;
import io.intercom.android.sdk.identity.Registration;

public class IntercomTracker implements TrackerAdapter {


    @Override
    public void logPageView(String name) {
    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        String eventName = (String) eventData.get(Constants.EVENT_NAME);
        eventData.remove(Constants.EVENT_NAME);

        Intercom.client().logEvent(eventName, eventData);
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
        Intercom.client().updateUser(builder.build());
    }

    @Override
    public void logException(Throwable e) {
    }
}
