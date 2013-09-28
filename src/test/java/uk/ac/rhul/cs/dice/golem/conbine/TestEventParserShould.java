package uk.ac.rhul.cs.dice.golem.conbine;

import org.junit.Test;
import uk.ac.rhul.cs.dice.golem.action.Event;

public class TestEventParserShould {

    @Test
    public void extract_the_timestamp_from_a_valid_event_string() throws Exception {
        String eventString = "1379158435051:::buyer_2:::s_5:::decommit(ao, 11, buyer_2, bananas)";
        long expected = 1379158435051l;

        Event event = EventParser.parse(eventString);
        assert (event.getTimestamp() == expected);
    }
}
