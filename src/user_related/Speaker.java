package user_related;

/**
 * A speaker: a person who gives talks.
 */
public class Speaker implements iUser {
    private final String id;
    private String username;
    private String password;

    /**
     * Constructs a new Speaker based on the given username and password.
     *
     * @param username the given username
     * @param password the given password
     */
    public Speaker(String username, String password) {
        this.id = 's' + username;
        this.password = password;
        this.username = username;
    }

    /**
     * Returns the string version of this speaker.
     *
     * @return the string version of this speaker
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
     * Returns the password of this Speaker.
     *
     * @return the password of this Speaker
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this speaker to the given password.
     *
     * @param newPassword the given password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Returns the username of this Speaker.
     *
     * @return the username of this Speaker
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this speaker to the given username.
     *
     * @param newUsername the given username to set
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
}