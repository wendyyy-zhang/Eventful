package user_related;

import _start.GeneralPresenter;

public class UserPresenter extends GeneralPresenter {
    /**
     * Prints out the main menu for an Attendee or an Organizer.
     */
    public void printMainMenu1() {
        System.out.println("\n" +
                "Menu:\n" +
                "1. Event\n" +
                "2. Message\n" +
                "3. Friend\n" +
                "Option:");
    }

    /**
     * Prints out the main menu for a Speaker
     */
    public void printMainMenu2() {
        System.out.println("\n" +
                "Menu:\n" +
                "1. Event\n" +
                "2. Message\n" +
                "Option:");
    }
}
