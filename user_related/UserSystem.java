package user_related;

import _start.GetInput;
import event_related.EventSystem;
import login_related.RegisterSystem;
import message_related.MessageSystem;
import friend_related.FriendSystem;

/**
 * The System responsible for user interactions with the main menus
 */
public class UserSystem {
    private final UserPresenter presenter = new UserPresenter();
    private final GetInput getInput = new GetInput();

    // stores eventSystem, friendSystem, messageSystem
    // pass registerSystem to them
    private final EventSystem eventSystem;
    private final FriendSystem friendSystem;
    private final MessageSystem messageSystem;

    /**
     * Creates an instance of UserSystem based on the given RegisterSystem
     *
     * @param registerSystem the given RegisterSystem
     */
    public UserSystem(RegisterSystem registerSystem) {
        eventSystem = new EventSystem(registerSystem);
        friendSystem = new FriendSystem(registerSystem);
        messageSystem = new MessageSystem(registerSystem, eventSystem);
    }

    /**
     * Allow users to interact with the main menu of this program after logged in
     *
     * @param user the given user
     */
    public void run(iUser user) {

        boolean isSpeaker = user.isSpeaker();
        boolean isOrganizer = user.isOrganizer();
        boolean isAttendee = !isSpeaker && !isOrganizer;

        String input = "";
        while (!input.equals("back")) {
            if (isAttendee | isOrganizer) {
                presenter.printMainMenu1();
                input = getInput.getNumericalInput(3);
            } else {
                presenter.printMainMenu2();
                input = getInput.getNumericalInput(2);
            }
            showMenu(user, input);
        }
    }

    private void showMenu(iUser user, String input) {
        switch (input) {
            case "1":
                // event
                eventSystem.run(user);
                break;
            case "2":
                // message
                messageSystem.run(user);
                break;
            case "3":
                // friend
                friendSystem.run(user);
                break;
        }
    }
}
