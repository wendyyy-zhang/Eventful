package login_related;

import _start.GeneralPresenter;

public class RegisterPresenter extends GeneralPresenter {

    /**
     * Asks for the account type to be created.
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
     * Asks to create a username for the to be created account.
     */
    public void printCreateUsername() {
        System.out.println("\nCreate username:");
    }

    /**
     * Prints when the previously input username has been taken.
     */
    public void printDuplicateUsername() {
        System.out.println("\nThis username has been taken, please try again.");
    }

    /**
     * Asks to create a password for the to be created account.
     */
    public void printCreatePassword() {
        System.out.println("\nCreate a password:");
    }

    /**
     * Prints when an new account is successfully created.
     */
    public void printAccountCreated() {
        System.out.println("\nAccount created!");
    }

    /**
     * Asks to confirm the creation of new account by the given username and account type.
     *
     * @param accountType the given type of account; 1 is Attendee, 2 is Organizer, 3 is Speaker
     * @param username    the given username
     */
    public void printConfirm(int accountType, String username) {
        String type = "Attendee";
        if (accountType == 2) {
            type = "Organizer";
        } else if (accountType == 3) {
            type = "Speaker";
        }
        System.out.println("\n" +
                "------------------------------\n" +
                " Account type: " + type + "\n" +
                " Username: " + username + "\n" +
                "------------------------------\n" +
                "1. Confirm" + "\n" +
                "2. Decline");
    }
}
