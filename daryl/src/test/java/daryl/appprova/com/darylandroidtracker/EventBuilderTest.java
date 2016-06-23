package daryl.appprova.com.darylandroidtracker;

import com.appprova.daryl.Constants;
import com.appprova.daryl.EventBuilder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class EventBuilderTest {

    @Test
    public void testSetCategory() {
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.setCategory("testCategory");

        assertEquals("testCategory", eventBuilder.get().get(Constants.EVENT_CATEGORY));
    }

    @Test
    public void testSetAction() {
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.setAction("testAction");

        assertEquals("testAction", eventBuilder.get().get(Constants.EVENT_ACTION));
    }

    @Test
    public void testSetLabel() {
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.setLabel("testLabel");

        assertEquals("testLabel", eventBuilder.get().get(Constants.EVENT_LABEL));
    }

    @Test
    public void testSetCustomProperty() {
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.setProperty("customProperty", "customValue");

        assertEquals("customValue", eventBuilder.get().get("customProperty"));
    }
}
