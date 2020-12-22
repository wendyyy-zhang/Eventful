package message_related;

import user_related.iUser;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A message manager.
 */
public class MessageManager implements Serializable {

    /**
     * Returns a newly created message with given sender name, receiver name, title, and content.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @param title    the title of the message
     * @param content  the content of the message
     * @return a newly created message with given sender name, receiver name, title, and content.
     */
    public iMessage createMessage(iUser sender, iUser receiver, String title, String content) {
        return new Message(sender, receiver, title, content);
    }

    /**
     * Returns the titles of given messages along with the name of sender if type is 1; the name of receiver
     * if the type is 2.
     *
     * @param messages the given messages
     * @param type     the type of message will be shown, can either be 1 (received) or 2 (sent)
     * @return the title of given messages along with the name of sender if type is 1; the name of receiver
     * if the type is 2.
     */
    public String messagesToTitles(ArrayList<iMessage> messages, int type) {
        if (!messages.isEmpty()) {
            StringBuilder result = new StringBuilder();
            result.append("----------------------------------------\n");

            int length = messages.size();
            for (int i = 0; i < length; i++) {
                iMessage message = messages.get(i);
                String title = message.getTitle();
                String userName;
                String keyword;
                if (type == 1) {
                    // received message, add sender name
                    userName = message.getSenderUsername();
                    keyword = "from ";
                } else {
                    // sent message, add receiver name
                    userName = message.getReceiverUsername();
                    keyword = "to ";
                }
                // number and title
                result.append(i + 1).append(". ").append(title);
                // from / to
                result.append(" (").append(keyword).append(userName).append(")\n");
            }
            result.append("----------------------------------------");
            return result.toString();
        }
        return "\nNone.";
    }
}