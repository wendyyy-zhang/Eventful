package user_related;

/**
 * An attendee.
 * A person who can attend events.
 */
public class Attendee implements iUser {
    private final String id;
    private String username;
    private String password;

    /**
     * Constructs a new Attendee based on the given username and password.
     *
     * @param username the given username
     * @param password the given password
     */
    public Attendee(String username, String password) {
        this.id = 'a' + username;
        this.password = password;
        this.username = username;
    }

    /**
     * Returns the string version of the Attendee.
     *
     * @return the string version of the Attendee
     */
    @Override
    public String toString() {
        return getUsername();
    }

    /**
     * Returns true if and only if the user is an organizer.
     *
     * @return true if and only if the user is an organizer
     */
    @Override
    public boolean isOrganizer() {
        return false;
    }

    /**
     * Returns true if and only if the user is a speaker.
     *
     * @return true if and only if the user is a speaker
     */
    @Override
    public boolean isSpeaker() {
        return false;
    }

    // getters and setters

    /**
     * Returns the username of this attendee.
     *
     * @return the username of this attendee
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this attendee to the given username.
     *
     * @param newUsername the given username to set
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    /**
     * Returns the password of this attendee.
     *
     * @return the password of this attendee
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this attendee to the given password.
     *
     * @param newPassword the given password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

}