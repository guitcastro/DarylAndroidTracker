package com.appprova.daryl;

import com.google.android.gms.analytics.Tracker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;

import static com.appprova.daryl.Constants.EVENT_ACTION;
import static com.appprova.daryl.Constants.EVENT_CATEGORY;
import static com.appprova.daryl.Constants.EVENT_LABEL;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GoogleAnalyticsTrackerTest {

    private GoogleAnalyticsTracker subject;
    private Tracker tracker;

    @Before
    public void setUp() {
        this.tracker = mock(Tracker.class);
        this.subject = new GoogleAnalyticsTracker(this.tracker);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLogEvent() {
        // Arrange

        HashMap<String, Object> eventValue = new HashMap<>();
        eventValue.put(EVENT_CATEGORY, "testCategory");
        eventValue.put(EVENT_ACTION, "testAction");
        eventValue.put(EVENT_LABEL, "testLabel");
        ArgumentCaptor<HashMap> event = ArgumentCaptor.forClass(HashMap.class);

        // Act

        this.subject.logEvent(eventValue);
        // Assert

        verify(this.tracker).send(event.capture());

        assertTrue(eventValue.containsValue("testCategory"));
        assertTrue(eventValue.containsValue("testLabel"));
        assertTrue(eventValue.containsValue("testAction"));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTrackPageView() {
        // Act
        this.subject.logPageView("test");
        // Assert

        verify(this.tracker).setScreenName("test");
        verify(this.tracker).send(anyMap());
    }

}
