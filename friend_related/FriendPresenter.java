package friend_related;

import _start.GeneralPresenter;

public class FriendPresenter extends GeneralPresenter {
    /**
     * Prints out friend list options for Attendee and Organizer
     */
    public void printFriend() {
        System.out.println("\n" +
                "1. See list of friends\n" +
                "2. Add friend\n" +
                "Option:");
    }

    /**
     * Asks for the username of a potential friend to be added
     */
    public void printSearch() {
        System.out.println("\nSearch a user by username:");
    }

    /**
     * Prints when a friend has been successfully added
     */
    public void printFriendAdded() {
        System.out.println("\nFriend has been successfully added!");
    }

    /**
     * Prints when a friend cannot be added
     */
    public void printDuplicateFriend() {
        System.out.println("\nFailed: a friend can not be added twice.");
    }

}
