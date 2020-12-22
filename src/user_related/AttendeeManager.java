package user_related;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An attendee manager.
 * A manager that stores Attendees.
 */
public class AttendeeManager implements iUserManager, iAttendManager {

    private final ArrayList<iUser> attendees;
    private final Hashtable<String, iUser> usernameToAttendee;

    /**
     * Creates an instance of AttendeeManager.
     */
    public AttendeeManager() {
        attendees = new ArrayList<>();
        usernameToAttendee = new Hashtable<>();
    }

    /**
     * Returns a newly created attendee with the given username and password.
     *
     * @param username the given username
     * @param password the given password
     * @return a newly created attendee with the given username and password
     */
    @Override
    public iUser createUser(String username, String password) {
        return new Attendee(username, password);
    }

    /**
     * Adds the given attendee to the collection.
     *
     * @param attendee the given attendee
     */
    @Override
    public void addUser(iUser attendee) {
        String username = attendee.getUsername();
        attendees.add(attendee);
        usernameToAttendee.put(username, attendee);
    }

    /**
     * Returns true if and only if the given username matches with any existing attendee.
     *
     * @param username the given username
     * @return true if and only if the given username matches with any existing attendee
     */
    @Override
    public boolean userExists(String username) {
        return usernameToAttendee.containsKey(username);
    }

    // getters and setters

    /**
     * Returns a list of attendees.
     *
     * @return a list of attendees
     */
    @Override
    public ArrayList<iUser> getUsers() {
        return attendees;
    }
}
