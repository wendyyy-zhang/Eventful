package user_related;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A manager that stores Organizers
 */
public class OrganizerManager implements iUserManager, iAttendManager {

    private final ArrayList<iUser> organizers;
    Hashtable<String, iUser> usernameToOrganizer;

    /**
     * Creates an instance of AttendeeManager
     */
    public OrganizerManager() {
        organizers = new ArrayList<>();
        usernameToOrganizer = new Hashtable<>();
    }

    /**
     * Returns an organizer with username and password
     *
     * @param username the username
     * @param password the password
     * @return an organizer with username and password
     */
    @Override
    public iUser createUser(String username, String password) {
        return new Organizer(username, password);
    }

    /**
     * Adds the organizer to the organizer list hashtable of username to organizer
     *
     * @param organizer the organizer
     */
    @Override
    public void addUser(iUser organizer) {
        String username = organizer.getUsername();
        organizers.add(organizer);
        usernameToOrganizer.put(username, organizer);
    }

    /**
     * Returns true if the organizer exists, and false otherwise.
     *
     * @param username the string of username
     * @return true if the username is in usernameToOrganizer, and false otherwise
     */
    @Override
    public boolean userExists(String username) {
        return usernameToOrganizer.containsKey(username);
    }

    /**
     * Returns the list of organizers.
     *
     * @return the list of Organizer
     */
    @Override
    public ArrayList<iUser> getUsers() {
        return organizers;
    }
}
