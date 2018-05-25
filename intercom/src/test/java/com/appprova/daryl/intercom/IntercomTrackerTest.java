package com.appprova.daryl.intercom;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import io.intercom.android.sdk.Intercom;

import static com.appprova.daryl.Constants.EVENT_CATEGORY;
import static com.appprova.daryl.Constants.EVENT_NAME;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IntercomTrackerTest {

    private IntercomTracker subject;
    private Intercom tracker;

    @Before
    public void setUp() {
        this.tracker = mock(Intercom.class);
        this.subject = new IntercomTracker(this.tracker);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLogEvent() {

        // Arrange

        HashMap<String, Object> eventValue = new HashMap<>();
        eventValue.put(EVENT_NAME, "testName");
        eventValue.put(EVENT_CATEGORY, "testCategory");
        // Act
        this.subject.logEvent(eventValue);
        // Assert

        verify(this.tracker).logEvent(eq("testName"), anyMap());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTrackPageView() {

        // Act
        this.subject.logPageView("test");
        // Assert

        verify(this.tracker).logEvent(eq("pageView"), anyMap());
    }

}
