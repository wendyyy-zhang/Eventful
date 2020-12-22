package user_related;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A manager that stores iUsers
 */
public class UserManager implements Serializable {

    private final Hashtable<String, iUser> usernameToUser = new Hashtable<>();

    /**
     * Adds the given user to the collection.
     *
     * @param newUser the given user
     */
    public void addUser(iUser newUser) {
        String username = newUser.getUsername();
        usernameToUser.put(username, newUser);
    }

    /**
     * Returns true if and only if the user with the given username exists and the username
     * matches with the given password.
     *
     * @param username the username
     * @param password the password
     * @return true if and only if the user with the given username exists and the username
     * matches with the given password
     */
    public boolean logIn(String username, String password) {
        boolean userExists = usernameToUser.containsKey(username);
        if (!userExists) {
            return false;
        }
        String correctPassword = usernameToUser.get(username).getPassword();
        return password.equals(correctPassword);
    }

    /**
     * Returns true if and only if there exists a user with the given username.
     *
     * @param username the given username
     * @return true if and only if there exists a user with the given username
     */
    public boolean userExists(String username) {
        return usernameToUser.containsKey(username);
    }

    /**
     * Returns a user with the given username or null if there is no such user.
     *
     * @param username the given username
     * @return a user with the given username or null if there is no such user
     */
    public iUser getUser(String username) {
        return usernameToUser.getOrDefault(username, null);
    }

    /**
     * Returns the string version of the given list of users.
     *
     * @param users the given list of users
     * @return the string version of the given list of users
     */
    public String usersToString(ArrayList<iUser> users) {
        if (users.isEmpty()) {
            return "\nNone.";
        }
        return users.toString();
    }
}