package _start;

public class GeneralPresenter {

    // TODO
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
     * Asks the user to provide a given number or go back to the previous level of menu
     */
    public void printEnterNumber() {
        System.out.println("\nPlease enter a number from above.");
    }

    /**
     * Prints when an IOException was thrown
     */
    public void printWrong() {
        System.out.println("\nSomething went wrong...");
    }

    /**
     * Prints the given string onto the UI
     *
     * @param string the given string
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Prints when the provided user cannot be found.
     */
    public void printNoMatch() {
        System.out.println("\nUser is not found.");
    }
}


