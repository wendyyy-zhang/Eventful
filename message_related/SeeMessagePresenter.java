package message_related;

public class SeeMessagePresenter extends MessagePresenter {
    /**
     * Prints out options of different lists of messages to check.
     */
    public void printSeeMessage() {
        System.out.println("\n" +
                "What would you check:\n" +
                "1. general inbox\n" +
                "2. unread messages\n" +
                "3. archived messages\n" +
                "4. sent messages\n" +
                "Option:");
    }

    /**
     * Prints out options under a chosen message.
     */
    public void printMessageOption() {
        System.out.println("" +
                "1. Mark as unread\n" +
                "2. Archive\n" +
                "3. Delete\n" +
                "4. Reply to messages");
    }

    /**
     * Prints out when a message is successfully marked as unread.
     */
    public void printMarked() {
        System.out.println("\nMessage Marked.");
    }

    /**
     * Prints out when a message is successfully archived.
     */
    public void printArchived() {
        System.out.println("\nMessage Archived.");
    }

    /**
     * Prints out to confirm if the chosen message is to be removed from the current box.
     */
    public void printConfirmRemove() {
        System.out.println("\n" +
                "This message will be removed from this box\n" +
                "1. Confirm\n" +
                "2. Decline");
    }

    /**
     * Prints out to confirm if the chosen message is to be deleted from all inboxes.
     */
    public void printConfirmDelete() {
        System.out.println("\n" +
                "This message will be deleted from all inboxes\n" +
                "1. Confirm\n" +
                "2. Decline");
    }

    /**
     * Prints out when a message is successfully removed or deleted
     */
    public void printMessageDeleted() {
        System.out.println("\nMessage successfully deleted.");
    }
}
