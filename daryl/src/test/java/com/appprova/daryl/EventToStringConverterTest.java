package com.appprova.daryl;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class EventToStringConverterTest {
    @Test
    public void testToString() throws Exception {
        Map<String, Object> event = new EventBuilder("An event").setAction("an action").get();
        String myString = EventToStringConverter.toString(event);

        assertEquals("event: name : An event\naction : an action\n", myString);

    }

}