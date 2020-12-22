package login_related;

import _start.GeneralPresenter;

public class LoginPresenter extends GeneralPresenter {
    /**
     * Asks for the username when the user tries to log in
     */
    public void printEnterUsername() {
        System.out.println("\nEnter username:");
    }

    /**
     * Asks for the password when the user tries to log in
     */
    public void printEnterPassword() {
        System.out.println("\nEnter password: ");
    }

    /**
     * Prints out when the user has successfully logged in
     */
    public void printLogInSuccess() {
        System.out.println("\nSuccessfully logged in!");
    }

    /**
     * Prints out when the user fails to log in
     */
    public void printLogInFail() {
        System.out.println("" +
                "\nInvalid input.\n\n" +
                "Enter \"retry\" to retry or \"back\" to go back.");
    }
}
