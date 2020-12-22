package message_related;

import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A received message manager.
 * <p>
 * Stores the relationship between user and their lists of received messages
 */
public class ReceivedMessageManager implements iMessageManager {

    // all received messages of this iUser {user, [message]}
    private final Hashtable<iUser, ArrayList<iMessage>> userToReceivedMessages;

    /**
     * Constructs a new received message manager.
     */
    public ReceivedMessageManager() {
        userToReceivedMessages = new Hashtable<>();
    }

    /**
     * Returns (i-1)th message in the given user's received message list.
     *
     * @param user the give user
     * @param i    the number of index + 1
     * @return (i - 1)th message in the given user's received message list.
     */
    @Override
    public iMessage grabMessage(iUser user, int i) {
        return userToReceivedMessages.get(user).get(i - 1);
    }

    /**
     * Returns the given user's list of received messages.
     *
     * @param user the given user
     * @return the given user's list of received messages
     */
    @Override
    public ArrayList<iMessage> getMessages(iUser user) {
        return userToReceivedMessages.getOrDefault(user, new ArrayList<>());
    }

    /**
     * Returns the length of the given user's received message list.
     *
     * @param user the given user
     * @return the length of the given user's received message list
     */
    @Override
    public int getListLength(iUser user) {
        return getMessages(user).size();
    }

    /**
     * Adds the given message to the given user's received message list.
     *
     * @param user    the given user
     * @param message the given message
     */
    @Override
    public void addMessage(iUser user, iMessage message) {
        if (!getMessages(user).isEmpty()) {
            userToReceivedMessages.get(user).add(message);
        } else {
            ArrayList<iMessage> messages = new ArrayList<>();
            messages.add(message);
            userToReceivedMessages.put(user, messages);
        }
    }

    /**
     * Deletes the given message from the given user's received message list.
     *
     * @param user    the given user
     * @param message the given message
     */
    @Override
    public void deleteMessage(iUser user, iMessage message) {
        getMessages(user).remove(message);
    }
}


