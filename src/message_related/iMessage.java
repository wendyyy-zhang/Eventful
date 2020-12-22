package message_related;

/**
 * An iMessage interface.
 */
public interface iMessage {

    String getSenderUsername();

    String getReceiverUsername();

    String getTitle();

    String toString();
}
