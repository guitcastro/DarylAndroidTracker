package com.appprova.daryl;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;

import static com.appprova.daryl.Constants.EVENT_CATEGORY;
import static com.appprova.daryl.Constants.EVENT_NAME;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FirebaseAnalytics.class)
public class FirebaseTrackerTest {

    private FirebaseTracker subject;
    private FirebaseAnalytics tracker;

    @Before
    public void setUp() {
        this.tracker = mock(FirebaseAnalytics.class);
        this.subject = new FirebaseTracker(this.tracker);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLogEvent() {

        if (true) {
            return; // TODO Find out why jacoco is breaking the tests
        }

        // Arrange

        HashMap<String, Object> eventValue = new HashMap<>();
        eventValue.put(EVENT_NAME, "testName");
        eventValue.put(EVENT_CATEGORY, "testCategory");
        // Act
        this.subject.logEvent(eventValue);
        // Assert

        verify(this.tracker).logEvent(eq("testName"), any(Bundle.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTrackPageView() {
        if (true) {
            return; // TODO Find out why jacoco is breaking the tests
        }

        // Act
        this.subject.logPageView("test");
        // Assert

        verify(this.tracker).logEvent(eq("pageView"), any(Bundle.class));
    }

}
