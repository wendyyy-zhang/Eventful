package _start;

import login_related.LoginSystem;
import login_related.RegisterSystem;

/**
 * The System responsible for user interaction with the start menu.
 */
public class StartSystem {
    private final StartPresenter presenter = new StartPresenter();
    private final GetInput getInput = new GetInput();

    // systems
    // stores the registerSystem
    private final RegisterSystem registerSystem = new RegisterSystem();

    // stores the loginSystem
    // pass registerSystem to it
    private final LoginSystem loginSystem = new LoginSystem(registerSystem);

    /**
     * Allows users to interact with the start menu of this program.
     */
    public void run() {
        presenter.printWelcome();
        String input = "";
        while (!input.equals("exit")) {
            presenter.printStartMenu();
            input = getInput.getNumericalInput(2);
            if (input.equals("1")) {
                // log in
                loginSystem.run();
            } else if (input.equals("2")) {
                // sign up
                registerSystem.run();
            }
        }
    }
}
