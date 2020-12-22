package user_related;

import rating_related.iRate;

/**
 * An organizer: a person who can attend and organize events.
 */
public class Organizer implements iUser, iRate {
    private final String id;
    private String username;
    private String password;
    private String rating;

    /**
     * Constructs a new Organizer.
     *
     * @param username the username
     * @param password the password
     */
    public Organizer(String username, String password) {
        this.id = 'o' + username;
        this.password = password;
        this.username = username;
        this.rating = "(NA)";
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
        return true;
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
     * Returns the id of this Organizer.
     *
     * @return the id of this Organizer.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the password of this Organizer.
     *
     * @return the password of this Organizer.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username of this Organizer.
     *
     * @return the username of this Organizer.
     */
    @Override
    public String getUsername() {
        return username;
    }

    // TODO
    public String getRating() {
        return rating;
    }

    /**
     * Sets the password of this Organizer to a new password.
     *
     * @param newPassword the new password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Sets the username of this Organizer to a new username.
     *
     * @param newUsername the new username to set
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    //TODO
    public void setRating(String rating) {
        this.rating = rating;
    }
}