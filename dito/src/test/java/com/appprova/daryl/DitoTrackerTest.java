package com.appprova.daryl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import br.com.dito.ditosdk.DitoSDK;
import br.com.dito.ditosdk.interfaces.DitoSDKCallback;
import br.com.dito.ditosdk.model.DitoCredentials;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DitoSDK.class})
public class DitoTrackerTest {

    private TrackerAdapter subject;
    private Map<String, Object> userProperties = new HashMap<>();

    @Before
    public void setUp() {
        PowerMockito.mockStatic(DitoSDK.class);
        this.subject = new DitoTracker(userProperties);
    }

    @Test
    public void logEvent() throws Exception {

        this.subject.logEvent(new EventBuilder("test")
                .setAction("an action")
                .get());

        verifyStatic();
        DitoSDK.track(any(DitoCredentials.class), any(), any(DitoSDKCallback.class));


    }

    @Test
    public void setUserProperty() throws Exception {
        // Act
        this.subject.setUserProperty(Constants.USER_PROPERTY_ID, "123");
        this.subject.setUserProperty("custom", "a value");

        // Assert
        assertEquals("a value", this.userProperties.get("custom"));
        assertEquals("123", this.userProperties.get(Constants.USER_PROPERTY_ID));
    }

}