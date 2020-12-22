package friend_related;

import _start.GetInput;
import login_related.RegisterSystem;
import user_related.*;

import java.util.ArrayList;

/**
 * The System responsible for all friend-related user interactions
 */
public class FriendSystem {
    private final FriendPresenter presenter = new FriendPresenter();
    private final GetInput getInput = new GetInput();

    // managers
    private final UserManager userManager;
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;

    // update
    private final FriendManager friendManager = new FriendManager();

    /**
     * Constructs an instance of FriendSystem based on the given RegisterSystem
     *
     * @param registerSystem the given RegisterSystem
     */
    public FriendSystem(RegisterSystem registerSystem) {
        userManager = registerSystem.getUserManager();
        attendeeManager = registerSystem.getAttendeeManager();
        organizerManager = registerSystem.getOrganizerManager();
    }

    /**
     * Allows the given user to interact with friend menu
     *
     * @param user the given user
     */
    public void run(iUser user) {
        String option = "";
        while (!option.equals("back")) {
            presenter.printFriend();
            option = getInput.getNumericalInput(2);
            friendOptions(user, option);
        }
    }

    // helper methods
    private void friendOptions(iUser user, String option) {
        if (option.equals("1")) {
            // see friend list
            seeFriendList(user);
        } else if (option.equals("2")) {
            // add friend
            addFriend(user);
        }
    }

    private void seeFriendList(iUser user) {
        ArrayList<iUser> friends = friendManager.getFriend(user);
        String friendNames = userManager.usersToString(friends);
        System.out.println(friendNames);
    }

    private void addFriend(iUser user) {
        boolean isOrganizer = user.isOrganizer();
        String friendName = getFriend();

        boolean isValidFriend = isValidFriend(isOrganizer, friendName);

        if (isValidFriend) {
            boolean friendAdded = friendAdded(user, friendName);

            if (friendAdded) {
                presenter.printFriendAdded();
            } else {
                presenter.printDuplicateFriend();
            }
        } else {
            presenter.printNoMatch();
        }
    }

    private String getFriend() {
        presenter.printSearch();
        return getInput.getUserInput();
    }

    private boolean isValidFriend(boolean isOrganizer, String friendName) {
        iAttendManager manager;
        if (isOrganizer) {
            manager = organizerManager;
        } else {
            manager = attendeeManager;
        }
        return manager.userExists(friendName);
    }

    private boolean friendAdded(iUser user, String friendName) {
        iUser friend = userManager.getUser(friendName);
        return friendManager.addFriend(user, friend);
    }
}
