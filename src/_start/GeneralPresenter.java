package _start;

public class GeneralPresenter {

    /**
     * Prints to ask for an input other than the empty string.
     */
    public void printEnterSomething() {
        System.out.println("Please enter something.");
    }

    /**
     * Prints when a request has been declined.
     */
    public void printRequestDeclined() {
        System.out.println("\nRequest declined.");
    }

    /**
     * Asks the user to provide a valid number that is listed above.
     */
    public void printEnterNumber() {
        System.out.println("\nPlease enter a number from above.");
    }

    /**
     * Prints when an IOException is thrown.
     */
    public void printWrong() {
        System.out.println("\nSomething went wrong...");
    }

    /**
     * Prints when the provided username does not match with any existing user.
     */
    public void printNoMatch() {
        System.out.println("\nUser is not found.");
    }
}


