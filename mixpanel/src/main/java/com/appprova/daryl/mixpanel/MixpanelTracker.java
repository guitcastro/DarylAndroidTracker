package com.appprova.daryl.mixpanel;


import com.appprova.daryl.Constants;
import com.appprova.daryl.TrackerAdapter;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import java.util.HashMap;
import java.util.Map;

public class MixpanelTracker implements TrackerAdapter {

    private final MixpanelAPI mixpanelAPI;
    private String currentPage = null;
    public MixpanelTracker(MixpanelAPI mixpanelAPI) {
        this.mixpanelAPI = mixpanelAPI;
    }

    @Override
    public void logPageView(final String name) {
        if (this.currentPage != null && !this.currentPage.equals(name)) {
            this.mixpanelAPI.track(this.currentPage); // finish previous page view event
        }
        this.currentPage = name;
        this.mixpanelAPI.timeEvent(this.currentPage); // start new pageview event
    }

    @Override
    public void logEvent(final Map<String, Object> eventData) {
        final Map<String, Object> eventDataCopy = new HashMap<>(eventData);
        String eventName = (String) eventDataCopy.get(Constants.EVENT_NAME);
        eventDataCopy.remove(Constants.EVENT_NAME);
        mixpanelAPI.trackMap(eventName, eventDataCopy);
    }

    @Override
    public void setUserProperty(String key, Object value) {
        switch (key) {
            case Constants.USER_PROPERTY_ID:
                this.mixpanelAPI.identify(value.toString());
                this.mixpanelAPI.getPeople().identify("13793");
                return;
            default:
                this.mixpanelAPI.getPeople().set(key, value.toString());
                break;
        }
    }

    @Override
    public void logException(Throwable e) {
    }
}
