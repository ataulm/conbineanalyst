package uk.ac.rhul.cs.dice.golem.conbine;

import uk.ac.rhul.cs.dice.golem.action.Action;
import uk.ac.rhul.cs.dice.golem.action.Event;
import uk.ac.rhul.cs.dice.golem.conbine.action.NegotiationAction;
import uk.ac.rhul.cs.dice.golem.container.ContainerHistory;

import static uk.ac.rhul.cs.dice.golem.conbine.action.ConbineActionType.*;

/**
 * MetricsCollector takes as input a ContainerHistory and agent ID (buyer)
 * and returns a set of metrics in a Metrics wrapper.
 */
public class MetricsCollector {
    public static Metrics findMetricsForAgent(ContainerHistory history, String agent) {
        Metrics.Builder metrics = new Metrics.Builder();

        for (int i = 0; i < history.size(); i++) {
            Event event = history.get(i);
            Action action = event.getAction();

            if (isAnnounceEntranceActionByAgent(agent, event)) {
                metrics.startTime(event.getTimestamp());
                continue;
            }

            if (isAcceptActionByAgent(agent, event)) {
                metrics.successful(true);
                metrics.acceptTime(event.getTimestamp());
                double offer = backtrackFromAcceptEventToFindOffer(history, i, event.getAction());
                metrics.utility(calculateUtilityBasedOnOffer(offer));
            }
        }
        return metrics.build();
    }

    private static double calculateUtilityBasedOnOffer(double offer) {
        // FIXME: how can we calculate utility - we don't know the deadline, initialPrice or reservationPrice
        return 0;
    }

    private static double backtrackFromAcceptEventToFindOffer(ContainerHistory history, int accept, Action action) {
        String dialogueId = ((NegotiationAction) action).getDialogueId();

        for (int i = accept; i >= 0; i--) {
            Event event = history.get(i);
            if (isOfferActionWithMatchingDialogueId(event, dialogueId)) {
                String value = ((NegotiationAction) event.getAction()).getValue();
                return Double.valueOf(value);
            }
        }
        return 0;
    }

    private static boolean isOfferActionWithMatchingDialogueId(Event event, String dialogueId) {
        return event.getActionType().equals(OFFER.toString())
                && ((NegotiationAction) event.getAction()).getDialogueId().equals(dialogueId);
    }

    private static boolean isAcceptActionByAgent(String agent, Event event) {
        return event.getActionType().equals(ACCEPT.toString())
                && event.getInitiatorId().equals(agent);
    }

    private static boolean isAnnounceEntranceActionByAgent(String agent, Event event) {
        return event.getActionType().equals(ANNOUNCE_ENTRANCE.toString())
                && event.getInitiatorId().equals(agent);
    }
}

