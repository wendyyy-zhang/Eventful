package login_related;

import _start.GetInput;
import user_related.*;

/**
 * The System responsible for creating new users according to the input.
 */
public class RegisterSystem {
    private final RegisterPresenter presenter = new RegisterPresenter();
    private final GetInput getInput = new GetInput();

    // update
    private final UserManager userManager;
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;

    public RegisterSystem() {
        userManager = new UserManager();
        attendeeManager = new AttendeeManager();
        organizerManager = new OrganizerManager();
        speakerManager = new SpeakerManager();
    }

    /**
     * Allows users to create accounts.
     */
    public void run() {
        int id = getIdentity();
        iUser user = createUser(id);
        boolean userCreated = (user != null);
        if (userCreated) {
            presenter.printAccountCreated();
        } else {
            presenter.printRequestDeclined();
        }
    }

    /**
     * Returns a user created by the given id.
     *
     * @param id the given id
     * @return a user created by the given id
     */
    public iUser createUser(int id) {
        iUser user = null;
        String username = getUsername();
        String password = getPassword();

        boolean confirmed = confirmed(id, username);
        if (confirmed) {
            user = registerOptions(username, password, id);
        }
        return user;
    }

    private int getIdentity() {
        presenter.printGetIdentity();
        return getInput.getNumber(3);
    }

    private iUser registerOptions(String username, String password, int id) {
        iUser user;
        if (id == 1) {  // create attendee
            user = userFactory(username, password, 1);
        } else if (id == 2) {  // create organizer
            user = userFactory(username, password, 2);
        } else {  // create speaker
            user = userFactory(username, password, 3);
        }
        return user;
    }

    private String getUsername() {
        presenter.printCreateUsername();
        String username = getInput.getUserInput();

        boolean userExists = userManager.userExists(username);
        while (userExists) {
            presenter.printDuplicateUsername();
            presenter.printCreateUsername();
            username = getInput.getUserInput();
            userExists = userManager.userExists(username);
        }
        return username;
    }

    private String getPassword() {
        presenter.printCreatePassword();
        return getInput.getUserInput();
    }

    private boolean confirmed(int id, String username) {
        boolean confirmed = false;
        presenter.printConfirm(id, username);
        int confirm = getInput.getNumber(2);
        if (confirm == 1) {
            confirmed = true;
        }
        return confirmed;
    }

    private iUser userFactory(String username, String password, int identity) {
        iUserManager manager;
        if (identity == 1) {  // attendee
            manager = attendeeManager;
        } else if (identity == 2) {  // organizer
            manager = organizerManager;
        } else {  // speaker
            manager = speakerManager;
        }
        iUser user = manager.createUser(username, password);
        manager.addUser(user);
        userManager.addUser(user);
        return user;
    }

    /**
     * Returns the userManager.
     *
     * @return the userManager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Returns the attendeeManager.
     *
     * @return the attendeeManager
     */
    public AttendeeManager getAttendeeManager() {
        return attendeeManager;
    }

    /**
     * Returns the speakerManager.
     *
     * @return the speakerManager
     */
    public SpeakerManager getSpeakerManager() {
        return speakerManager;
    }

    /**
     * Returns the organizerManager.
     *
     * @return the organizerManager
     */
    public OrganizerManager getOrganizerManager() {
        return organizerManager;
    }
}
