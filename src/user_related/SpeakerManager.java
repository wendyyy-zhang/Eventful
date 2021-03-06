package user_related;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A speaker manager.
 * A manager that stores Speakers.
 */
public class SpeakerManager implements Serializable, iUserManager {

    private final ArrayList<iUser> speakers = new ArrayList<>();
    private final Hashtable<String, iUser> usernameToSpeaker = new Hashtable<>();

    /**
     * Returns a newly created speaker with the given username and password.
     *
     * @param username the given username
     * @param password the given password
     * @return a newly created speaker with the given username and password
     */
    @Override
    public iUser createUser(String username, String password) {
        return new Speaker(username, password);
    }

    /**
     * Adds the given speaker to the collection.
     *
     * @param speaker the given speaker
     */
    @Override
    public void addUser(iUser speaker) {
        String username = speaker.getUsername();
        speakers.add(speaker);
        usernameToSpeaker.put(username, speaker);
    }

    /**
     * Returns the string of all existing speakers.
     *
     * @return the string of all existing speakers
     */
    public String seeAllSpeakers() {
        return speakers.toString();
    }

    // getters and setters

    /**
     * Returns a list of speakers.
     *
     * @return a list of speakers
     */
    @Override
    public ArrayList<iUser> getUsers() {
        return speakers;
    }

}
