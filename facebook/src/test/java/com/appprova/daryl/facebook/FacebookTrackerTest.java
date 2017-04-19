package com.appprova.daryl.facebook;

import android.os.Bundle;

import com.appprova.daryl.EventBuilder;
import com.facebook.appevents.AppEventsLogger;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FacebookTrackerTest {

    private FacebookTracker subject;
    private AppEventsLogger logger;


    @Before
    public void setUp() {
        this.logger = mock(AppEventsLogger.class);
        this.subject = new FacebookTracker(logger);
    }

    @Test
    public void logPageView() throws Exception {
        // ACT
        this.subject.logPageView("test");

        // Assert
        verify(this.logger).logEvent("test");
    }

    @Test
    public void logEvent() throws Exception {
        // ACT
        this.subject.logEvent(new EventBuilder("test")
                .setAction("an action")
                .get());

        // Assert
        verify(this.logger).logEvent(eq("test"),  any(Bundle.class));

    }

}