package user_related;

/**
 * An attendee: a person who can attend events.
 */
public class Attendee implements iUser {
    private final String id;
    private String username;
    private String password;

    /**
     * Constructs a new Attendee.
     *
     * @param username the username
     * @param password the password
     */
    public Attendee(String username, String password) {
        this.id = 'a' + username;
        this.password = password;
        this.username = username;
    }

    /**
     * A comma-separated list of username and id.
     *
     * @return a comma-separated list of username and id.
     */
    @Override
    public String toString() {
        return getUsername();
    }

    /**
     * Returns true if the user is an organizer.
     *
     * @return true if the user is an organizer.
     */
    @Override
    public boolean isOrganizer() {
        return false;
    }

    /**
     * Returns true if the user is an speaker.
     *
     * @return true if the user is an organizer.
     */
    @Override
    public boolean isSpeaker() {
        return false;
    }

    // getters and setters

    /**
     * Returns the id of this attendee.
     *
     * @return the id of this attendee.
     */

    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the username of this attendee.
     *
     * @return the username of this attendee.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of this attendee.
     *
     * @return the password of this attendee.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this attendee to a new password.
     *
     * @param newPassword the new password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Sets the username of this attendee to a new username.
     *
     * @param newUsername the new username to set
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

}