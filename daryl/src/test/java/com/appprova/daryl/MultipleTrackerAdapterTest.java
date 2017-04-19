package com.appprova.daryl;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MultipleTrackerAdapterTest {

    private MultipleTrackerAdapter subject;
    private TrackerAdapter tracker1;
    private TrackerAdapter tracker2;

    @Before
    public void setUp() {
        this.subject = new MultipleTrackerAdapter();
        this.tracker1 = mock(TrackerAdapter.class);
        this.tracker2 = mock(TrackerAdapter.class);
        this.subject.addTracker(tracker1);
        this.subject.addTracker(tracker2);
    }

    @Test
    public void logPageView() throws Exception {
        this.subject.logPageView("test");
        verify(this.tracker1).logPageView(eq("test"));
        verify(this.tracker2).logPageView(eq("test"));
    }

    @Test
    public void logEvent() throws Exception {
        Map<String, Object> event = new EventBuilder("event name").setAction("event action").get();
        this.subject.logEvent(event);

        verify(this.tracker1).logEvent(event);
        verify(this.tracker2).logEvent(event);
    }

    @Test
    public void setUserProperty() throws Exception {
        this.subject.setUserProperty("key", "value");

        verify(this.tracker1).setUserProperty("key", "value");
        verify(this.tracker2).setUserProperty("key", "value");
    }

    @Test
    public void logException() throws Exception {
        Exception ex = new IllegalArgumentException();
        this.subject.logException(ex);

        verify(this.tracker1).logException(ex);
        verify(this.tracker2).logException(ex);
    }

}