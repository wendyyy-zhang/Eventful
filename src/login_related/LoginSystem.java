package login_related;

import _start.GetInput;
import user_related.UserManager;
import user_related.UserSystem;
import user_related.iUser;

/**
 * The System responsible for logging users in.
 */
public class LoginSystem {
    private final LoginPresenter presenter = new LoginPresenter();
    private final GetInput getInput = new GetInput();

    // stores userSystem
    // pass registerSystem to it
    private final UserSystem userSystem;

    // managers
    private final UserManager userManager;

    /**
     * Constructs an instance of LoginSystem based on the given RegisterSystem.
     *
     * @param registerSystem the given RegisterSystem
     */
    public LoginSystem(RegisterSystem registerSystem) {
        userSystem = new UserSystem(registerSystem);
        userManager = registerSystem.getUserManager();
    }

    /**
     * Allows users to interact with the login interface.
     */
    public void run() {
        String input = "";

        while (!input.equals("back")) {
            // enter username
            presenter.printEnterUsername();
            String username = getInput.getUserInput();

            // enter password
            presenter.printEnterPassword();
            String password = getInput.getUserInput();

            // try to the user in
            boolean loggedIn = userManager.logIn(username, password);
            if (loggedIn) {
                presenter.printLogInSuccess();

                iUser user = userManager.getUser(username);
                userSystem.run(user);
                break;
            } else {
                presenter.printLogInFail();
                input = getInput.getUserInput();
            }
        }
    }
}