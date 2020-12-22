package message_related;

import user_related.iUser;

/**
 * A message.
 * <p>
 * Text containing names of the sender, receiver, the title and content of a message sending between users.
 */
public class Message implements iMessage {

    private final String senderUsername;
    private final String receiverUsername;
    private final String title;
    private final String content;

    /**
     * Constructs a new message with the given sender name, a receiver name, and the content of this message
     *
     * @param sender   the sender of this message
     * @param receiver the receiver of this message
     * @param title    the title of the message
     * @param content  the content of this message
     */
    public Message(iUser sender, iUser receiver, String title, String content) {
        this.senderUsername = sender.getUsername();
        this.receiverUsername = receiver.getUsername();
        this.title = title;
        this.content = content;
    }

    /**
     * Returns the string version of this message.
     *
     * @return the string version of this message.
     */
    public String toString() {
        return "\n--------------------------------------------------\n" +
                "  Sender: " + getSenderUsername() + "\n" +
                "  Receiver: " + getReceiverUsername() + "\n" +
                "  Title: " + getTitle() + "\n" +
                "  Content: " + getContent() + "\n" +
                "--------------------------------------------------";
    }

    // getters and setters

    /**
     * Returns the name of the receiver of this message.
     *
     * @return the name of the receiver of this message.
     */
    @Override
    public String getReceiverUsername() {
        return receiverUsername;
    }

    /**
     * Returns the name of the sender of this message.
     *
     * @return the name of the sender of this message.
     */
    @Override
    public String getSenderUsername() {
        return senderUsername;
    }

    /**
     * Returns the title of this message.
     *
     * @return the title of this message.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns the content of this message.
     *
     * @return the content of this message.
     */
    public String getContent() {
        return content;
    }
}