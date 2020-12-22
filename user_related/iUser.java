package user_related;

/**
 * An interface implemented by Attendee, Organizer, and Speaker class.
 */
public interface iUser {
    String getId();

    String getPassword();

    String getUsername();

    String toString();

    boolean isOrganizer();

    boolean isSpeaker();
}
