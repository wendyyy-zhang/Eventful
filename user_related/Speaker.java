package user_related;

/**
 * A speaker: a person who gives talks.
 */
public class Speaker implements iUser {
    private final String id;
    private String username;
    private String password;

    /**
     * Constructs a new Speaker.
     *
     * @param username the username
     * @param password the password
     */
    public Speaker(String username, String password) {
        this.id = 's' + username;
        this.password = password;
        this.username = username;
    }

    /**
     * Returns the string version of a speaker
     *
     * @return the string version of a speaker
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
        return true;
    }

    // getters and setters

    /**
     * Returns the id of this Speaker.
     *
     * @return the id of this Speaker.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the password of this Speaker.
     *
     * @return the password of this Speaker.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username of this Speaker.
     *
     * @return the username of this Speaker.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password of this Speaker to a new password.
     *
     * @param newPassword the new password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Sets the username of this Speaker to a new username.
     *
     * @param newUsername the new username to set
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
}