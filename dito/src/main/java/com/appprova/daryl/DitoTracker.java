package com.appprova.daryl;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.com.dito.ditosdk.DitoSDK;
import br.com.dito.ditosdk.exception.DitoSDKException;
import br.com.dito.ditosdk.model.DitoCredentials;

import static com.appprova.daryl.Constants.EVENT_ACTION;
import static com.appprova.daryl.Constants.USER_PROPERTY_ID;

public class DitoTracker implements TrackerAdapter {

    final private Map<String, Object> userProperties;
    private String userId;

    public DitoTracker(Map<String, Object> userProperties) {
        this.userProperties = userProperties;
    }

    @Override
    public void logPageView(String name) {
        Map<String, Object> event = new HashMap<>();
        event.put(Constants.EVENT_ACTION, "pageView: " + name);
        this.logEvent(event);
    }

    @Override
    public void logEvent(final Map<String, Object> eventData) {
        DitoCredentials credentials = new DitoCredentials(this.userId, null, null, null, null);
        if (!eventData.containsKey("data")) {
            eventData.put("data", new HashMap<>(eventData));
        }
        try {
            DitoSDK.track(credentials, eventData, null);
        } catch (DitoSDKException e) {
            Log.e("DitoTracker", "fail to track event", e);
        }
    }

    @Override
    public void setUserProperty(String key, Object value) {
        switch (key) {
            case USER_PROPERTY_ID:
                this.userId = value.toString();
            default:
                this.userProperties.put(key, value);
        }
    }

    @Override
    public void logException(Throwable e) {

    }
}
