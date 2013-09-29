package uk.ac.rhul.cs.dice.golem.conbine;

import uk.ac.rhul.cs.dice.golem.action.Action;
import uk.ac.rhul.cs.dice.golem.conbine.action.AnnounceEntranceAction;
import uk.ac.rhul.cs.dice.golem.conbine.action.ConbineActionType;
import uk.ac.rhul.cs.dice.golem.conbine.action.ExitAllAction;
import uk.ac.rhul.cs.dice.golem.conbine.action.NegotiationAction;

import static uk.ac.rhul.cs.dice.golem.conbine.action.ConbineActionType.valueOf;

public class ActionParser {

    public static Action parse(String senderId, String action) {
        return parse(senderId, null, action);
    }

    public static Action parse(String senderId, String recipientId, String action) {
        // exit_all has to be weird, and doesn't have an action type
        if (action.contains("(")) {
            return reconstructActionGivenActionType(senderId, recipientId, action);
        }
        return reconstructActionForExitAll(senderId, recipientId, action);
    }

    private static Action reconstructActionForExitAll(String senderId, String recipientId, String action) {
        String[] parameters = action.split(", ");
        return reconstructAction(senderId, recipientId, "exit_all", parameters);
    }

    private static Action reconstructActionGivenActionType(String senderId, String recipientId, String action) {
        String type = getActionType(action);
        String[] parameters = getActionPayload(action, type);
        return reconstructAction(senderId, recipientId, type, parameters);
    }

    private static String[] getActionPayload(String action, String type) {
        return action.substring(type.length() + 1, action.length() - 1).split(", ");
    }

    private static String getActionType(String action) {
        return action.substring(0, action.indexOf("("));
    }

    private static Action reconstructAction(String senderId, String recipientId, String type, String[] terms) {
        if (isNegotiationAction(type)) {
            return reconstructNegotiationAction(senderId, recipientId, type, terms);
        } else if (type.equals(ConbineActionType.EXIT_ALL.toString())) {
            return reconstructExitAllAction(senderId, terms);
        } else if (type.equals(ConbineActionType.ANNOUNCE_ENTRANCE.toString())) {
            return reconstructAnnounceEntranceAction(senderId, terms);
        }
        return null;
    }

    private static boolean isNegotiationAction(String type) {
        return type.equals(ConbineActionType.OFFER.toString())
                || type.equals(ConbineActionType.COMMIT.toString())
                || type.equals(ConbineActionType.DECOMMIT.toString())
                || type.equals(ConbineActionType.ACCEPT.toString())
                || type.equals(ConbineActionType.EXIT.toString());
    }

    private static Action reconstructAnnounceEntranceAction(String senderId, String[] terms) {
        return new AnnounceEntranceAction.Builder()
                .setAgentId(senderId)
                .setAgentType(terms[0])
                .setProductId(terms[ExitAllAction.PRODUCT_ID])
                .build();
    }

    private static Action reconstructExitAllAction(String senderId, String[] terms) {
        System.out.println(terms[ExitAllAction.AGENT_TYPE]);
        return new ExitAllAction.Builder()
                .setAgentId(senderId)
                .setAgentType(terms[0])
                .setProductId(terms[2])
                .build();
    }

    private static Action reconstructNegotiationAction(String senderId, String recipientId, String type, String[] terms) {
        NegotiationAction.Builder builder = new NegotiationAction.Builder()
                .setType(valueOf(type.toUpperCase()))
                .setProtocol(terms[NegotiationAction.PROTOCOL])
                .setDialogueId(terms[NegotiationAction.DIALOGUE_ID])
                .setProductId(terms[NegotiationAction.PRODUCT_ID])
                .setRecipient(recipientId)
                .setReplyToId(senderId);

        if (terms.length - 1 == NegotiationAction.VALUE) {
            builder.setValue(terms[NegotiationAction.VALUE]);
        }

        return builder.build();
    }
}
