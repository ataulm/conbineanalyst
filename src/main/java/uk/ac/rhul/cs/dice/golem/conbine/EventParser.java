package uk.ac.rhul.cs.dice.golem.conbine;

import uk.ac.rhul.cs.dice.golem.action.Action;
import uk.ac.rhul.cs.dice.golem.action.Event;

/**
 * An Event in ConBiNe consists of: a timestamp, an instigator, and an Action. There
 * are three types of events in ConBiNe, typified by the following examples:
 * <p/>
 * 1) "1379158435052:::buyer_2:::buyer, buyer_2, bananas"
 * 2) "1379158435052:::buyer_2:::s_18:::exit(ao, 76, buyer_2, bananas)"
 * 3) "1379158353975:::s_7:::announce_entrance(seller, s_7, bananas)"
 * <p/>
 * In all cases, the timestamp (milliseconds since UNIX Epoch) is given first, and
 * the source of the Event (the instigator) is given second.
 * <p/>
 * There are either 3 or 4 terms in an Event. If there are 4 terms in total, the
 * 3rd term is the recipient of an entity-to-entity broadcast. If there are 3 terms
 * in total, the recipient is not given (it was broadcast to any listening entity).
 * <p/>
 * The last term in an Event is the Action's string representation, which is an
 * ordered collection of elements. Example 2 and 3 demonstrate the correct format,
 * where the type of Action is given first, with the payload (collection of elements)
 * within parentheses. Example 1 is the exception where the type was incorrectly
 * omitted (it was for "exit_all" action) so this must be inferred.
 * <p/>
 * It would have been better if the recipient could be set as part of the Action.
 */
public class EventParser {
    public static final int TIMESTAMP_INDEX = 0;
    public static final int SENDER_INDEX = 1;
    public static final String DELIMITER = ":::";

    public static Event parse(String input) {
        String[] eventParts = getEventTerms(input);
        return reconstructEvent(eventParts);
    }

    private static Event reconstructEvent(String[] eventParts) {
        long timestamp = getTimestamp(eventParts);
        String sender = getSender(eventParts);
        Action action;

        if (eventParts.length == 3) {
            action = getActionForEventWithThreeTerms(sender, eventParts[2]);
        } else {
            action = getActionForEventWithFourTerms(sender, eventParts[2], eventParts[3]);
        }

        return new Event(sender, action, timestamp);
    }

    private static Action getActionForEventWithThreeTerms(String sender, String actionToParse) {
        return ConbineActionParser.parse(sender, actionToParse);
    }

    private static Action getActionForEventWithFourTerms(String sender, String recipient, String actionToParse) {
        return ConbineActionParser.parse(sender, recipient, actionToParse);
    }

    private static String getSender(String[] eventParts) {
        return eventParts[SENDER_INDEX];
    }

    private static long getTimestamp(String[] eventParts) {
        return Long.parseLong(eventParts[TIMESTAMP_INDEX]);
    }

    private static String[] getEventTerms(String input) {
        return input.split(DELIMITER);
    }
}
