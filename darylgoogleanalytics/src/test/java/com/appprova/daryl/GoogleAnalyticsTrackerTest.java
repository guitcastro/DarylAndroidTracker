package com.appprova.daryl;

import com.google.android.gms.analytics.Tracker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;

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

        EventBuilder builder = new EventBuilder();
        builder.setCategory("testCategory")
                .setAction("testAction")
                .setLabel("testLabel");
        ArgumentCaptor<HashMap> event = ArgumentCaptor.forClass(HashMap.class);
        // Act

        this.subject.logEvent(builder.get());
        // Assert

        verify(this.tracker).send(event.capture());
        Map eventValue = event.getValue();

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
