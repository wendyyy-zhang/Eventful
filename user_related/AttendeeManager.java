package user_related;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A manager that stores Attendees
 */
public class AttendeeManager implements iUserManager, iAttendManager {

    private final ArrayList<iUser> attendees;
    private final Hashtable<String, iUser> usernameToAttendee;

    /**
     * Creates an instance of AttendeeManager
     */
    public AttendeeManager() {
        attendees = new ArrayList<>();
        usernameToAttendee = new Hashtable<>();
    }

    /**
     * Returns an newly created attendee with username and password.
     *
     * @param username the username
     * @param password the password
     * @return an attendee with username and password
     */
    @Override
    public iUser createUser(String username, String password) {
        return new Attendee(username, password);
    }

    /**
     * Add the attendee to the stored attendee list and hashtable of usernames to attendees
     *
     * @param attendee the attendee
     */
    @Override
    public void addUser(iUser attendee) {
        String username = attendee.getUsername();
        attendees.add(attendee);
        usernameToAttendee.put(username, attendee);
    }

    /**
     * Returns true if the attendee exists, and false otherwise.
     *
     * @param username the string of username
     * @return true if the username is in usernameToAttendee, and false otherwise
     */
    @Override
    public boolean userExists(String username) {
        return usernameToAttendee.containsKey(username);
    }

    // getters and setters

    /**
     * Returns the list of attendees.
     *
     * @return the list of Attendee
     */
    @Override
    public ArrayList<iUser> getUsers() {
        return attendees;
    }
}
