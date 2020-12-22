package user_related;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An organizer manager.
 * A manager that stores Organizers.
 */
public class OrganizerManager implements iUserManager, iAttendManager {

    private final ArrayList<iUser> organizers;
    Hashtable<String, iUser> usernameToOrganizer;

    /**
     * Creates an instance of OrganizerManager.
     */
    public OrganizerManager() {
        organizers = new ArrayList<>();
        usernameToOrganizer = new Hashtable<>();
    }

    /**
     * Returns a newly created organizer with the given username and password.
     *
     * @param username the given username
     * @param password the given password
     * @return a newly created organizer with the given username and password
     */
    @Override
    public iUser createUser(String username, String password) {
        return new Organizer(username, password);
    }

    /**
     * Adds the given organizer to the collection.
     *
     * @param organizer the given organizer
     */
    @Override
    public void addUser(iUser organizer) {
        String username = organizer.getUsername();
        organizers.add(organizer);
        usernameToOrganizer.put(username, organizer);
    }

    /**
     * Returns true if and only if the given username matches with any existing organizer.
     *
     * @param username the given username
     * @return true if and only if the given username matches with any existing organizer
     */
    @Override
    public boolean userExists(String username) {
        return usernameToOrganizer.containsKey(username);
    }

    /**
     * Returns a list of organizers.
     *
     * @return a list of organizers
     */
    @Override
    public ArrayList<iUser> getUsers() {
        return organizers;
    }
}
