package _start;

public class StartPresenter extends GeneralPresenter {

    /**
     * Welcome the user
     */
    public void printWelcome() {
        System.out.println("Welcome!");
    }

    /**
     * Print the start menu
     */
    public void printStartMenu() {
        System.out.println("\n" +
                "1. Log in\n" +
                "2. Sign up\n" +
                "Option:");
    }
}
