package _start;

public class StartPresenter extends GeneralPresenter {

    /**
     * Welcomes the user.
     */
    public void printWelcome() {
        System.out.println("Welcome!");
    }

    /**
     * Prints the start menu when the program is run.
     */
    public void printStartMenu() {
        System.out.println("\n" +
                "1. Log in\n" +
                "2. Sign up\n" +
                "Option:");
    }
}
