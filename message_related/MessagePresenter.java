package message_related;

import _start.GeneralPresenter;

/**
 * A presenter related to message functionality
 */
public class MessagePresenter extends GeneralPresenter {
    /**
     * Prints out the message options
     */
    public void printMessage() {
        System.out.println("\n" +
                "1. See messages\n" +
                "2. Send message\n" +
                "Option:");
    }
}
