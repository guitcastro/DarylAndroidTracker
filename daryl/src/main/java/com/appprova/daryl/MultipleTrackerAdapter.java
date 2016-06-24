package com.appprova.daryl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultipleTrackerAdapter implements TrackerAdapter {

    private List<TrackerAdapter> trackers;

    public MultipleTrackerAdapter() {
        trackers = new ArrayList<>();
    }

    public void addTracker(TrackerAdapter tracker) {
        trackers.add(tracker);
    }

    @Override
    public void logPageView(String name) {
        for (TrackerAdapter tracker : this.trackers) {
            tracker.logPageView(name);
        }
    }

    @Override
    public void logEvent(Map<String, Object> eventData) {
        for (TrackerAdapter tracker : this.trackers) {
            tracker.logEvent(eventData);
        }
    }

    @Override
    public void setUserProperty(String key, Object value) {
        for (TrackerAdapter tracker : this.trackers) {
            tracker.setUserProperty(key, value);
        }
    }

    @Override
    public void logException(Throwable e) {
        for (TrackerAdapter tracker : this.trackers) {
            tracker.logException(e);
        }
    }
}
