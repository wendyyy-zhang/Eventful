package message_related;

import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An archived message manager.
 * <p>
 * Stores the relationship between users and their lists of archived messages.
 */
public class ArchivedMessageManager implements iMessageManager {
    // all archived messages of this iUser {user, [message]}
    private final Hashtable<iUser, ArrayList<iMessage>> userToArchivedMessages;

    /**
     * Constructs a new archived message manager.
     */
    public ArchivedMessageManager() {
        userToArchivedMessages = new Hashtable<>();
    }

    /**
     * Returns (i-1)th message in the given user's archived message list.
     *
     * @param user the give user
     * @param i    the number of index + 1
     * @return (i - 1)th message in the given user's archived message list.
     */
    @Override
    public iMessage grabMessage(iUser user, int i) {
        return userToArchivedMessages.get(user).get(i - 1);
    }

    /**
     * Returns the given user's list of archived messages
     *
     * @param user the given user
     * @return the given user's list of archived messages
     */
    @Override
    public ArrayList<iMessage> getMessages(iUser user) {
        return userToArchivedMessages.getOrDefault(user, new ArrayList<>());
    }

    /**
     * Returns the length of the given user's archived message list
     *
     * @param user the given user
     * @return the length of the given user's archived message list
     */
    @Override
    public int getListLength(iUser user) {
        return getMessages(user).size();
    }

    /**
     * Adds the given message to the given user's archived message list
     *
     * @param user    the given user
     * @param message the given message
     */
    @Override
    public void addMessage(iUser user, iMessage message) {
        if (!getMessages(user).isEmpty()) {
            userToArchivedMessages.get(user).add(message);
        } else {
            ArrayList<iMessage> messages = new ArrayList<>();
            messages.add(message);
            userToArchivedMessages.put(user, messages);
        }
    }

    /**
     * Deletes the given message from the given user's archived message list
     *
     * @param user    the given user
     * @param message the given message
     */
    @Override
    public void deleteMessage(iUser user, iMessage message) {
        getMessages(user).remove(message);
    }
}
