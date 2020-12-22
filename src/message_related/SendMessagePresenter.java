package message_related;

public class SendMessagePresenter extends MessagePresenter {
    /**
     * Prints out options an organizer can make when trying to send a message
     */
    public void printOrganizerSendMessage() {
        System.out.println("\n" +
                "Message:\n" +
                "1. All attendees\n" +
                "2. All speakers\n" +
                "3. A user\n" +
                "Option:");
    }

    /**
     * Prints out options a speaker can make when trying to send a message
     */
    public void printSpeakerSendMessage() {
        System.out.println("\n" +
                "Message attendees of:\n" +
                "1. One or more events\n" +
                "2. All events\n" +
                "Option:");
    }

    /**
     * Prints out options an administrator can make when trying to send a message
     */
    public void printAdminSendMessage() {
        System.out.println("\n" +
                "Message:\n" +
                "1. All users\n" +
                "2. All organizers\n" +
                "3. Selected user\n" +
                "Option:");
    }

    /**
     * Prints out to ask for the username of the receiver of the message
     */
    public void printSendTo() {
        System.out.println("\nSend to:");
    }

    /**
     * Prints out to ask for the title of the message
     */
    public void printGetTitle() {
        System.out.println("\nTitle:");
    }

    /**
     * Prints out to ask for the content of the message
     */
    public void printGetContent() {
        System.out.println("\nContent:");
    }

    /**
     * Prints out when a message is successfully sent
     */
    public void printMessageSent() {
        System.out.println("\nMessage sent!");
    }

    /**
     * Prints out to ask for the event names
     */
    public void printEventNames() {
        System.out.println("\nEnter event name(s) in such format (name1,name2,name3):");
    }

    /**
     * Prints out when there is no related events or at least one of the event names given are invalid.
     * <p>
     * A valid event name is of an event that exists and is related to the current user.
     */
    public void printInvalidEvents() {
        System.out.println("\nFailed: No related events or invalid event name(s).");
    }
}
