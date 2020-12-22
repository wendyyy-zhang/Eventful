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
     * Constructs a new Organizer based on the given username and password.
     *
     * @param username the given username
     * @param password the given password
     */
    public Organizer(String username, String password) {
        this.id = 'o' + username;
        this.password = password;
        this.username = username;
        this.rating = "(NA)";
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
     * Returns the password of this Organizer.
     *
     * @return the password of this Organizer
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this Organizer to the given password.
     *
     * @param newPassword the given password to set
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Returns the username of this Organizer.
     *
     * @return the username of this Organizer
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this Organizer to the given username.
     *
     * @param newUsername the given username to set
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    /**
     * Returns the rating of this Organizer.
     *
     * @return the rating of this Organizer
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets the rating of this Organizer to the given rating.
     *
     * @param rating the given rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }
}