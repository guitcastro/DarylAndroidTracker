package com.appprova.daryl.intercom;


import com.appprova.daryl.Constants;
import com.appprova.daryl.TrackerAdapter;

import java.util.Map;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UserAttributes;

public class IntercomTracker implements TrackerAdapter {


    @Override
    public void logPageView(String name) {

    }

    @Override
    public void logEvent(Map<String, Object> eventData) {

    }

    @Override
    public void setUserProperty(String key, Object value) {
        UserAttributes.Builder builder = new UserAttributes.Builder();
        switch (key) {
            case Constants.USER_PROPERTY_EMAIL:
                builder.withEmail(value.toString());
            case Constants.USER_PROPERTY_ID:
                builder.withUserId(value.toString());
            case Constants.USER_PROPERTY_NAME:
                builder.withName(value.toString());
            case Constants.USER_PROPERTY_PHONE:
                builder.withPhone(value.toString());
            default:
                builder.withCustomAttribute(key, value.toString());
        }
        Intercom.client().updateUser(builder.build());
    }

    @Override
    public void logException(Throwable e) {

    }
}
