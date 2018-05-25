package com.appprova.daryl.mixpanel;

import com.appprova.daryl.EventBuilder;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MixpanelTrackerTest {

    private MixpanelTracker subject;
    private MixpanelAPI mixpanelAPI;

    @Before
    public void setUp() {
        this.mixpanelAPI = mock(MixpanelAPI.class);
        this.subject = new MixpanelTracker(mixpanelAPI);
    }

    @Test
    public void logPageView() {
        // ACT
        this.subject.logPageView("test");

        // Assert
        verify(this.mixpanelAPI).timeEvent("test");

        this.subject.logPageView("test2");
        verify(this.mixpanelAPI).track("test"); // finished track old page
        verify(this.mixpanelAPI).timeEvent("test2"); // start track new page
    }

    @Test
    @SuppressWarnings("unchecked")
    public void logEvent() {
        // ACT
        this.subject.logEvent(new EventBuilder("test")
                .setAction("an action")
                .get());

        // Assert
        verify(this.mixpanelAPI).trackMap(eq("test"), anyMap());

    }
}