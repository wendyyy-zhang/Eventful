package user_related;

/**
 * An iUser interface.
 */
public interface iUser {
    String getPassword();

    String getUsername();

    String toString();

    boolean isOrganizer();

    boolean isSpeaker();
}
