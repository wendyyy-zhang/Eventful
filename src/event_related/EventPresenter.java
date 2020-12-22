package event_related;

import _start.GeneralPresenter;

public class EventPresenter extends GeneralPresenter {
    /**
     * Prints out Attendee event options
     */
    public void printAttendeeEvent() {
        System.out.println("\n" +
                "1. See entire event list\n" +
                "2. See signed up event list\n" +
                "Option:");
    }

    /**
     * Prints out Organizer event options
     */
    public void printOrganizerEvent() {
        System.out.println("\n" +
                "1. See entire event list\n" +
                "2. See signed up event list\n" +
                "3. Schedule a new event\n" +
                "4. See organized event list\n" +
                "Option:");
    }

    /**
     * Prints out Speaker event options
     */
    public void printSpeakerEvent() {
        System.out.println("\n" +
                "1. See related events\n" +
                "Option:");
    }

    /**
     * Prints out options under a single event for attendees and organizers
     */
    public void printSingleEventOptions() {
        System.out.println("" +
                "1. Sign up\n" +
                "2. Cancel spot");
    }

    /**
     * Prints out when the to-be-signed-up event does not have enough room
     */
    public void printNotEnoughSpace() {
        System.out.println("\nFailed: the room is full.");
    }

    /**
     * Prints out when the user has signed up for the event previously given
     */
    public void printHasSignedUp() {
        System.out.println("\nFailed: You have already signed up for this event.");
    }

    /**
     * Prints out when the user has signed up for an event successfully
     */
    public void printSignUpSuccess() {
        System.out.println("\nSuccessfully signed up!");
    }

    /**
     * Prints out when a user tries to cancel spot in an event that he/she has not yet signed up for
     */
    public void printDidNotSignedUp() {
        System.out.println("\nFailed: You did not sign up for this event.");
    }

    /**
     * Prints out when the user has successfully canceled spot in an event
     */
    public void printCancelSuccess() {
        System.out.println("\nSuccessfully cancelled!");
    }
}
