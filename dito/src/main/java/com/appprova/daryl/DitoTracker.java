package com.appprova.daryl;

import android.util.Log;

import java.util.Map;

import br.com.dito.ditosdk.DitoSDK;
import br.com.dito.ditosdk.exception.DitoSDKException;
import br.com.dito.ditosdk.model.DitoCredentials;

import static com.appprova.daryl.Constants.USER_PROPERTY_ID;

public class DitoTracker implements TrackerAdapter {

    private String userId;

    @Override
    public void logPageView(String name) {

    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        DitoCredentials credentials = new DitoCredentials(this.userId, null, null, null, null);
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

        }
    }

    @Override
    public void logException(Throwable e) {

    }
}
