package login_related;

import _start.GeneralPresenter;

public class RegisterPresenter extends GeneralPresenter {

    /**
     * Ask for the account type to be created
     */
    public void printGetIdentity() {
        System.out.println("\n" +
                "Account type:\n" +
                "1. Attendee\n" +
                "2. Organizer\n" +
                "3. Speaker\n" +
                "Option:");
    }

    /**
     * Ask for a new username
     */
    public void printCreateUsername() {
        System.out.println("\nCreate username:");
    }

    /**
     * Ask to input another username different from the previous one
     */
    public void printDuplicateUsername() {
        System.out.println("\nThis username has been taken, please try again.");
    }

    /**
     * Ask to create a password for the to be created account
     */
    public void printCreatePassword() {
        System.out.println("\nCreate a password:");
    }

    /**
     * Print when an new account is successfully created
     */
    public void printAccountCreated() {
        System.out.println("\nAccount created!");
    }

    /**
     * Ask to user to confirm the creation of new account by showing the previous input
     *
     * @param id       the number representing account type, 1 is for Attendee, 2 is for Organizer, 3 is for Speaker
     * @param username the input username
     */
    public void printConfirm(int id, String username) {
        String identity = "Attendee";
        if (id == 2) {
            identity = "Organizer";
        } else if (id == 3) {
            identity = "Speaker";
        }
        System.out.println("\n" +
                "------------------------------\n" +
                " Account type: " + identity + "\n" +
                " Username: " + username + "\n" +
                "------------------------------\n" +
                "1. Confirm" + "\n" +
                "2. Decline");
    }

    /**
     * Print when a request has been declined.
     */
    public void printRequestDeclined() {
        System.out.println("\nRequest declined.");
    }
}
